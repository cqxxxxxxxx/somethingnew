package com.cqx.leetcode.zailai.lookback.sort;

/**
 * 归并排序
 */
public class MergeSort {

    public static final void mergeSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) >> 1;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    /**
     * 开辟空间帮助排序
     * @param array
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] array, int left, int mid, int right) {
        int[] tmp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            tmp[k++] = array[i] < array[j] ? array[i++] : array[j++];
        }
        while (i <= mid) {
            tmp[k++] = array[i++];
        }
        while (j <= right) {
            tmp[k++] = array[j++];
        }
        for (int z = 0; z < tmp.length; z++) {
            array[left++] = tmp[z];
        }
    }

    public static void main(String[] args) {
        int[] unsort = new int[]{3, 1, 2, 3};
        MergeSort.mergeSort(unsort, 0, unsort.length - 1);
        System.out.println(unsort);
    }


}
