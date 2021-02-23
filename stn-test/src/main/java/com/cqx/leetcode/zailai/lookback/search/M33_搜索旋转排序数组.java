package com.cqx.leetcode.zailai.lookback.search;

/**
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 */
public class M33_搜索旋转排序数组 {

    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
//            if (nums[low] < )

        }

        return 1;
    }
}
