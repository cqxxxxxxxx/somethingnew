package com.cqx.leetcode.zailai.lookback.dp;

/**
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 */
public class M1143_最长公共子序列 {

    /**
     * f(i,j)=max{ f(i-1,j), f(i,j-1) }  i,j不同时候
     * f(i,j)=f(i-1,j-1) + 1             i,j相同时候
     * <p>
     * dp[i][j] 定义为 字符串数组 的最长公共子序列长度
     * <p>
     * dp[i][j]=max(dp[i-1][j], dp[i][j-1])
     * dp[i][j]=dp[i-1,j-1] + 1;
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        final int m = text1.toCharArray().length;
        final int n = text2.toCharArray().length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[m][0] = 0; //不需要。默认就是0
        }
        for (int i = 0; i < n + 1; i++) {
            dp[0][i] = 0; //不需要。默认就是0
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

}
