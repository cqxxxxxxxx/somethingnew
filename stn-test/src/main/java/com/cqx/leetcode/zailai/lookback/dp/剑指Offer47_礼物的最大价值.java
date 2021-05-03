package com.cqx.leetcode.zailai.lookback.dp;

/**
 * https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/
 */
public class 剑指Offer47_礼物的最大价值 {


    /**
     * dp数组： dp[i][j] i,j 位置礼物的最大值
     * dp方程： dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     *
     * @param grid
     * @return
     */
    public int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = grid[i][j] + Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }
}
