package com.cqx.leetcode.zailai.lookback.array;

/**
 * 符合下列属性的数组 arr 称为 山脉数组 ：
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。
 *  
 * <p>
 * 示例 1：
 * 输入：arr = [0,1,0]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：arr = [0,2,1,0]
 * 输出：1
 * <p>
 * 链接：https://leetcode-cn.com/problems/peak-index-in-a-mountain-array
 */
public class E852_山脉数组的峰顶索引 {

    /**
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        int tmp = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= tmp) {
                tmp = arr[i];
            } else {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找 log(N)
     *
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray1(int[] arr) {
        int n = arr.length;
        int l = 1, r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (arr[mid - 1] < arr[mid]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

}
