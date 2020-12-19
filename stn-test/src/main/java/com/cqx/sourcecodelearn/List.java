package com.cqx.sourcecodelearn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;

public class List {

    public static void main(String[] args) {
        /**
         * 1.5倍扩容
         * 底层数组
         */
        new ArrayList<>();

        /**
         * 双端队列
         * 链表结构
         */
        new LinkedList<>();

        /**
         * 底层数组
         * 写操作时候进行复制
         * 迭代时候利用快照
         * 有个重入锁用于保证写数据时候的线程安全问题
         */
        new CopyOnWriteArrayList<>();
    }
}
