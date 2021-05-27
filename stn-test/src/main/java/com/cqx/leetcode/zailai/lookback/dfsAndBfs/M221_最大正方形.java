package com.cqx.leetcode.zailai.lookback.dfsAndBfs;

/**
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * <p>
 * https://leetcode-cn.com/problems/maximal-square/
 */
public class M221_最大正方形 {


    /**
     * dp
     * if (grid[i - 1][j - 1] == '1') {
     * dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1;
     * }
     * <p>
     * 链接：https://leetcode-cn.com/problems/maximal-square/solution/li-jie-san-zhe-qu-zui-xiao-1-by-lzhlyle/
     *
     * @param matrix
     * @return
     */
    public int maximalSquare0(char[][] matrix) {
        return 1;
    }

    /**
     * 暴力
     * BFS, 每个点向→，↘，↓都是的话才能继续扩展
     *
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(bfs(matrix, i, j, 0), max);
            }
        }
        return max * max;
    }


    private static int bfs(char[][] matrix, int i, int j, int layer) {
        if (i + layer >= matrix.length || j + layer >= matrix[0].length) {
            return 0;
        }
        //查找
        boolean thisLayerOk = true;
        for (int k = 0; k <= layer; k++) {
            if (matrix[i + k][j + layer] == '0') {
                thisLayerOk = false;
                break;
            }
            if (matrix[i + layer][j + k] == '0') {
                thisLayerOk = false;
                break;
            }
        }
        if (thisLayerOk) {
            return 1 + bfs(matrix, i, j, layer + 1);
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        int i = maximalSquare(matrix);
        System.out.println(i);
    }
}
