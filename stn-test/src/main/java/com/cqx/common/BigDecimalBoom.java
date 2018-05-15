package com.cqx.common;


import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Shan on 2017/2/9.
 */
public class BigDecimalBoom {

    /**
     * BigDecimal的加减乘除都是运算好后返回一个新的BigDecimal 原来的对象值不变
     */
    @Test
    public void caculateTest(){
        BigDecimal param1 = BigDecimal.ZERO;
        BigDecimal result = param1.add(BigDecimal.TEN);
        System.out.println(result);


        BigDecimal param2 = BigDecimal.ZERO;
        param2 = param2.add(BigDecimal.ONE);
        System.out.println(param2);
    }

    @Test
    public void integerTest(){
//        Integer i = null;
//        Assert.assertTrue(i < 1);
        System.out.println(BigDecimal.ZERO.compareTo(null));
        System.out.println(BigDecimal.valueOf(1.02).multiply(BigDecimal.valueOf(100)));
        System.out.println(BigDecimal.valueOf(1.02).multiply(BigDecimal.valueOf(100)).longValue());

    }

    @Test
    public void strTest() {
        String a = "cqx1111";
        String b = a.replace("cqx", "aaaaaaaaaa");
        System.out.println(a);
        System.out.println(b);
        StringBuilder sb = new StringBuilder();
        sb.append("\\").append("aaa").append("\\*");
        System.out.println(sb.toString());
    }

    @Test
    public void kexuejishufa(){
        BigDecimal bd = new BigDecimal("0.0000000000");
        System.out.println(bd);
        System.out.println(bd.toPlainString()); //采用非科学计数法

        BigDecimal bd1 = null;
        System.out.println(bd1);
//        System.out.println(bd1.toPlainString());  //NullPointerException
    }


    @Test
    public void testEqual() {
        BigDecimal a = new BigDecimal("10");
        BigDecimal b = new BigDecimal("10.00");
        System.out.println(a.compareTo(b));
        System.out.println(a.equals(b));
    }
}
