package com.cqx.compile.ordinary;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by BG307435 on 2017/9/5.
 */
public class Knight {

    private static final String name = "cqx";
    private static final List<Integer> girls = new ArrayList<>();

    static {
        System.out.println("aaaa");
    }

    private int id;

    private List<String> list = new ArrayList();

    public Knight() {

    }

    public Knight(int id) {
        this.id = id;
    }

    private Integer result;

    public int add(int a, int b) {
        result = a + b;
        return result;
    }

    public static int calc(int a) {
        return a * a * 3 - 1 & 231;
    }

    public void array() {
        int[] t = new int[2000];
        for (int i = 0; i < 2000; i++) {
            t[i] = i;
        }
        Arrays.stream(t).forEach(x -> {
            calc(x);
            System.out.println(Thread.currentThread());
        });
    }

    public void invoke() {
        Knight knight = new Knight();
        knight.say("cqx", "534");
    }


    public void string() {
        String a = "abc";
        String b = new String("abc");
        String c = new String("abc");
        String d = new String("abc").intern();
        System.out.println(a.hashCode() + "|" + System.identityHashCode(a));
        System.out.println(b.hashCode() + "|" + System.identityHashCode(b));
        System.out.println(c.hashCode() + "|" + System.identityHashCode(c));
        System.out.println(d.hashCode() + "|" + System.identityHashCode(d));
    }

    public void say(String a, String b) {
        String c = "niubi";
        String d = a + b;
        System.out.println(d);
        System.out.println(c);
        System.out.println("hahaha");
        String e = c + "c" + "d";
    }

    public static void main(String[] args) {
        new Knight(1);
        new Knight().string();
    }

}
