package com.cqx.leetcode.zailai.lookback.dp.backpack;

/**
 * 完全背包问题 ： 有 N 种物品和一个容量为 C 的背包，每种物品都有无限件。
 * <p>
 * 第 i 件物品的体积是 v[i]，价值是 w[i]。
 * <p>
 * 求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
 * <p>
 * 其实就是在 0-1 背包问题的基础上，增加了每件物品可以选择多次的特点（在容量允许的情况下）。
 * <p>
 * 链接：https://leetcode-cn.com/circle/discuss/GWpXCM/
 */
public class I完全背包 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//        int C = sc.nextInt();
//        int[] v = new int[N];
//        int[] w = new int[N];
//        for (int i = 0; i < N; i++) {
//            v[i] = sc.nextInt();
//            w[i] = sc.nextInt();
//        }
        System.out.println(maxValue(4, 10, new int[]{3, 4, 5, 8}, new int[]{1, 2, 3, 8}));
        System.out.println(maxValue1(4, 10, new int[]{3, 4, 5, 8}, new int[]{1, 2, 3, 8}));
    }

    /**
     * dp[i][j] 前i个物品，背包容量为j时候最大价值
     * dp[i][j] = max(dp[i-1][j - k*w[i]]) =>  0< k*w[i] <= j
     *
     * @param N 物品数量
     * @param C 背包容量
     * @param v 物品价值
     * @param w 物品重量
     * @return
     */
    private static int maxValue1(int N, int C, int[] v, int[] w) {
        int[][] dp = new int[N][C + 1];
        for (int i = 1; i <= C; i++) {
            dp[0][i] = i >= w[i - 1] ? ((i / w[i - 1])) * v[i - 1] : 0;
        }
        //前i个物品
        for (int i = 1; i < N; i++) {
            //背包容量为j
            for (int j = 0; j <= C; j++) {
                //遍历k 求最大值
                for (int k = 0; ; k++) {
                    if (k * w[i] <= j) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * w[i]] + v[i] * k);
                    } else {
                        break;
                    }
                }
            }
//            for (int j = 0; j < C + 1; j++) {
//                //遍历k 求最大值
//                for (int k = 0; ; k++) {
//                    if (k * w[i] <= j) {
//                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * w[i]] + w[i] * k);
//                    } else {
//                        break;
//                    }
//                }
//            }
        }
        return dp[N - 1][C];
    }


    private static int maxValue(int N, int C, int[] v, int[] w) {
        int[] dp = new int[C + 1];
        for (int i = 0; i < N; i++) {
            for (int j = C; j >= v[i]; j--) {
                for (int k = 0; ; k++) {
                    if (j < v[i] * k) {
                        break;
                    }
                    dp[j] = Math.max(dp[j], dp[j - v[i] * k] + w[i] * k);
                }
            }
        }
        return dp[C];
    }


}
