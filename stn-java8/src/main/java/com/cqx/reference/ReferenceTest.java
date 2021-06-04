package com.cqx.reference;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class ReferenceTest {

    public static void eg0() {
        Object o = new Object();
        // 默认的构造函数，会使用ReferenceQueue.NULL 作为queue
        WeakReference<Object> wr = new WeakReference<Object>(o);
        System.out.println(wr.get() == null);
        o = null;
        System.gc();
        System.out.println(wr.get() == null);
    }


    public static void eg1() {
        List<WeakHashMap<byte[][], byte[][]>> maps = new ArrayList<WeakHashMap<byte[][], byte[][]>>();
        for (int i = 0; i < 1000; i++) {
            WeakHashMap<byte[][], byte[][]> d = new WeakHashMap<byte[][], byte[][]>();
            d.put(new byte[1000][1000], new byte[1000][1000]);
            maps.add(d);
            System.gc();
            System.err.println(i);
        }
    }

    public static void eg2() {
//        DirectByteBuffer d = new DirectByteBuffer(1024);

    }


    public static void main(String[] args) {
//        eg0();
//        eg1();
        eg2();
    }
}
