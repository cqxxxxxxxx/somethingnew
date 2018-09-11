package com.cqx.jvm;

/**
 * Created by BG307435 on 2018/9/10.
 */
public class AllocationTest {
    private static final int _1MB = 1024 * 1024;


    /**
     * -verbose:gc -Xmx20M -Xmx20M -Xmn10M --XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testEdenAllocation() {
        byte[] a1, a2, a3, a4;
        a1 = new byte[2 * _1MB];
        a2 = new byte[2 * _1MB];
        a3 = new byte[2 * _1MB];

//      出现一次Minor GC
        a4 = new byte[4 * _1MB];
    }


    /**
     * -verbose:gc -Xmx20M -Xmx20M -Xmn10M --XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145278 (3MB)
     */
    public static void testPretenureSizeThreshold() {
//      直接进入 tenure generation
        byte[] a1 = new byte[4 * _1MB];
    }


    /**
     * -verbose:gc -Xmx20M -Xmx20M -Xmn10M --XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
     * -XX:+PrintTenuringDistribution
     */
    public static void testTenuringThreshold() {
        byte[] a1, a2, a3;
        a1 = new byte[_1MB / 4];
        a2 = new byte[_1MB * 4];
        a3 = new byte[_1MB * 4];
        a3 = null;
        a3 = new byte[_1MB * 4];
    }

    public static void main(String args[]) {
//        testEdenAllocation();
//        testPretenureSizeThreshold();
        testTenuringThreshold();
    }
}
