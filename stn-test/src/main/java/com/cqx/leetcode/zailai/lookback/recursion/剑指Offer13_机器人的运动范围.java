package com.cqx.leetcode.zailai.lookback.recursion;

/**
 * 剑指 Offer 13. 机器人的运动范围
 */
public class 剑指Offer13_机器人的运动范围 {
    int m1, n1, k1;
    boolean[][] visited;

    /**
     * 数位和规律 如果每次都计算 可能会超时
     * 19 -> 20 数位之和 -8
     * 29 -> 30 数位之和 -8
     * 12 -> 13 数位之和 +1
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        visited = new boolean[m][n];
        m1 = m;
        n1 = n;
        k1 = k;
        return dfs(0, 0, 0, 0);
    }

    private int dfs(int i, int j, int si, int sj) {
        // terminator
        if (i > m1 - 1 || j > n1 - 1 || (si + sj) > k1 || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        //process
        return 1 + dfs(i + 1, j, c(i + 1, si), sj) + dfs(i, j + 1, si, c(j + 1, sj));
    }

    private int c(int val0, int val1) {
        return (val0 + 1) % 10 == 0 ? val1 - 8 : val1 + 1;
    }

}
