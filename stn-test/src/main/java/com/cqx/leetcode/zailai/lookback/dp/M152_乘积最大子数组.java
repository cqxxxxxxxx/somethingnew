package com.cqx.leetcode.zailai.lookback.dp;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 */
public class M152_乘积最大子数组 {

    /**
     * @param nums
     * @return
     */
    public static int maxProduct(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        // 表示最大值
        dp[0][0] = nums[0];
        // 表示最小值
        dp[0][1] = nums[0];
        int res = nums[0];
        for (int i = 1; i < n; i++) {
            int a = dp[i - 1][0] * nums[i];
            int b = dp[i - 1][1] * nums[i];
            int c = nums[i];
            dp[i][0] = Math.max(c, Math.max(a, b));
            dp[i][1] = Math.min(c, Math.min(a, b));
            res = Math.max(res, dp[i][0]);
        }
        return res;
    }


    /**
     * 动态规划
     * dp[i][0] 标识以i结尾，最大的正数
     * dp[i][1] 表示以i结尾，最小的负数
     * <p>
     * 状态转移方程
     * <p>
     * base case
     * dp[0][0]
     *
     * @param nums
     * @return
     */
    public int maxProduct1(int[] nums) {
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int a = dp[i - 1][0] * nums[i];
            int b = dp[i - 1][1] * nums[i];
            dp[i][0] = Math.max(Math.max(a, b), nums[i]);
            dp[i][1] = Math.min(Math.min(a, b), nums[i]);
            max = Math.max(max, dp[i][0]);
        }
        return max;
    }

    public static void main(String[] args) {
        int i = maxProduct(new int[]{-3, -1, -1});
        System.out.println(i);
    }
}
