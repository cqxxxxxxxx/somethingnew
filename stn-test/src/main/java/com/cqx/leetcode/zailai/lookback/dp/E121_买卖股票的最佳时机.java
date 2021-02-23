package com.cqx.leetcode.zailai.lookback.dp;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 */
public class E121_买卖股票的最佳时机 {

    /**
     * f(i) = f(i-1) + prices[i] - prices[i-1]
     * dp[i] 定义为在i天卖出时候的最大利润
     * dp[i] = dp[i-1] + prices[i] - prices[i-1]
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 0) {
            return 0;
        }
        int max = 0;
        int[] dp = new int[prices.length];
        dp[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            int profit = dp[i - 1] + prices[i] - prices[i - 1];
            if (profit > 0) {
                dp[i] = profit;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] data = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(data));
    }
}
