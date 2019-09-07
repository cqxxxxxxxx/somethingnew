package com.cqx.compile.ordinary;


import java.util.Arrays;

/**
 * Created by BG307435 on 2017/9/5.
 */
public class Knight {

    private Integer result;

    public int add(int a, int b) {
        result = a + b;
        return result;
    }

    public static int calc(int a) {
        return a * a * 3 - 1 & 231;
    }

    public static void main(String[] args) {
//        JStack j = new JStack();
//        j.run();
        int[] t = new int[2000];
        for (int i = 0; i < 2000; i++) {
            t[i] = i;
        }
        Arrays.stream(t).forEach(x -> {
            calc(x);
            System.out.println(Thread.currentThread());
        });

        Knight knight = new Knight();
        knight.say("cqx", "534");


        String s1 = "cqx";
        String s2 = new String("我无敌cqx");
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s2);


    }

    public void say(String a, String b) {
        String c = "niubi";
        String d = a + b;
        System.out.println(d);
        System.out.println(c);
        System.out.println("hahaha");
        String e = c + "c" + "d";
    }
}
