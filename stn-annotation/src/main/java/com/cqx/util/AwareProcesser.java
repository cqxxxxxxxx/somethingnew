package com.cqx.util;

import com.cqx.callback.Aware;
import com.cqx.factory.BeanFactoryImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by Shan on 2017/1/20.
 */
public class AwareProcesser {
    private BeanFactoryImpl beanFactory = new BeanFactoryImpl();

    /**
     * 某个接口继承了Aware接口，并且该接口被bean实现了
     * 则会在实例化的时候进行调用接口中的方法。
     * 作用类似Spring的Aware
     *
     * @param clazz
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void awareProcess(Class clazz) throws InvocationTargetException, IllegalAccessException {
        String name = defaultName(clazz.getSimpleName());
        Object object = beanFactory.getBean(name);
        Class[] clazzes = clazz.getInterfaces();

        for (Class clazz1 : clazzes) {
            boolean callable = false;
            Class[] superclazzes = clazz1.getInterfaces();
            for (Class superclass : superclazzes) {
                if (superclass == Aware.class)
                    callable = true;    //如果该接口继承了Aware接口，则自动调用该接口的所有方法
            }
            if (callable) {
                Method[] methods = clazz1.getMethods();
                for (Method method : methods) {
                    method.invoke(object);
                }
            }
        }

    }

    public String defaultName(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }
}
