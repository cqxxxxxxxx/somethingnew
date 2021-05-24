package com.cqx.leetcode.zailai.lookback.dp;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *  
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * <p>
 * 提示：
 * 1 <= n <= 104
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 */
public class M279_完全平方数 {
    /**
     * dp[i] 为值为i时候，最少之和为i的完全平方数组合个数
     * <p>
     * i为完全平方数 => dp[i] = 1;
     * i不为完全平方数 => dp[i] = min(dp[i-完全平方数] + 1, dp[i-完全平方数] + 1)
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Set<Integer> squareSet = new HashSet<>();
        dp[0] = 0;
        //填充完全平方数时候i的值=1
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (i * i > n) {
                break;
            }
            squareSet.add(i * i);
        }
        for (int i = 1; i <= n; i++) {
            if (squareSet.contains(i)) {
                dp[i] = 1;
            } else {
                int min = Integer.MAX_VALUE;
                for (Integer squareValue : squareSet) {
                    if (i - squareValue >= 0) {
                        min = Math.min(dp[i - squareValue] + 1, min);
                    }
                }
                dp[i] = min;
            }
        }
        return dp[n];
    }
}
