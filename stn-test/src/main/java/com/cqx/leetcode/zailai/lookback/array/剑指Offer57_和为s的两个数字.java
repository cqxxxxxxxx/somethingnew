package com.cqx.leetcode.zailai.lookback.array;

/**
 * https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof/
 */
public class 剑指Offer57_和为s的两个数字 {


    /**
     * 两边夹逼
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (true) {
            int val = nums[start] + nums[end];
            if (val == target) {
                return new int[]{nums[start], nums[end]};
            }
            if (val > target) {
                end--;
            }
            if (val < target) {
                start++;
            }

        }
    }
}
