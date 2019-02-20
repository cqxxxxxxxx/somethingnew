package com.cqx.stncqxhat.plugin;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;

/**
 * 提供插件加载机制、插件获取
 * TODO: 需要注册到spring容器中
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/2/19
 */
public class PluginProvider {
    private static final Map<String, Plugin> pluginMap = new HashMap<>();
    private static final Object lock = new Object();
    private static boolean initialized = false;

    private PluginProvider() {

    }

    public static Plugin find(String name) {
        return plugins(false).get(name);
    }

    public static Map<String, Plugin> plugins(boolean update) {
        if (!update && initialized)
            return pluginMap;
        synchronized (lock) {
            return AccessController.doPrivileged(
                    new PrivilegedAction<Map<String, Plugin>>() {
                        public Map<String, Plugin> run() {
                            /**
                             * 系统属性||spi机制加载plugin
                             */
                            if (loadPluginFromProperty() || loadPluginAsService()) {
                                initialized = true;
                                return pluginMap;
                            }
                            return Collections.EMPTY_MAP;
                        }
                    });
        }
    }

    private static boolean loadPluginFromProperty() {
        String cn = System.getProperty("cqxhat.plugins");
        String[] plugins = cn.split(",");
        if (cn == null)
            return false;
        try {
            for (String plugin : plugins) {
                //            @SuppressWarnings("deprecation")
                Object tmp = Class.forName(plugin, true,
                        ClassLoader.getSystemClassLoader()).newInstance();
                pluginMap.put(plugin, (Plugin) tmp);
            }
            return true;
        } catch (ClassNotFoundException x) {
            throw new ServiceConfigurationError(null, x);
        } catch (IllegalAccessException x) {
            throw new ServiceConfigurationError(null, x);
        } catch (InstantiationException x) {
            throw new ServiceConfigurationError(null, x);
        } catch (SecurityException x) {
            throw new ServiceConfigurationError(null, x);
        }
    }

    private static boolean loadPluginAsService() {
        ServiceLoader<Plugin> sl = ServiceLoader.load(Plugin.class, ClassLoader.getSystemClassLoader());
        Iterator<Plugin> i = sl.iterator();
        Plugin p;
        for (; ; ) {
            try {
                if (!i.hasNext())
                    return false;
                p = i.next();
                pluginMap.put(p.getClass().getSimpleName(), p);
                return true;
            } catch (ServiceConfigurationError sce) {
                if (sce.getCause() instanceof SecurityException) {
                    // Ignore the security exception, try the next provider
                    continue;
                }
                throw sce;
            }
        }
    }


//    public static class BeanDefinitionRegistrar implements ApplicationContextAware {
//
//        private static ApplicationContext applicationContext;
//
//        public static <T> T register(Class<T> tClass) {
//
//        }
//
//        @Override
//        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//            this.applicationContext = applicationContext;
//        }
//    }
}
