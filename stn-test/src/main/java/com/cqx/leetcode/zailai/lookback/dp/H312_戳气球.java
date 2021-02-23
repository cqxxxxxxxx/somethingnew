package com.cqx.leetcode.zailai.lookback.dp;

/**
 * https://leetcode-cn.com/problems/burst-balloons/
 */
public class H312_戳气球 {

    /**
     * dp数组： dp[i][j] (i,j)的开区间里面最大的金币数量
     * dp方程，状态转移方程:
     * dp[i][j] = dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j]
     *
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        int[][] dp = new int[nums.length + 2][nums.length + 2];

        return 1;
    }
}
