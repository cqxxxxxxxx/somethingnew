package com.cqx.leetcode.zailai.lookback.dp;

/**
 * https://leetcode-cn.com/problems/burst-balloons/
 */
public class H312_戳气球 {

    /**
     * dp数组： dp[i][j] (i,j)的开区间里面最大的金币数量
     * dp方程，状态转移方程:
     * dp[i][j] = dp[i][k] + dp[k][j] + nums[i] * nums[k] * nums[j]
     *
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        // 创建一个辅助数组，并在首尾各添加1，方便处理边界情况
        int[] temp = new int[n+2];
        temp[0] = 1;
        temp[n+1] = 1;
        for(int i=0; i<n; i++){
            temp[i+1] = nums[i];
        }
        int[][] dp = new int[n+2][n+2];
        // len表示开区间长度
        for(int len=3; len<=n+2; len++){
            // i表示开区间左端点
            for(int i=0; i<=n+2-len; i++){
                int res = 0;
                // k为开区间内的索引
                for(int k = i+1; k<i+len-1; k++){
                    int left = dp[i][k];
                    int right = dp[k][i+len-1];
                    res = Math.max(res, left + temp[i]*temp[k]*temp[i+len-1] + right);
                }
                dp[i][i+len-1] = res;
            }
        }
        return dp[0][n+1];
    }
}
