package com.cqx.complie.innerClass;

public class OutClass {
    private String a = "231";

    private void add() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(a);
                System.out.println(1123);
            }
        };
        System.out.println("1+1=2");
    }
}
