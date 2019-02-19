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

    HashSet hashSet = new HashSet();

    Thread thread = new Thread();

    InputStream io;

    public static void main(String[] args) throws IOException {

        InputStream inputStream = System.in;
        int b;
        for (; (b = inputStream.read()) != -1; ) {
            System.out.println(b);
        }
    }
}
