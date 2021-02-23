package com.cqx.leetcode.zailai.lookback.dp;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/unique-paths/
 */
public class M62_不同路径 {

    /**
     * 62. 不同路径
     * 动态规划 DP dynamic programming
     * f(m,n)=f(m-1,n)+f(m,n-1)
     * dp[i][j] 为到达i，j上的路径数量  空间复杂度 m*n
     * dp[i][j]=dp[i-1][j]+dp[i][j-1]
     *
     * @param m 列数量
     * @param n 行数量
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 空间复杂度 2*n
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        int[] pre = new int[n];
        int[] cur = new int[n];
        Arrays.fill(pre, 1);
        cur[0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] = cur[j - 1] + pre[j];
            }
            pre = cur.clone();
        }
        return cur[n - 1];
    }
}
