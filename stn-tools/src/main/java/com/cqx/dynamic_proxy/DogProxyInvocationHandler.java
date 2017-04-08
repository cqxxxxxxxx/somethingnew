package com.cqx.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by admin on 2017/3/16.
 */
public class DogProxyInvocationHandler implements InvocationHandler {

    private Object animal;

    public DogProxyInvocationHandler(Object animal){
        this.animal = animal;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy class:" + proxy.getClass() + ",class:" + getClass() + ",method" + method);
        Object obj = method.invoke(animal, args);
        System.out.println("obj:" + obj);
        return obj;
    }
}
