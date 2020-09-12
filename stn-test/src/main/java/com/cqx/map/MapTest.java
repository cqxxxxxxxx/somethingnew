package com.cqx.map;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MapTest {


    public static void main(String[] args) {
        Random r  = new Random();
        Set<String> sets = new HashSet<>(100000);
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 100000; i++) {
            sets.add(i + "");
        }
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 100; i++) {
            boolean contains = sets.contains(r.nextInt());
            System.out.println(contains);
        }
        System.out.println(System.currentTimeMillis());
    }
}
