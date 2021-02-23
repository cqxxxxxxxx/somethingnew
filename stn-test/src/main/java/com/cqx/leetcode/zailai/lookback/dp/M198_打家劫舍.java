package com.cqx.leetcode.zailai.lookback.dp;

/**
 * https://leetcode-cn.com/problems/house-robber/
 */
public class M198_打家劫舍 {

    /**
     * f(i) = max{f(i-1), f(i-2) + v(i)}
     * dp[i] 定义处于i家时候可盗窃的最大值
     * dp[i] = max(dp[i-1], dp[i-2]+v(i)) i家时候可盗窃的最大值为，i-1可盗窃的最大值 或者 i-2可盗窃最大值 + i的值
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }


    /**
     * 错误
     *
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int v1 = 0;
        for (int i = 0; i < nums.length; i += 2) {
            v1 += nums[i];
        }
        int v2 = 0;
        for (int i = 1; i < nums.length; i += 2) {
            v2 += nums[i];
        }
        return Math.max(v1, v2);
    }
}
