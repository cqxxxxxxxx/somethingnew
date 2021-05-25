package com.cqx.leetcode.zailai.lookback.search;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * <p>
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 */
public class M240_搜索二维矩阵II {


    /**
     * 官方第四中方案
     * 从左下开始查找，当前值大于target则往上，小于则往右走。 超出边界则返回false
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix0(int[][] matrix, int target) {
        // start our "pointer" in the bottom-left
        int row = matrix.length - 1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else { // found it
                return true;
            }
        }

        return false;
    }


    /**
     * TODO 思路没有错，但是呢，矩阵可能不是正方形。。。。具体做法参考，需要对剩余部分继续对角线查找
     * TODO https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/240-sou-suo-er-wei-ju-zhen-ii-by-junlin-k7r76/
     * 左上->右下对角线搜索
     * 找到第一个对角线上大于target的数, matrix[i][j]
     * 那么这个数就在 matrix[i-1][j] -> matrix[i-1][end] 或者 matrix[i][j-1] -> matrix[end][j-1]
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 1) {
            for (int i = 0; i < matrix[0].length; i++) {
                if (matrix[0][i] == target) {
                    return true;
                }
            }
            return false;
        }
        if (matrix[0].length == 1) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][0] == target) {
                    return true;
                }
            }
            return false;
        }
        int i = 0, j = 0;
        boolean hasBigger = false;
        while (i < matrix.length && j < matrix[0].length) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                hasBigger = true;
                break;
            }
            i++;
            j++;
        }
        if (!hasBigger || (i == 1 && j == 1)) {
            return false;
        }
        for (int k = j; k < matrix[0].length; k++) {
            if (matrix[i - 1][k] == target) {
                return true;
            }
        }
        for (int k = 0; k < j; k++) {
            if (matrix[i][k] == target) {
                return true;
            }
        }
        return false;
    }
}
