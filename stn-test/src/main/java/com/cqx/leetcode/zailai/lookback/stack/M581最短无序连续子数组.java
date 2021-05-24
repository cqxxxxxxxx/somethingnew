package com.cqx.leetcode.zailai.lookback.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * <p>
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：0
 * <p>
 * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
 */
public class M581最短无序连续子数组 {

    /**
     * 原数组和整体排序后的数组进行比较
     * 第一个不同的值的索引为开始位置
     * 最后一个不同值的索引为结束位置
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray0(int[] nums) {
        int[] sorted = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sorted[i] = nums[i];
        }
        Arrays.sort(sorted);
        int start = 0;
        while (start < nums.length && sorted[start] == nums[start]) {
            start++;
        }
        int end = sorted.length - 1;
        while (end >= 0 && sorted[end] == nums[end]) {
            end--;
        }
        return end - start > 0 ? end - start + 1 : 0;
    }

// ====================== 栈 ==================

    /**
     * 单调栈，两次遍历，找到最小和最大索引位置
     * 最小值，从前往后遍历，单调递增栈
     * 最大值，从后往前遍历，单调递减栈
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray1(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        int min = nums.length;
        //第一次 单调递增栈， 顶部是最大的元素的index， 如果遇到顶部小于当前的，说明顺序有问题
        for (int i = 0; i < nums.length; i++) {
            //栈顶元素大于当前，pop直到小于当前,pop出的最后一个即为最小索引位置
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                min = Math.min(stack.pop(), min);
            }
            stack.push(i);
        }
        stack.clear();
        int max = 0;
        //递减栈, 如果发现当前的比栈顶大，说明顺序有问题
        for (int i = nums.length - 1; i >= 0; i--) {
            //栈顶元素小于当前，pop直到大于当前,pop出的最后一个即为最大索引位置
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                max = Math.max(stack.pop(), max);
            }
            stack.push(i);
        }
        return (max - min > 0) ? max - min + 1 : 0;
    }


//======================== 错误，不用DP====================

    /**
     * 单调递增栈
     * dp[i] 以i为下标 最短连续子数组长度
     * val[i] > 前面 最大值 => dp[i] = dp[i-1]
     * val[i] < 前面最大值 => dp[i] = i-最开始的索引 + 1
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int minIndex = 0;
        int maxVal = Integer.MIN_VALUE;
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                dp[i] = dp[i - 1];
                minIndex = i;
            } else {
                dp[i] = i - minIndex + 1;

            }
        }
        return 1;
    }
}
