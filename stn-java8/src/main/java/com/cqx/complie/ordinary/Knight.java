package com.cqx.complie.ordinary;

/**
 * Created by BG307435 on 2017/9/5.
 */
public class Knight {

    private Integer result;

    public int add(int a, int b) {
        result = a + b;
        return result;
    }

    public void say() {
        System.out.println("hahaha");
    }

    public static void main(String[] args) {
        Knight knight = new Knight();
        knight.say();
    }
}
