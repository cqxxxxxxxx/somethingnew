package com.cqx.common;


import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    @Test
    public void divideTest() {
        System.out.println(BigDecimal.valueOf(133).divide(BigDecimal.valueOf(155), 2, BigDecimal.ROUND_CEILING));
    }


    @Test
    public void growthTest() {
        double re = MathUtil.growthCalc(2d, 3d);
        System.out.println(re);
    }

    @Test
    public void doubleMax() {
        System.out.println(Double.MAX_VALUE > 86536565500.55d);
    }

    @Test
    public void sortTest() {
        List<String> list = new ArrayList<>();
        list.add("cqx");
        list.add("cqx1");
        list.add("cqx2");
        list.stream().forEach(System.out::println);
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        };
        Collections.sort(list, comparator);
        list.stream().forEach(System.out::println);

    }

    private static class MathUtil {
        public static double round(Double d, int round) {
            assert d != null;
            assert round >= 0;
            BigDecimal decimal = BigDecimal.valueOf(d);
            decimal.setScale(round, BigDecimal.ROUND_HALF_UP);
            return decimal.doubleValue();
        }

        public static double add(Double d0, Double d1) {
            assert (d0 != null && d1 != null);
            BigDecimal result = BigDecimal.valueOf(d0).add(BigDecimal.valueOf(d1));
            return result.doubleValue();
        }

        public static double multiply(Double d, int i) {
            assert d != null;
            BigDecimal decimal = BigDecimal.valueOf(d);
            return decimal.multiply(BigDecimal.valueOf(i)).doubleValue();
        }

        public static double sub(Double d0, Double d1) {
            assert (d0 != null && d1 != null);
            BigDecimal result = BigDecimal.valueOf(d0).subtract(BigDecimal.valueOf(d1));
            return result.doubleValue();
        }

        /**
         * (d0 - d1)/d1
         *
         * @param d0
         * @param d1
         * @param roundingMode
         * @return
         */
        public static double divide(Double d0, Double d1, int scale, int roundingMode) {
            assert (d0 != null && d1 != null);
            BigDecimal decimal = BigDecimal.valueOf(d0);
            BigDecimal divisor = BigDecimal.valueOf(d1);
            if (divisor.compareTo(BigDecimal.ZERO) == 0) {
                return -999d;
            }
            return decimal.divide(divisor, scale, roundingMode).doubleValue();
        }

        public static double growthCalc(Double d0, Double d1) {
            assert (d0 != null && d1 != null);
            BigDecimal decimal0 = BigDecimal.valueOf(d0);
            BigDecimal decimal1 = BigDecimal.valueOf(d1);
            if (decimal1.compareTo(BigDecimal.ZERO) == 0) {
                return -999d;
            }
            return divide(decimal0.subtract(decimal1).doubleValue(), d1, 4, BigDecimal.ROUND_CEILING);
        }

    }
}
