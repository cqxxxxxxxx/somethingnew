package com.cqx.map;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by BG307435 on 2018/7/26.
 */
public class MapLearning {


    Map concurrentHashMap = new ConcurrentHashMap();

    Map hashTable = new Hashtable();

    Map hashMap = new HashMap();

    Map linkedHashMap = new LinkedHashMap();

    Map treeMap = new TreeMap();

    Map weakHashMap = new WeakHashMap();

    HashSet hashSet = new HashSet();

    Thread thread = new Thread();

    InputStream io;

    Collections collections;

    public static void main(String[] args) {
        Map<String, String> map0 = new HashMap<>();
        map0.put("1", "2");

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        map.put("First", 10);
        map.put("Second", 20);
        map.put("Third", 30);
        map.put("Fourth", 40);

        Iterator<String> iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            map.put("Fifth", 50);
            System.out.println(key);
        }
    }

    public static void main0(String[] args) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            map.put("4", "4");
            System.out.println(entry.getKey());
        }


        InputStream inputStream = System.in;
        int b;
        for (; (b = inputStream.read()) != -1; ) {
            System.out.println(b);
        }
    }
}
