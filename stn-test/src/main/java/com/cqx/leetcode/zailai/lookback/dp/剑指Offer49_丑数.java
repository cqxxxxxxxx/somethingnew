package com.cqx.leetcode.zailai.lookback.dp;

public class 剑指Offer49_丑数 {

    /**
     * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。
     *  1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
     * dp数组 dp[i] 为第i个丑数的值
     * dp方程 dp[i] = min(dp[a]*2, dp[b]*3, dp[c]*5)
     *
     * https://leetcode-cn.com/problems/chou-shu-lcof/solution/mian-shi-ti-49-chou-shu-dong-tai-gui-hua-qing-xi-t/
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 1; i < n; i++) {
            int n2 = dp[a] * 2, n3 = dp[b] * 3, n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if(dp[i] == n2) a++;
            if(dp[i] == n3) b++;
            if(dp[i] == n5) c++;
        }
        return dp[n - 1];
    }
}
