package com.cqx.stncqxhat.plugin;

import com.cqx.stncqxhat.service.ChatService;
import com.cqx.stncqxhat.support.util.ApplicationContextUtil;
import com.google.common.collect.Lists;

import javax.annotation.PostConstruct;
import java.lang.ref.PhantomReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
public class PluginUtil {
    private static final String PREFIX = ":";
    private static final Pattern PLUGIN_NAME = Pattern.compile("(:\\b\\w+\\b)");
    private static Map<String, Plugin> pluginMap;
    /**
     * 是否已经初始化过了
     */
    private static volatile boolean initialized = false;

    /**
     * 初始化用的锁
     */
    private static final ReentrantLock lock = new ReentrantLock();

    /**
     * 主动阻塞时间 1000毫秒
     */
    private static final long PARK_NANOSECONDS = 1000 * 1000;

    /**
     * 自旋的次数
     * 可用处理器小于2 -> 0
     * 否则 32
     */
    private static final int SPIN_TIMES = (Runtime.getRuntime().availableProcessors() < 2) ? 0 : 32;

    public static List<Plugin> getAllPlugins() {
        return Lists.newArrayList(pluginMap.values());
    }
    /**
     * 根据mode获取插件
     * @param mode
     * @return
     */
    public static Plugin getPlugin(int mode) {
        while (!initialized) {
            doInitialize0();
        }
        return pluginMap.entrySet().stream()
                .filter(x -> mode == x.getValue().metadata().getMode())
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);
    }

    /**
     * 根据发送的消息 <:HelpPlugin /s> 获取插件
     * @param body
     * @return
     */
    public static Plugin getPlugin(String body) {
        while (!initialized) {
            doInitialize0();
        }
        return pluginMap.get(getPluginName(body));
    }

    /**
     * 是否切换插件
     * @param body
     * @return
     */
    public static boolean isPluginSwitch(String body) {
        Matcher matcher = PLUGIN_NAME.matcher(body);
        return matcher.find();
    }

    /**
     * 获取插件名字
     * @param body
     * @return
     */
    public static String getPluginName(String body) {
        Matcher matcher = PLUGIN_NAME.matcher(body);
        matcher.find();
        String name = matcher.group();
        return name.substring(1);
    }

    public static int getMode(String pluginName) {
        return getMode(pluginMap.get(pluginName));
    }

    public static int getMode(Plugin plugin) {
        return plugin.metadata().getMode();
    }

    /**
     * PluginProvider的测试
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println(getPluginName(":my world"));
//        Plugin plugin = PluginProvider.find("ChatPlugin");
//        System.out.println(plugin.metadata().getPluginName());
//        Map plugins = PluginProvider.plugins(false);
//        plugin = PluginUtil.getPlugin(0);
    }


    private static void doInitialize0() {
        if (lock.tryLock()) {
            try {
                pluginMap = ApplicationContextUtil.getBeansOfType(Plugin.class);
                initialized = true;
            } finally {
                lock.unlock();
            }
        } else {
            //锁获取失败则自旋等待其他线程初始化好就好了
            for (;;) {
                if (initialized) {
                    return;
                }
                //让当前线程等待一段时间 放弃cpu
                LockSupport.parkNanos(Thread.currentThread(), PARK_NANOSECONDS);
            }
        }
    }
}
