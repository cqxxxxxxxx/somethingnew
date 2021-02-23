package com.cqx.leetcode.zailai.lookback.dp;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/triangle/
 */
public class M120_三角形最小路径和 {
    /**
     * f(i,j) = min{ f(i+1,j), f(i+1,j+1)} + val(i,j)
     * <p>
     * dp[i][j] 定义为i,j的最小路径和
     * <p>
     * dp[i][j]=min(dp[i+1,j],dp[i+1,j+1]) + val(i,j)
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n + 1][n + 1]; //最下面铺一层 000000
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

}
