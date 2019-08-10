package com.cqx.stncqxhat.plugin.aop;

import com.cqx.stncqxhat.plugin.Plugin;

import java.lang.reflect.Proxy;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/9
 */
public class PluginProxy {
    /**
     * 获取代理类
     * @param plugin
     * @return
     */
    public static Plugin getProxy(Plugin plugin) {
        PluginInvocationHandler invocationHandler = new PluginInvocationHandler(plugin);
        Plugin proxyInstance = (Plugin) Proxy.newProxyInstance(plugin.getClass().getClassLoader(),
                plugin.getClass().getSuperclass().getInterfaces(),
                invocationHandler);
        return proxyInstance;
    }
}
