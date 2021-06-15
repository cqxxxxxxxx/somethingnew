package com.cqx.leetcode.zailai.lookback.dp.backpack;

import java.util.Scanner;

/**
 * 0-1 背包问题 ：有 N 件物品和一个容量为 C 的背包。第 i 件物品的费用是 v[i]，价值是 w[i]。
 * <p>
 * 求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
 * <p>
 * 链接：https://leetcode-cn.com/circle/discuss/GWpXCM/
 */
public class I零一背包 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int C = sc.nextInt();
        int[] v = new int[N];
        int[] w = new int[N];
        for (int i = 0; i < N; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }
        System.out.println(maxValue(N, C, v, w));
    }

    /**
     * dp[i][j] 表示前i个物品在容量j的背包中 能产生最大的价值
     * dp[i][j] = max(dp[i-1][j], dp[i-1][j-w[i]] + v[i])
     * 即第i个不选，或者第i个选（前i-1个的背包容量就是j-w[i]），两者取最大值。
     *
     * @param N n个物品
     * @param C 容量C
     * @param v 物品价值
     * @param w 物品重量
     * @return
     */
    private static int maxValue(int N, int C, int[] v, int[] w) {
        int[][] dp = new int[N][C + 1];
        dp[0][0] = 0;
        for (int i = 0; i < C + 1; i++) {
            dp[0][i] = i >= v[0] ? w[0] : 0;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < C + 1; j++) {
                int a = dp[i - 1][j]; // 不选该物品
                int b = j >= v[i] ? w[i] + dp[i - 1][j - v[i]] : 0; //选择该物品
                dp[i][j] = Math.max(a, b);
            }
        }
        return dp[N - 1][C];
    }
}
