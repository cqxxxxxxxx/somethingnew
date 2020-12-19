package com.cqx.sourcecodelearn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Map {

    public static void main(String[] args) {
        new HashMap<>();
        new HashSet<>();
        new Hashtable<>();
        new LinkedHashMap<>();

        /**
         * fail-safe
         */
        new ConcurrentHashMap<>();
        new ConcurrentSkipListMap<>();
    }
}
