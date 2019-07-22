package com.cqx.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Java函数的参数是拷贝传递，只不过传递的是地址指针，按这个思路看看下面这个TEST
 * Created by Shan on 2017/2/10.
 */
public class Boom {

    class People {
        public String name;

        public People(String name) {
            this.name = name;
        }
    }

    @Test
    public void initialTest() {
        int[] a = new int[10];
        System.out.println(a[0]);
    }


    @Test
    public void dateTest() {
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println(System.currentTimeMillis());
        Date date1 = new Date(date.getTime());
        System.out.println(date1.getYear());
        Date date2 = new Date(System.currentTimeMillis());
        System.out.println(date2.getYear());
    }

    @Test
    public void test() {
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

    public void method1(int a) {
        a = 100;
    }

    public void method2(Integer b) {
        b = 100;
    }

    public void method3(String c) {
        c = "ccccc";
    }

    public void method4(People people) {
        people.name = "cqxnmb";
    }

    @Test
    public void kongge() {
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
        for (String s : strings) {
            System.out.println(s);
        }
    }

    @Test
    public void arrayCopy() {
        int[] a1 = {1, 2, 3, 4};
        int[] a2 = {5, 6, 7, 8};
        System.arraycopy(a1, 1, a2, 1, 2);
        System.out.println(1);
    }

    @Test
    public void calculate() {
        System.out.println(5/3);
        System.out.println(5%3);
    }

    @Test
    public void split() {
        String phoneStr = "";
        String old = "";
        String[] phones = phoneStr.split("\n");
        String[] oldPhones = old.split("\n");
        System.out.println("phones:" + phones.length + "---old:" + oldPhones.length);

        List<String> badDatas = Arrays.stream(phones).filter(x -> x.length() != 11).map(String::trim).collect(Collectors.toList());
        List<String> goodDatas = Arrays.stream(phones).filter(x -> x.length() == 11).map(String::trim).collect(Collectors.toList());
        List<String> oldDatas = Arrays.stream(oldPhones).filter(x -> x.length() == 11).map(String::trim).collect(Collectors.toList());
        String result = goodDatas.stream().map(x -> "\"" + x + "\"").collect(Collectors.joining(","));
        System.out.println("badDatas:" + badDatas.size() + "---goodDatas:" + goodDatas.size() + "---oldDatas:" + oldDatas.size());
        System.out.println(result);

//      打印出跟老数据重复的
        goodDatas.stream().filter(x -> oldDatas.contains(x)).forEach(System.out::println);

        //查找同一份数据里重复出现的
        List<String> aaa = goodDatas.stream().filter(x ->
            goodDatas.stream().filter(s -> s.equals(x)).count() > 1).collect(Collectors.toList());
        System.out.println(aaa);
    }



    @Test
    public void testInstanceOf() {
//        BigDecimal bigDecimal = null;
//        System.out.println(bigDecimal.toString());
//        long a = 0L;
//        Object b = a;
//        System.out.println(b instanceof Long);
//        int c = 0;
//        Object d = c;
//        System.out.println(d instanceof Integer);
//        Long.parseLong("0");
        Long l1 = Long.valueOf(0);
        System.out.println(l1.longValue() == 0L);
    }


    @Test
    public void condition() {
        if (true && false) {
            System.out.println("true && false");
        }
        if (false || true) {
            System.out.println("false || true");
        }
    }

    @Test
    public void foreachTest() {
        List<One> ones = new ArrayList<>();
        One one;
        for (int i = 0; i < 10; i++) {
            one = new One();
            one.name = i + "";
            ones.add(one);
        }
        ones.stream().forEach(System.out::print);
    }

    private class One {
        String name;

        @Override
        public String toString() {
            return name;
        }
    }
}
