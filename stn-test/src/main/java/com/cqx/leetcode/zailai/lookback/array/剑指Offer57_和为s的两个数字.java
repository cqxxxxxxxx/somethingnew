package com.cqx.leetcode.zailai.lookback.array;

/**
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 * <p>
 * <p>
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * <p>
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof
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
