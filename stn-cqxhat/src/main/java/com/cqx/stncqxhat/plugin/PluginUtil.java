package com.cqx.stncqxhat.plugin;

import java.util.Map;
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

    /**
     * 根据mode获取插件
     * @param mode
     * @return
     */
    public static Plugin getPlugin(int mode) {
        return PluginProvider.plugins(false).entrySet().stream()
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
        return PluginProvider.find(getPluginName(body));
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
        return getMode(PluginProvider.find(pluginName));
    }

    public static int getMode(Plugin plugin) {
        return plugin.metadata().getMode();
    }

    /**
     * PluginProvider的测试
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getPluginName(":my world"));
        Plugin plugin = PluginProvider.find("ChatPlugin");
        System.out.println(plugin.metadata().getPluginName());
        Map plugins = PluginProvider.plugins(false);
        plugin = PluginUtil.getPlugin(0);
    }


}
