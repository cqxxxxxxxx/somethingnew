package com.cqx.leetcode.zailai.lookback.sort;

public class QuickSort {

    public static void quickSort(int[] array, int begin, int end) {
        if (end <= begin) {
            return;
        }
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    private static int partition(int[] array, int begin, int end) {
        int pivot = begin;
        for (int i = begin + 1; i < end; i++) {
            if (array[i] < array[pivot]) {
                int tmp = array[pivot];
                array[pivot] = array[i];
                array[i] = array[pivot];
                pivot = i;
            }
        }
        return pivot;
    }
}
