package com.cqx.leetcode.zailai.lookback.recursion;

public class E70_爬楼梯 {

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 注意：给定 n 是一个正整数。
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int i1 = 1, i2 = 2, r = 0;
        for (int i = 3; i <= n; i++) {
            r = i1 + i2;
            i1 = i2;
            i2 = r;
        }
        return r;
    }

    /**
     * 动态规划解斐波那契数列
     * 重叠子问题：f(n) = f(n-1) + f(n-2)
     * dp数组: dp[n]
     * 状态转移方程：f(n) = f(n-1) + f(n-2)
     *
     * @param n
     * @return
     */
    public int climbStairsDP(int n) {
        int[] dp = new int[n];  //用于优化过程，避免不必要的计算 DP数组
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }
}
