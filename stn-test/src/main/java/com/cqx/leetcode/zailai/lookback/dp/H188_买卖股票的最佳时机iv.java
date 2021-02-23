package com.cqx.leetcode.zailai.lookback.dp;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 */
public class H188_买卖股票的最佳时机iv {


    /**
     * 动态规划 类似iii题
     * <p>
     * dp数组: dp[i][j][k]
     * i 第i天
     * j 股票持有状态 0持有现金 1持有股票
     * k 第k次交易
     * ...
     * <p>
     * dp方程，状态转移方程:
     * dp[i][0][k] = max(dp[i-1][0][k] , dp[i-1][1][k]+prices[i])
     * dp[i][1][k-1] = max(dp[i-1][1][k-1] , dp[i-1][0][k-1]-prices[i])
     * 这里需要注意：我将买入股票时作为一次交易，也就是在买入股票的时候交易次数+1
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;
        int[][][] dp = new int[prices.length][2][k + 1];
        for (int i = 0; i <= k; i++) {
            dp[0][0][i] = 0;
            dp[0][1][i] = -prices[0];
        }
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j < k + 1; j++) {
                dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i]);
                dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i]);
            }
        }
        return dp[n - 1][0][k];
    }


    public int maxProfit3(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;
        k = Math.min(k, n / 2);
        int[][][] dp = new int[n][2][k + 1];
        for (int j = 0; j <= k; j++) {
            dp[0][0][j] = 0;
            dp[0][1][j] = -prices[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i]);
                dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i]);
            }
        }
        return dp[n - 1][0][k];
    }


    public int maxProfit2(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        //当k非常大时转为无限次交易
        if (k >= n / 2) {
            int dp0 = 0, dp1 = -prices[0];
            for (int i = 1; i < n; ++i) {
                int tmp = dp0;
                dp0 = Math.max(dp0, dp1 + prices[i]);
                dp1 = Math.max(dp1, tmp - prices[i]);
            }
            return dp0;
        }
        //定义三维数组，第i天、交易了多少次、当前的买卖状态
        int[][][] dp = new int[n][k + 1][2];
        //初始化第一天，这里的dp[0][k][1]可以不用管，后面也不会用到
        for (int i = 0; i <= k; ++i) {
            dp[0][i][0] = 0;
            dp[0][i][1] = -prices[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j <= k; ++j) {
                //处理第k次买入
                dp[i][j - 1][1] = Math.max(dp[i - 1][j - 1][1], dp[i - 1][j - 1][0] - prices[i]);
                //处理第k次卖出
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] + prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }
}
