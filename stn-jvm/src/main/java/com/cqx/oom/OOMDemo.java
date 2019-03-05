package com.cqx.oom;

import java.nio.ByteBuffer;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/18
 */
public class OOMDemo {

    private static final int _1MB = 1024 * 1024;

//    WeakHashMap

    /**
     * -verbose:gc -Xmx20M -Xmx20M -Xmn10M -XX:+PrintGCDetails
     * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/Users/cqx/Projects/somethingnew/stn-jvm/src/main/java/com/cqx/oom/test1.hprof
     */
//    public static void main(String[] args) throws InterruptedException {
//        byte[] M20 = new byte[1 * _1MB];
//        new Thread(() -> {
//            byte[] M1 = new byte[10 * _1MB];
//        }).start();
//        TimeUnit.SECONDS.sleep(10);
//        System.out.println("im back");
//    }


//    public static void main(String[] args) throws InterruptedException {
//        new Thread(() -> {
//            throwError();
//            System.out.println("im ok");
//        }, "throw error thread").start();
//        TimeUnit.SECONDS.sleep(10);
//    }


//     -XX:MaxDirectMemorySize=1M,
    public static void main(String[] args) throws InterruptedException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(2 * _1MB);
        byteBuffer.wait();
    }


    public static void throwError() {
        throw new OutOfMemoryError();
    }
}
