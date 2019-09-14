package com.cqx.compile.syntax_sugar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BG307435 on 2018/11/14.
 */
public class SugarDemo {

    public void genericSugar() {
        List<Integer> integerList = new ArrayList<>();
        for (Integer integer : integerList) {
            System.out.println(integer);
        }
    }

    public void intSugar() {
        Integer a = 1;
        Integer b = 233;
        Integer c = a + b;
        System.out.println(a == 1);
    }

    public void stringSugar() {
        String a = "aaaaaaaaxxxxxxxxxx";
        String b = "vvvvv" + "cccccc";
        String c = a + b;
    }

    public static void main(String[] args) {
        String a = "cqx";
        String b = "nb";
        String c = a + b;
        String d = "cqx" + "nb";
        String e = new String("cqxnb");
        System.out.println(c == d);
        System.out.println(c == "cqxnb");
        System.out.println(d == "cqxnb");
        System.out.println(e == "cqxnb");
    }
}
