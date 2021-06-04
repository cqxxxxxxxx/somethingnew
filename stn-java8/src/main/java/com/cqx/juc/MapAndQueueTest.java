package com.cqx.juc;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/6
 */
public class MapAndQueueTest {

    @Test
    public void ConcurrentHashMap() throws InterruptedException {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                map.put(i, i);
            }
        }).start();
        TimeUnit.MILLISECONDS.sleep(1);
        for (Integer str : map.values()) {
            System.out.println(str);
        }
    }
}
