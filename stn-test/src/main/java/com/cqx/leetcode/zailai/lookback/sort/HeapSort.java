package com.cqx.leetcode.zailai.lookback.sort;

import java.util.PriorityQueue;

public class HeapSort {
    public static final void heapSort(int[] array) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < array.length; i++) {
            queue.offer(array[i]);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = queue.poll();
        }
    }

    public static void main(String[] args) {
        int[] unsort = new int[]{3, 1, 2, 3};
        HeapSort.heapSort(unsort);
        System.out.println(unsort);
    }
}
