package com.cqx.sort;

import java.util.Random;

/**
 * Created by BG307435 on 2017/9/30.
 */
public class MergeSortUtil<T extends Comparable> {

    public T[] sort(T[] array) {
        sort(array, 0, array.length);
        return array;
    }


    private void sort(T[] array, int start, int length) {
        if (length > 2) {
            int mid = start + length / 2;
            int aLength = (int) Math.floor(length / 2);
            int bLength = length - aLength;
            sort(array, start, aLength);
            sort(array, mid, bLength);
            mergeInPlace(array, start, aLength, mid, bLength);
        } else if (length == 2) {
            if (array[start].compareTo(array[start + 1]) > 0) {
                T a = array[start];
                array[start] = array[start + 1];
                array[start + 1] = a;
            }
        } else if (length == 1) {

        }
    }

    public Integer[] mergeWithExtraStorage(Integer[] array1, Integer[] array2) {
        int count = 0;
        int i = 0;
        int j = 0;
        int aSize = array1.length;
        int bSize = array2.length;
        Integer[] output =  new Integer[aSize + bSize];
        while (i < aSize || j < bSize) {
            Integer a = null;
            if (i < aSize) {
                a = array1[i];
            }
            Integer b = null;
            if (j < bSize) {
                b = array2[j];
            }
            if (a != null && b == null) {
                output[count++] = a;
                i++;
            } else if (b != null && a == null) {
                output[count++] = b;
                j++;
            } else if (b != null && b.compareTo(a) <= 0) {
                output[count++] = b;
                j++;
            } else {
                output[count++] = a;
                i++;
            }
        }
        return output;
    }


    public void mergeInPlace(T[] array, int aStart, int aLength, int bStart, int bLength) {
        int i = aStart;
        int j = bStart;
        int aSize = aStart + aLength;   //a最大下标
        int bSize = bStart + bLength;   //b最大下标
        while (i < aSize && j < bSize) {
            T a = array[i];
            T b = array[j];
            if (a.compareTo(b) > 0) {
                //前者比后者大
                System.arraycopy(array, i, array, i + 1, j - i);
                array[i] = b;
                i++;
                j++;
                aSize++;
            } else {
                i++;
            }
        }
    }

    private static final boolean check(Integer[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] unsorted = new Integer[10000];
        Random random = new Random();
        int i = 0;
        while (i < unsorted.length) {
            int j = random.nextInt(unsorted.length * 10);
            unsorted[i++] = j;
        }
        MergeSortUtil mergeSortUtil = new MergeSortUtil();
        Integer[] result = (Integer[]) mergeSortUtil.sort(unsorted);
        System.out.println(check(result));
    }
}
