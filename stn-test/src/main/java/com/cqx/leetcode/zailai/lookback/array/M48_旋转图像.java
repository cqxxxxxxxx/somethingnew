package com.cqx.leetcode.zailai.lookback.array;

/**
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * <p>
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-image
 */
public class M48_旋转图像 {
    /**
     * 用另外一个辅助矩阵
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int[][] help = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                help[j][matrix[i].length - 1 - i] = matrix[i][j];
            }
        }
        for (int i = 0; i < help.length; i++) {
            for (int j = 0; j < help[i].length; j++) {
                matrix[i][j] = help[i][j];
            }
        }
    }
}
