package com.cqx.leetcode.zailai.lookback.qianzuihe;

import java.util.HashMap;
import java.util.Map;

/**
 * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
 * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
 * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
 * <p>
 * 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * 输出：4
 * 解释：四个只含 0 的 1x1 子矩阵。
 * <p>
 * 链接：https://leetcode-cn.com/problems/number-of-submatrices-that-sum-to-target
 */
public class H1074_元素和为目标值的子矩阵数量 {


    public int numSubmatrixSumTarget0(int[][] mat, int t) {
        int n = mat.length, m = mat[0].length;
        int[][] sum = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int ans = 0;
        for (int top = 1; top <= n; top++) {
            for (int bot = top; bot <= n; bot++) {
                int cur = 0;
                Map<Integer, Integer> map = new HashMap<>();
                for (int r = 1; r <= m; r++) {
                    cur = sum[bot][r] - sum[top - 1][r];
                    if (cur == t) ans++;
                    if (map.containsKey(cur - t)) ans += map.get(cur - t);
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return ans;
    }

// ================================

    /**
     * TODO 还是有问题
     * 二维前缀和 转一维前缀和
     * https://leetcode-cn.com/problems/number-of-submatrices-that-sum-to-target/solution/dong-tu-yan-shi-ha-xi-biao-qian-zhui-he-wq9f4/
     *
     * @param matrix
     * @param target
     * @return
     */
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int[][] table = new int[matrix.length][matrix[0].length + 1];
        //计算每行的前缀和, 第一列铺0，方便后面计算
        for (int i = 0; i < table.length; i++) {
            int preSum = 0;
            for (int j = 1; j < table[0].length; j++) {
                preSum = preSum + matrix[i][j - 1];
                table[i][j] = preSum;
            }
        }
        int count = 0;
        //一列一列遍历，判断是否有=target的数值
        for (int i = 1; i < table[0].length; i++) {
            for (int col = i; col < table[0].length; col++) {
                int colPreSum = 0;
                for (int row = 0; row < table.length; row++) {
                    colPreSum = colPreSum + table[row][col] - table[row][i - 1];
                    if (colPreSum - target == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
//        int[][] t = new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}};
        int[][] t = new int[][]{{-1, 1}, {1, -1}};

        int i = numSubmatrixSumTarget(t, 0);
        System.out.println(i);
    }
}
