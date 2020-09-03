package com.cqx.leetcode.zailai.lookback.array;

public class 合并两个有序数组 {
    //给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
//
//
//
// 说明:
//
//
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
//
//
//
//
// 示例:
//
// 输入:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//输出: [1,2,2,3,5,6]
// Related Topics 数组 双指针
// 👍 604 👎 0


    class Solution {
        /**
         * 从后往前遍历
         *
         * @param nums1
         * @param m
         * @param nums2
         * @param n
         */
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int current = m + n - 1;
            while (current >= 0) {
                if (n == 0) {
                    return;
                } else if (m == 0) {
                    nums1[current] = nums2[n - 1];
                    n--;
                } else {
                    int val0 = nums1[m - 1];
                    int val1 = nums2[n - 1];
                    if (val1 > val0) {
                        nums1[current] = val1;
                        n--;
                    } else {
                        nums1[current] = val0;
                        m--;
                    }
                }
                current--;
            }
        }
    }

}
