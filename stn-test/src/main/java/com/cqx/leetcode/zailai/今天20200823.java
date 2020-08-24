package com.cqx.leetcode.zailai;

import java.util.Arrays;
import java.util.List;

public class 今天20200823 {

    /**
     * 62. 不同路径
     * 动态规划 DP dynamic programming
     *
     * @param m 列数量
     * @param n 行数量
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) dp[0][i] = 1;
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    //优化内存 todo 不太理解
    public int uniquePaths1(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] += cur[j - 1];
            }
        }
        return cur[n - 1];
    }


    /**
     * 63. 不同路径
     *
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        //错误的， 第一行或者列如果有一个不通，后面的也都不通了
        //obstacleGrid[i][0] == 1时候需要break
        for (int i = 0; i < m; i++) {
            dp[i][0] = obstacleGrid[i][0] == 1 ? 0 : 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = obstacleGrid[0][i] == 1 ? 0 : 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] obstacle = new int[1][2];
        obstacle[0][0] = 1;
        obstacle[0][1] = 0;
        final int i = uniquePathsWithObstacles1(obstacle);
        System.out.println(i);
    }


    public static int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }

        // 定义 dp 数组并初始化第 1 行和第 1 列。
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }

        // 根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推。
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }


//=========================================  最长公共子序列

    public int longestCommonSubsequence(String text1, String text2) {
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        int length1 = t1.length;
        int length2 = t2.length;
        int[][] dp = new int[length1 + 1][length2 + 1]; //dp数组存储中间结果 优化计算
        for (int i = 1; i < length1 + 1; i++) {
            for (int j = 1; j < length2 + 1; j++) {
                if (t1[i - 1] == t2[j - 1]) {
                    // 这边找到一个 lcs 的元素，继续往前找
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    //谁能让 lcs 最长，就听谁的
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[length1][length2];
    }

    public int longestCommonSubsequence1(String text1, String text2) {
        final char[] rows = text1.toCharArray();
        final char[] columns = text2.toCharArray();
        int rowSize = rows.length;
        int columnSize = columns.length;
        int[][] dp = new int[rowSize + 1][columnSize + 1]; //dp数组
        for (int i = 1; i < rowSize + 1; i++) {
            for (int j = 1; j < columnSize + 1; j++) {
                if (rows[i - 1] == columns[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[rowSize][columnSize];
    }


    //  ================== 120. 三角形最小路径和

    /**
     * 自顶向下递归
     * 递归
     * f(i,j)=min(f(i+1,j),f(i+1,j+1))+triangle[i][j]
     *
     * @param triangle
     * @return
     */
    public int minimumTotalRecursion(List<List<Integer>> triangle) {
        return dfs(triangle, 0, 0);
    }

    private int dfs(List<List<Integer>> triangle, int i, int j) {
        //terminator
        if (i == triangle.size()) {
            return 0;
        }
        return Math.min(dfs(triangle, i + 1, j), dfs(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
    }


    /**
     * 自顶向下递归
     * 递归 记忆优化了的 类似斐波那契数列
     * 时间复杂度 O(n^2) 1分为2 看下递归树
     * 空间复杂度 O(n^2) memo数组
     *
     * @param triangle
     * @return
     */
    Integer[][] memo;

    public int minimumTotalRecursionOptimised(List<List<Integer>> triangle) {
        memo = new Integer[triangle.size()][triangle.size()];
        return dfsOptimised(triangle, 0, 0);
    }

    private int dfsOptimised(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size()) {
            return 0;
        }
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        return memo[i][j] = Math.min(dfsOptimised(triangle, i + 1, j), dfsOptimised(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
    }


    /**
     * 动态规划
     * 自底向上推导（已知开始推）
     * dp数组 dp[n+1][n+1]
     * 状态转移方程 f[i][j] = min(f[i+1][j],f[i+1][j+1]) + triangle[i][j]
     * 注意dp数组 n+1 是
     *
     * @param triangle
     * @return
     */
    public int minimumTotalDP(List<List<Integer>> triangle) {
        int n = triangle.size();
        // dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
        int[][] dp = new int[n + 1][n + 1];
        // 从三角形的最后一行开始递推。
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }


    public int minimumTotalDP1(List<List<Integer>> triangle) {
        int n = triangle.size(); //三角形行数， 最下层的行的列数
        int[][] dp = new int[n + 1][n + 1]; //n+1在底下再追加一层，int数组默认都是0
        for (int i = n - 1; i >= 0; i--) {  //从最低层开始推导
            for (int j = 0; j <= i; j++) { //i就是当前层的列数
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }


//  ==================== 53. 最大子序和

    /**
     * 1. 分治，子问题  max_sum(i) = max(max_sum(i-1), 0) + a[i]
     * 2. dp数组  f[i+1]
     * 3. 状态转移方程 :  f[i] = max(f[i-1],0) + a[i]
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int[] dp = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + nums[i - 1];
            max = Math.max(dp[i], max);
        }
        return max;
    }


//=============322. 零钱兑换

    /**
     * DP解
     * 子问题:  min_count(n) = min(min_count(n-1), min_count(n-3), min_count(n-5)) + 1;
     * dp数组:  f[n+1]
     * 状态转换方程:  f[n] = min(f[n-1], f[n-3], f[n-5]) + 1
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


// ======================== house rober

    /**
     * 198. 打家劫舍 简单
     * 子问题：
     * dp数组：int[][] dp = new int[n][2];
     * 状态转移方程:
     * dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
     * dp[i][1] = dp[i - 1][0] + nums[i];
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] dp = new int[n][2];

        dp[0][0] = 0;
        dp[0][1] = nums[0];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i];
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }


    /**
     * 状态转移方程 f[i] = max(f[i-1], f[i-2] + nums[i])
     *
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        final int length = nums.length;
        int dp[] = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);

        int result = 0;
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            result = Math.max(dp[i], result);
        }
        return result;
    }
}
