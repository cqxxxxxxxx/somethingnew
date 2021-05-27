package com.cqx.leetcode.zailai.lookback.dp;

/**
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * <p>
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * <p>
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * <p>
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 */
public class M309_最佳买卖股票时机含冷冻期 {

    /**
     * dp[i][0] 不持股且当天没卖出,定义其最大收益dp[i][0];
     * dp[i][1] 持股,定义其最大收益dp[i][1]
     * dp[i][2] 不持股且当天卖出了，定义其最大收益dp[i][2]；
     * <p>
     * 状态转移方程：
     * dp[i][0] = max(dp[i-1][0], dp[i-1][2])
     * dp[i][1] = max(dp[i-1][0] - prices[i], dp[i-1][1])
     * dp[i][2] = max(dp[i-1][1] + prices[i])
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int l = prices.length;
        int[][] dp = new int[l][3];
        dp[0][0] = 0;
        dp[0][1] = -1 * prices[0];
        dp[0][2] = 0;

        for (int i = 1; i < l; i++) {//从[1]...[n-1]
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            dp[i][2] = dp[i - 1][1] + prices[i];
        }
        return Math.max(dp[l - 1][0], dp[l - 1][2]);
    }
}
