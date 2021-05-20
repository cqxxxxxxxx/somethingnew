package com.cqx.leetcode.zailai.lookback.search;

/**
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class M34_在排序数组中查找元素的第一个和最后一个位置 {

    /**
     * logN的时间复杂度  那就二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int val = nums[mid];
            if (val == target) {
                //左右查找 返回
                int leftBoard = mid;
                int rightBoard = mid;
                while (--leftBoard >= 0 && nums[leftBoard] == target) {
                }
                while (++rightBoard < nums.length && nums[rightBoard] == target) {
                }
                int[] r = new int[] {leftBoard + 1, rightBoard - 1};
                return r;
            }
            if (val < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
    }
}
