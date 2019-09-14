package com.cqx.skeleton.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * fox代理类的工厂类
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/10
 */
public class FoxProxyFactory<T> {

    /**
     * 需要代理的接口
     * 类比于mybatis的mapper接口
     */
    private final Class<T> foxInterface;

    /**
     * 对象方法的缓存
     */
    private final Map<Method, FoxMethod> methodCache = new ConcurrentHashMap<Method, FoxMethod>();

    public FoxProxyFactory(Class<T> foxInterface) {
        this.foxInterface = foxInterface;
    }

    public Class<T> getFoxInterface() {
        return foxInterface;
    }

    public Map<Method, FoxMethod> getMethodCache() {
        return methodCache;
    }

    @SuppressWarnings("unchecked")
    protected T newInstance(FoxProxy<T> foxProxy) {
        return (T) Proxy.newProxyInstance(foxInterface.getClassLoader(), new Class[]{foxInterface}, foxProxy);
    }

    /**
     * 基于jdk动态代理创建一个代理对象
     *
     * @param name
     * @return
     */
    public T newInstance(String name) {
        final FoxProxy<T> foxProxy = new FoxProxy<>(name, foxInterface, methodCache, foxNotifier);
        return newInstance(foxProxy);
    }
}
