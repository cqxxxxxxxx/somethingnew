package com.cqx.map;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * https://blog.csdn.net/guangcigeyun/article/details/8278349
 */
public class SkipListMapLearning {

    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, Integer> map = new ConcurrentSkipListMap<>();
        for (int i = 0; i < 30; i++) {
            map.put(i, i);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
