package com.cqx.uboost;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/9
 */
public class TargetProxy<T> implements InvocationHandler {

    private final Map<Method, UBoostMethod> methodCache;
    private final Class<T> mapperInterface;

    public TargetProxy(Map<Method, UBoostMethod> methodCache, Class<T> mapperInterface) {
        this.methodCache = methodCache;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            try {
                return method.invoke(this, args);
            } catch (Throwable t) {
                throw new UBoostException(t);
            }
        }
        final UBoostMethod mapperMethod = methodCache.get(method);
        return mapperMethod.execute(proxy, args);
    }

}
