package com.cqx.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by BG307435 on 2019/2/13.
 */
public class ListLearning {

    ArrayList arrayList;

    LinkedList linkedList;

    CopyOnWriteArrayList<String> copyOnWriteArrayList;

    public static void main0(String[] args) throws InterruptedException {

        try {
            throw new OutOfMemoryError("ss");
        } catch (OutOfMemoryError error) {
            System.out.println(error.getCause() + error.getMessage() + error.getStackTrace());
        }

        List<String> list = new ArrayList();
        list.add("aaaa");
        list.add("cccc");
        System.out.println(list.get(0));

        Thread thread = new Thread(() -> {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                list.add("sss");
            }
        });


        thread2.start();
        thread.start();
        TimeUnit.SECONDS.sleep(100);
        List list1 = new ArrayList(0);
        list1.add("aaaa");
        list1.add("cccc");
        System.out.println(list.get(0));


    }


    public static void main(String[] args) throws InterruptedException {
        String s1 = new String("计算机");
        String s2 = s1.intern();
        String s3 = "计算机";
        System.out.println(s2);//计算机
        System.out.println(s1 == s2);//false，因为一个是堆内存中的 String 对象一个是常量池中的 String 对象，
        System.out.println(s3 == s2);//true，因为两个都是常量池中的 String 对象

        List<String> list = new ArrayList();
        list.add("aaaa");
        list.add("bbbb");
        list.add("cccc");
        for (String s : list) {
            if ("aaaa".equals(s)) {
                list.remove(s);
            }
        }
    }
}
