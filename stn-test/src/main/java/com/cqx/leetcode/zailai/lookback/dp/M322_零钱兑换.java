package com.cqx.leetcode.zailai.lookback.dp;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/coin-change/
 */
public class M322_零钱兑换 {

    /**
     * coins = [1, 2, 5], amount = 11
     * 最优子结构 f(amount) = min{f(amount-1), f(amount-2), f(amount-5)} + 1
     * dp数组 dp[i] 为 i快钱的最小硬币数量 ，初始化为amount+1
     * dp方程 dp[i]=min(dp[i-1], dp[i-2], dp[i-5]) + 1
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public int coinChange1(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
