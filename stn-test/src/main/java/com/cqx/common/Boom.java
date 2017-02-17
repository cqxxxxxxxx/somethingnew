package com.cqx.common;

import org.junit.Test;


/**
 * Java函数的参数是拷贝传递，只不过传递的是地址指针，按这个思路看看下面这个TEST
 * Created by Shan on 2017/2/10.
 */
public class Boom {

    class People{
        public String name;

        public People(String name){
            this.name = name;
        }
    }


    @Test
    public void test(){
        int a = 10;
        method1(a);
        System.out.println(a);

        Integer b = 10;
        method2(b);
        System.out.println(b);

//        System.exit(0);       //退出

        String c = "c";
        method3(c);
        System.out.println(c);

        People people = new People("cqx");
        method4(people);
        System.out.println(people.name);


    }

    public void method1(int a){
        a = 100;
    }

    public void method2(Integer b){
        b = 100;
    }

    public void method3(String c){
        c = "ccccc";
    }

    public void method4(People people){
        people.name = "cqxnmb";
    }
}
