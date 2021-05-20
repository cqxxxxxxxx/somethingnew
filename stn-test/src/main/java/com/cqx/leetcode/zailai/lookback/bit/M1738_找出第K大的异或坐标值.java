package com.cqx.leetcode.zailai.lookback.bit;

import java.util.PriorityQueue;

/**
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 * <p>
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 * <p>
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 * https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value/
 */
public class M1738_找出第K大的异或坐标值 {

    /**
     * sum[i][j] = sum[i-1][j] ^ sum[i][j-1] ^ sum[i-1][j-1] ^ matrix[i][j]
     * 然后就是排序找出最大的k个值（大顶堆）
     *
     * @param matrix
     * @param k
     * @return
     */
    public int kthLargestValue(int[][] matrix, int k) {
        int[][] preSum = new int[matrix.length + 1][matrix[0].length + 1];
        //默认小顶堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k);
        for (int i = 1; i < matrix.length + 1; i++) {
            for (int j = 1; j < matrix[0].length + 1; j++) {
                preSum[i][j] = preSum[i - 1][j] ^ preSum[i][j - 1] ^ preSum[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                if (priorityQueue.size() < k) {
                    priorityQueue.offer(preSum[i][j]);
                } else {
                    if (priorityQueue.peek() < preSum[i][j]) {
                        priorityQueue.poll();
                        priorityQueue.offer(preSum[i][j]);
                    }
                }
            }
        }
        return priorityQueue.poll();
    }

}
