package com.cqx.stncqxhat.plugin.aop;

import com.cqx.Meta;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/9
 */
@Slf4j
public class PluginInvocationHandler implements InvocationHandler {
    private static String TARGET_METHOD = "metadata";
    private final Object plugin;
    private final Meta meta;
    private final Meta.Info info;

    /**
     * 初始化一些插件信息
     * @param plugin
     */
    public PluginInvocationHandler(Object plugin){
        this.plugin = plugin;
        this.meta = plugin.getClass().getAnnotation(Meta.class);
        Meta.Info info = new Meta.Info();
        info.setAuthor(meta.author());
        info.setMode(meta.mode());
        info.setPluginName(meta.pluginName());
        info.setVersion(meta.version());
        this.info = info;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.debug("proxy class:" + proxy.getClass() + ",class:" + getClass() + ",method" + method);
        Object obj = null;
        //如果是metadata方法则做代理
        if (TARGET_METHOD.equals(method.getName())) {
            obj = info;
        } else {
            obj = method.invoke(plugin, args);
        }
        log.debug("obj:" + obj);
        return obj;
    }
}
