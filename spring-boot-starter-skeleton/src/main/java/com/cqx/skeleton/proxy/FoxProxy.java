package com.cqx.skeleton.proxy;

import com.cqx.skeleton.FoxException;
import com.cqx.skeleton.configurable.listener.FoxNotifier;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Observable;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/11
 */
public class FoxProxy<T> extends Observable implements InvocationHandler {
    private final String name;
    private final Class<T> forInterface;
    private final Map<Method, FoxMethod> methodCache;
    private final FoxNotifier foxNotifier;

    public FoxProxy(String name, Class<T> foxInterface, Map<Method, FoxMethod> methodCache, FoxNotifier foxNotifier) {
        this.name = name;
        this.forInterface = foxInterface;
        this.methodCache = methodCache;
        this.foxNotifier = foxNotifier;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            try {
                return method.invoke(this, args);
            } catch (Throwable t) {
                throw new FoxException(t);
            }
        }
        final FoxMethod foxMethod = cachedFoxMethod(method);
        return foxMethod.execute(name, args);
    }


    private FoxMethod cachedFoxMethod(Method method) {
        FoxMethod foxMethod = methodCache.get(method);
        if (foxMethod == null) {
            foxMethod = new FoxMethod(forInterface, method, name);
            methodCache.put(method, foxMethod);
        }
        return foxMethod;
    }
}
