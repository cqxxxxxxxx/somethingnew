package com.cqx.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by admin on 2017/3/16.
 */
public class Test {

    public static void main(String[] args){
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IAnimal dog = new Dog("大黄");
        InvocationHandler invocationHandler = new DogProxyInvocationHandler(dog);
        IAnimal animal = (IAnimal) Proxy.newProxyInstance(dog.getClass().getClassLoader(),
                dog.getClass().getInterfaces(),
                invocationHandler);
        animal.bark();
        animal.toString();
    }
}
