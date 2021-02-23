package com.cqx.leetcode.zailai.lookback.dp;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class E122_买卖股票的最佳时机ii {

    /**
     * 贪心算法
     * 作出局部最优解 从而寄希望因此得到全局最优解
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        int res = 0;
        for (int i = 1; i < len; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                res += diff;
            }
        }
        return res;
    }


    /**
     * 动态规划
     * 最优子结构  f(i,0) = max{f(i-1,0), f(i-1,1) + prices[i]}
     * f(i,1) = max{f(i-1,1), f(i-1,0) - prices[i]}
     * dp数组:  dp[i][j] i代表第i天  j代表当天状态 0持有现金 1持有股票
     * dp方程： dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     *
     * @param prices
     * @return
     */
    public int maxProfitDP(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }

}
