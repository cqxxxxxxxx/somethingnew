package com.cqx.compile.enumc;

public enum GenderType {
    GIRL("女"),
    BOY("男");


    private String desc;

    GenderType(String desc) {
        this.desc = desc;
    }

    public static void main(String[] args) {
        System.out.println(GenderType.BOY);
        System.out.println(GenderType.GIRL);
    }
}
