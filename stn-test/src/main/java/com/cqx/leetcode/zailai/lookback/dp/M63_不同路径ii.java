package com.cqx.leetcode.zailai.lookback.dp;

/**
 * https://leetcode-cn.com/problems/unique-paths/
 */
public class M63_不同路径ii {

    /**
     * 63. 不同路径ii
     * f(i,j)=f(i-1,j)+f(i,j-1)
     * dp[i][j] 到达i,j点的路径数量
     * dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * 注意障碍物为0 以及第一行或者第一列如果遇到障碍物后面的都会变0，无法通过
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        final int rowNum = obstacleGrid.length;
        final int columnNum = obstacleGrid[0].length;
        for (int i = 0; i < rowNum; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = 1;
            } else {
                break;
            }
        }
        for (int i = 0; i < columnNum; i++) {
            if (obstacleGrid[0][i] == 0) {
                dp[0][i] = 1;
            } else {
                break;
            }
        }
        for (int i = 1; i < rowNum; i++) {
            for (int j = 1; j < columnNum; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[rowNum - 1][columnNum - 1];
    }
}
