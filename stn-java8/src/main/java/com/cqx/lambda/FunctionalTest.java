package com.cqx.lambda;

import org.junit.Test;

import java.util.function.Predicate;

/**
 * Created by Shan on 2017/2/2.
 */
public class FunctionalTest {

    /**
     * 用来测试自定义的函数式接口 {@link FunctionalDemo}
     * @param a
     * @param b
     */
    public void method1(Integer a, Integer b){
        //相当于生成个函数式接口的实现类，
        //就相当于Runnable runnable = () -> System.out.println("Runnable Lambda1");
        FunctionalDemo<Integer> demo =
                (a1, b1) -> {System.out.println(a1); return a1;};

        //调用实现类处理
        demo.getSmaller(a, b);
    }

    /**
     * 使用 jdk自带的 {@link Predicate}
     * 还有许多其他的 {@link java.util.function.Consumer} 等等
     * @param t
     * @param <T>
     */
    public <T> void method2(T t){
        Predicate<T> predicate =
                (t1) -> {
                    return t1 instanceof String;
                };

        System.out.println(predicate.test(t));
    }

    /**
     * 测试method1
     */
    @Test
    public void method1Test(){
        int a = 10, b = 20;
        method1(a, b);
    }

    /**
     * 测试method2
     */
    @Test
    public void method2Test(){
        int a = 10;
        String b = "cqx";
        method2(a); //false
        method2(b); //true
    }
}
