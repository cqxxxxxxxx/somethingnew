package com.cqx.innerclass;

/**
 * 非静态内部类
 * Created by BG307435 on 2017/11/17.
 */
public class Outer2 {

    private String name;
    private static String des;

    public Inner2 getInner2() {
        return new Inner2();
    }

//    private之类修饰符 可以限制内部类在其他地方的可见性
    public class Inner2{
        Inner2(){}

        void cqx() {
            System.out.println(Outer2.this.name);   //可以直接访问外围实例对象的值
            System.out.println(des);
        }

//        static void say() {
//            System.out.println(des);
//        }
    }
}
