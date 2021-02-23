package com.cqx.leetcode.zailai.lookback.dp;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
public class H123_买卖股票的最佳时机iii {

    /**
     * 动态规划
     * <p>
     * <p>
     * dp数组： dp[i][0-4] i代表第i天， 0-4代表五个状态
     * 0 没有操作
     * 1 第一次买入
     * 2 第一次卖出
     * 3 第二次买入
     * 4 第二次卖出
     * <p>
     * dp方程 状态转移方程：
     * dp[i][0] = dp[i-1][0] = 0
     * <p>
     * 第i天处于第一次买入状态，1.之前一直没有买，第i天当天买入 2.前一天已经处于第一次买入状态 两者取最大值
     * dp[i][1] = max(dp[i-1][0] - prices[i], dp[i-1][1])
     * <p>
     * dp[i][2] = max(dp[i-1][1] + prices[i], dp[i-1][2])
     * <p>
     * dp[i][3] = max(dp[i-1][2] - prices[i], dp[i-1][3])
     * <p>
     * dp[i][4] = max(dp[i-1][3] + prices[i], dp[i-1][4])
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = 0;
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            dp[i][2] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][2]);
            dp[i][3] = Math.max(dp[i - 1][2] - prices[i], dp[i - 1][3]);
            dp[i][4] = Math.max(dp[i - 1][3] + prices[i], dp[i - 1][4]);
        }
        return dp[prices.length - 1][4];
    }
}
