package com.cqx.sort;

import java.util.Random;

/**
 * 单线程的归并排序
 * 拆分阶段，将序列分为更小的序列
 * 排序阶段，把小的序列合在一起（使用合并算法）来构成更大的序列
 * Created by BG307435 on 2017/9/29.
 */
public class MergeSort<T extends Comparable<T>> {

    /**
     * IN_PLACE（原地算法） 直接修改输入的序列而不是创建新的序列，节省内存空间
     * NOT_IN_PLACE 创建一个新的序列作为有序的序列
     */
    private enum SPACE_TYPE {
        IN_PLACE, NOT_IN_PLACE
    }

    public static <T extends Comparable<T>> T[] sort(SPACE_TYPE type, T[] unsorted) {
        sort(type, 0, unsorted.length, unsorted);
        return unsorted;
    }

    private static <T extends Comparable<T>> void sort(SPACE_TYPE type, int start, int length, T[] unsorted) {
        if (length > 2) {
            int aLength = (int) Math.floor(length / 2);
            int bLength = length - aLength;

            //拆分为2个数组
            sort(type, start, aLength, unsorted);
            sort(type, start + aLength, bLength, unsorted);

            //有序数组合并操作
            if (type == SPACE_TYPE.IN_PLACE) {
                mergeInPlace(start, aLength, start + aLength, bLength, unsorted);
            }
            if (type == SPACE_TYPE.NOT_IN_PLACE) {
                mergeWithExtraStorage(start, aLength, start + aLength, bLength, unsorted);
            }
        } else if (length == 2) {
            //如果长度为2则对这两个进行排序
            if (unsorted[start].compareTo(unsorted[start + 1]) > 0) {
                //前者比后者大 交换位置
                T e = unsorted[start + 1];
                unsorted[start + 1] = unsorted[start];
                unsorted[start] = e;
            }
        } else if (length == 1) {
            //长度为1 则不处理 默认有序
        }
    }

    /**
     * 在同一个数组里进行两个有序数组的合并操作,不浪费内存资源
     */
    public static <T extends Comparable<T>> void mergeInPlace(int aStart, int aLength, int bStart, int bLength, T[] unsorted) {
        int i = aStart;
        int j = bStart;
        int aSize = aStart + aLength;   //a最大下标
        int bSize = bStart + bLength;   //b最大下标
        while (i < aSize && j < bSize) {
            T a = unsorted[i];
            T b = unsorted[j];
            if (a.compareTo(b) > 0) {
                //a数组的第一个比b的第一个大
                System.arraycopy(unsorted, i, unsorted, i + 1, j - i);
                unsorted[i] = b;
                i++;
                j++;
                aSize++;
            } else {
                i++;
            }
        }
    }

    /**
     * 创建个临时数组来有序存储内容，效率可能高一点，但是浪费内存
     */
    public static <T extends Comparable<T>> void mergeWithExtraStorage(int aStart, int aLength, int bStart, int bLength, T[] unsorted) {
        int count = 0;
        T[] output = (T[]) new Comparable[aLength + bLength];
        int i = aStart;
        int j = bStart;
        int aSize = aStart + aLength;
        int bSize = bStart + bLength;
        while (i < aSize || j < bSize) {
            T a = null;
            if (i < aSize) {
                a = unsorted[i];
            }
            T b = null;
            if (j < bSize) {
                b = unsorted[j];
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
        int x = 0;
        int size = aStart + aLength + bLength;
        for (int y = aStart; y < size; y++) {
            unsorted[y] = output[x++];
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
        Integer[] result = sort(SPACE_TYPE.IN_PLACE, unsorted);
        System.out.println(check(result));
    }
}
