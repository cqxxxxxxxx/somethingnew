package com.cqx.leetcode.zailai.lookback.array;

import java.util.ArrayList;
import java.util.List;

public class M54_螺旋矩阵 {

    /**
     * https://leetcode-cn.com/problems/spiral-matrix/
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int down = matrix.length - 1;
        List<Integer> r = new ArrayList<>(matrix.length);
        while (true) {
            for (int i = left; i <= right; i++) {
                r.add(matrix[top][i]);
            }
            if (++top > down) {
                break;
            }
            for (int i = top; i <= down; i++) {
                r.add(matrix[i][right]);
            }
            if (--right < left) {
                break;
            }
            for (int i = right; i >= left; i--) {
                r.add(matrix[down][i]);
            }
            if (--down < top) {
                break;
            }
            for (int i = down; i >= top; i--) {
                r.add(matrix[i][left]);
            }
            if (++left > right) {
                break;
            }
        }
        return r;
    }
}
