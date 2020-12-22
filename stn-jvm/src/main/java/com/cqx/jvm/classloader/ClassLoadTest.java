package com.cqx.jvm.classloader;

import java.lang.reflect.InvocationTargetException;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/3/4
 */
public class ClassLoadTest {


    private String a;


    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Object o = new Object();
        String s = new String();
//        Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass("java.lang.Object");
        CqxClassLoader cqxClassLoader = new CqxClassLoader();
        Class c1 = cqxClassLoader.loadClass("com.cqx.jvm.NameO", false);
        Object o1 = c1.getDeclaredConstructor().newInstance();

        ClassLoader classLoader0 = ClassLoadTest.class.getClassLoader();
        Class c0 = Class.forName("com.cqx.jvm.NameO", false, classLoader0);
        Object o0 = c0.getDeclaredConstructor().newInstance();
        System.out.println(o0.getClass().getClassLoader());
        System.out.println(o1.getClass().getClassLoader());
    }
}
