package com.cqx.leetcode.zailai.lookback.sort;

public class QuickSort {
    public static void main(String[] args) {
//        int[] vals = {1, 2, 0};
        int[] vals = new int[]{2, 0, 2, 1, 1, 0};

        quickSort(vals, 0, vals.length - 1);
    }

    /**
     * @param array
     * @param begin
     * @param end
     */
    public static void quickSort(int[] array, int begin, int end) {
        if (end <= begin) {
            return;
        }
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    private static int partition(int[] array, int begin, int end) {
        int pivot = end;
        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (array[i] < array[pivot]) {
                int tmp = array[counter];
                array[counter] = array[i];
                array[i] = tmp;
                counter++;
            }
        }
        int tmp = array[counter];
        array[counter] = array[pivot];
        array[pivot] = tmp;
        return counter;
    }
}
