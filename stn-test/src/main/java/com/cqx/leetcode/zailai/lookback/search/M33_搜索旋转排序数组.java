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
            if (nums[mid] == target) {
                return mid;
            }
            //左边递增
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && nums[mid] >= target) {
                    //在左边找
                    high = mid - 1;
                } else {
                    //在右边找
                    low = mid + 1;
                }
            } else {
                //右边递增
                if (nums[mid] <= target && nums[high] >= target) {
                    //在左边找
                    low = mid + 1;
                } else {
                    //在右边找
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
