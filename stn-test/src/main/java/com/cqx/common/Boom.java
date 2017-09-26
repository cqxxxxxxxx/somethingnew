package com.cqx.common;

import org.junit.Test;

import java.util.Date;
import java.util.List;


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
    public void dateTest(){
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println(System.currentTimeMillis());
        Date date1 = new Date(date.getTime() );
        System.out.println(date1.getYear());
        Date date2 = new Date(System.currentTimeMillis());
        System.out.println(date2.getYear());
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

    @Test
    public void kongge(){
        char var1 = '\u0000';   //0
        char var2 = '\u0020';   //空格
        String var3 = "01   ";
        StringBuilder sb = new StringBuilder(var1).append(var2);
        String var4 = String.valueOf(var1) + String.valueOf(var2);
        System.out.println("var1:" + var1);
        System.out.println("var2:" + var2);
        System.out.println("var3:" + var3);
        System.out.println("var4:" + var4);
        System.out.println(var1 == ' ');
        System.out.println(var2 == ' ');
        System.out.println(var1 == 0);
    }


    @Test(expected = NullPointerException.class)
    public void testFor() {
        List<String> strings = null;
        for(String s : strings){
            System.out.println(s);
        }
    }
}
