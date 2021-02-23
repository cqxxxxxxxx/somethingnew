package com.cqx.leetcode.zailai.lookback.dp;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/
 */
public class E53_最大子序和 {

    /**
     * f(i) = max{f(i-1), 0} + val(i)
     * <p>
     * dp[i] 定义为 i下标出的最大子序和
     * <p>
     * dp[i] = max(dp[i-1], 0) + val(i)
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int[] dp = new int[nums.length + 1];
        for (int i = 1; i < nums.length + 1; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i - 1];
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
