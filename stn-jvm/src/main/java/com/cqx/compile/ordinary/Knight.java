package com.cqx.compile.ordinary;

/**
 * Created by BG307435 on 2017/9/5.
 */
public class Knight {

    private Integer result;

    public int add(int a, int b) {
        result = a + b;
        return result;
    }



    public static void main(String[] args) {
        Knight knight = new Knight();
        knight.say("cqx", "534");
    }

    public void say(String a, String b) {
        String c = "niubi";
        String d = a + b;
        System.out.println(d);
        System.out.println(c);
        System.out.println("hahaha");
    }
}
