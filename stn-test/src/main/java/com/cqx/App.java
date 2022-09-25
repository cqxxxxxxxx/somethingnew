package com.cqx;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5);
        List<Integer> list2 = Lists.newArrayList(2, 3, 4, 5, 6);
        List<Integer> merge = merge(list1, list2);
        for (Integer integer : merge) {
            System.out.println(integer);
        }
    }


    /**
     * @param list1
     * @param list2
     * @param <T>
     * @return
     */
    public static <T extends Comparable> List<T> merge(List<T> list1, List<T> list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        int len1 = list1.size();
        int len2 = list2.size();
        List<T> r = new ArrayList<>(len1 + len2);
        int a = 0, b = 0;
        T last = null;
        while (a < len1 && b < len2) {
            T t1 = list1.get(a);
            T t2 = list2.get(b);
            //t1 > t2
            if (t1.compareTo(t2) > 0) {
                if (last == null) {
                    r.add(t2);
                } else if (t2.compareTo(last) != 0) {
                    r.add(t2);
                }
                last = t2;
                b++;
            } else {
                if (last == null) {
                    r.add(t1);
                } else if (t1.compareTo(last) != 0) {
                    r.add(t1);
                }
                last = t1;
                a++;
            }
        }
        while (a < len1) {
            T t = list1.get(a);
            if (t.compareTo(last) != 0) {
                r.add(t);
            }
            a++;
        }
        while (b < list2.size()) {
            T t = list2.get(b);
            if (t.compareTo(last) != 0) {
                r.add(t);
            }
            b++;
        }
        return r;
    }


}
