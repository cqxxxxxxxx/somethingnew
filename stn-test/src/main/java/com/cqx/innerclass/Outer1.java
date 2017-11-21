package com.cqx.innerclass;

/**
 * 静态内部类
 * Created by BG307435 on 2017/11/17.
 */
public class Outer1 {

    private String name;
    private static String des;

    public static class Inner1{
        Inner1(){}

        void cqx() {
            Outer1 outer1 = new Outer1();
            System.out.println(outer1.name);
            System.out.println(des);
        }

        static void say() {
            System.out.println(des);
        }
    }
}
