package com.cqx.leetcode.zailai.lookback.array;

import java.util.HashSet;
import java.util.Set;

public class 两个数组的交集 {
    //给定两个数组，编写一个函数来计算它们的交集。
//
//
//
// 示例 1：
//
// 输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
//
//
// 示例 2：
//
// 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4]
//
//
//
// 说明：
//
//
// 输出结果中的每个元素一定是唯一的。
// 我们可以不考虑输出结果的顺序。
//
// Related Topics 排序 哈希表 双指针 二分查找
// 👍 224 👎 0


    class Solution {

        /**
         * TODO 其他思路
         * 1. 排序后优化查找
         * 2. 排序后二分查找
         * 3. 排序后双指针查找
         *
         * @param nums1
         * @param nums2
         * @return
         */
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set = new HashSet<>();
            Set<Integer> setR = new HashSet<>();
            for (int i = 0; i < nums1.length; i++) {
                set.add(nums1[i]);
            }
            for (int i = 0; i < nums2.length; i++) {
                if (set.contains(nums2[i])) {
                    setR.add(nums2[i]);
                }
            }
            int[] r = new int[setR.size()];
            int index = 0;
            for (Integer integer : setR) {
                r[index] = integer;
                index++;
            }
            return r;
        }
    }

}
