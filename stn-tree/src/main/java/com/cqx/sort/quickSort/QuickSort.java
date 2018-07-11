package com.cqx.sort.quickSort;

import java.util.Arrays;

/**
 * Created by cqx on 2018/6/9.
 */
public class QuickSort {

    public void sort(int[] array, int left, int right) {
        int ref = array[left];
        int uLeft = left + 1;
        int uRight = right;
        while (uLeft != uRight) {
            while (array[uRight] > ref && uRight > uLeft) {
                uRight--;
            }
            while (array[uLeft] < ref && uRight > uLeft) {
                uLeft++;
            }

            if (uLeft < uRight) {
                int tmpLeft = array[uLeft];
                array[uLeft] = array[uRight];
                array[uRight] = tmpLeft;
            }
        }
        array[left] = array[uLeft];
        array[uLeft] = ref;
        sort(array, left, uLeft - 1);
        sort(array, uLeft + 1, right);
    }


    public static void main(String[] args) {
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1, 8 };
        QuickSort quickSort = new QuickSort();
        quickSort.sort(a, 0, a.length - 1);
        Arrays.stream(a).forEach(System.out::println);
    }
}
