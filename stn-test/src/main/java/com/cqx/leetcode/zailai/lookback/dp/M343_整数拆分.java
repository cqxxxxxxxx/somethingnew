package com.cqx.leetcode.zailai.lookback.dp;

/**
 * https://leetcode-cn.com/problems/integer-break/
 */
public class M343_整数拆分 {

    // 暴力解法
    public int integerBreak1(int n) {
        if (n == 2) {
            return 1;
        }
        int res = -1;
        for (int i = 1; i <= n - 1; i++) {
            res = Math.max(res, Math.max(i * (n - i), i * integerBreak1(n - i)));
        }
        return res;
    }

    /**
     * dp[i] 数值为i时候 乘积最大值
     * dp[i] = max(dp[i], max((i - j) * j, dp[i - j] * j));
     * (i - j) * j, dp[i - j] * j)对应着拆分与不拆分两种方案
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        if (n < 2) {
            return 0;
        }
        int dp[] = new int[n + 1];
        dp[2] = 1;
        dp[1] = 0;
        dp[0] = 0;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i - 1; j++) {
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));
            }
        }
        return dp[n];
    }
}
