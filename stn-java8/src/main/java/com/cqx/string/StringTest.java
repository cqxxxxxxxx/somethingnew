package com.cqx.string;

import java.nio.charset.Charset;

public class StringTest {
    public static void main(String[] args) {
        String a = "a"; //1个字节
        String b = "中";//3
        String c = "1";//1
        String d = "a中1";//5
        String e = "aa11";//4
        System.out.println(a.getBytes().length);
        System.out.println(b.getBytes().length);
        System.out.println(c.getBytes().length);
        System.out.println(d.getBytes().length);
        System.out.println(e.getBytes().length);
        System.out.println(Charset.defaultCharset()); //UTF-8
    }
}
