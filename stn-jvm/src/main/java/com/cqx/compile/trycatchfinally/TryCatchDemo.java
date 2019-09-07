package com.cqx.compile.trycatchfinally;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/3/17
 */
public class TryCatchDemo {

    public int print() {
        int a = 1;
        try {
            a = 2 / 0;
        } catch (Exception e) {
            a = 3;
        } finally {
            a = 4;
        }
        return a;
    }

    public int print2() {
        int a;
        try {
            a = 1;
            return a;
        } catch (Exception e) {
            a = 2;
            return a;
        } finally {
            a = 3;
        }
    }

    public static void main(String[] args) {
        TryCatchDemo a = new TryCatchDemo();
        System.out.println(a.print());
        System.out.println(a.print2());
    }
}
