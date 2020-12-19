package com.cqx.sourcecodelearn;

import sun.misc.Cleaner;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.WeakHashMap;

public class Reference {

    public static void main(String[] args) {
        /**
         *  * Reference queues, to which registered reference objects are appended by the
         *  * garbage collector after the appropriate reachability changes are detected.
         */
        final ReferenceQueue<Object> objectReferenceQueue = new ReferenceQueue<>();


        Object strongRef = new Object();

        /**
         * 一般缓存用
         * 内存不够时 回收
         */
        SoftReference<Object> softRef = new SoftReference<>(new Object());

        /**
         * GC时候就回收
         * weakHashMap
         */
        WeakReference<Object> weakRef = new WeakReference<>(new Object());
        /**
         * key是weakReference
         * expungeStaleEntry 抹去过期的条目
         */
        WeakHashMap weakHashMap = new WeakHashMap(16);
        ThreadLocal threadLocal = new ThreadLocal();

        /**
         *
         */
        PhantomReference<Object> phantomRef = new PhantomReference<>(new Object(), new ReferenceQueue<>());
        /**
         * 直接内存的清理需要用cleaner来处理， 因为jvm的GC无法触及这部分
         * 所以定义一个cleaner，当gc时候发现byteBuffer没有强引用时，就通过referenceHandler来执行cleaner的回收方法进行内存回收。
         */
        final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        Cleaner.create(new Object(), () -> System.out.println("object cleaned"));

    }

}
