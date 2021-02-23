package com.cqx.leetcode.zailai.lookback.dp;

/**
 * https://leetcode-cn.com/problems/target-sum/solution/mu-biao-he-by-leetcode/
 */
public class M494_目标和 {


    /**
     * 递归
     * 时间复杂度 O（2^N）
     * 空间复杂度 O(N)，为递归使用的栈空间大小。
     *
     * @param nums
     * @param S
     * @return
     */
    int count = 0;

    public int findTargetSumWays(int[] nums, int S) {
        recursion(nums, 0, S);
        return count;
    }

    private void recursion(int[] nums, int i, int s) {
        if (i == nums.length - 1) {
            //0的情况 考虑
            if (nums[i] == s) {
                count++;
            }
            if (-nums[i] == s) {
                count++;
            }
        } else {
            recursion(nums, i + 1, s - nums[i]);
            recursion(nums, i + 1, s + nums[i]);
        }
    }


//  ====================== DP ====================


    /**
     * dp数组定义： dp[i][j]  前i个元素，组成和为j的方案数量
     * dp方程： dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
     * <p>
     * https://leetcode-cn.com/problems/target-sum/solution/mu-biao-he-by-leetcode/
     */
    public int findTargetSumWaysDP(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1; //num[0] = 0 的时候是2
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + 1000] = dp[i - 1][sum + 1000 + nums[i]] + dp[i - 1][sum + 1000 - nums[i]];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }

    /**
     * https://leetcode-cn.com/problems/target-sum/solution/huan-yi-xia-jiao-du-ke-yi-zhuan-huan-wei-dian-xing/
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWaysDP2(int[] nums, int S) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        // 背包容量为整数，sum+S为奇数的话不满足要求
        if ((sum + S) % 2 == 1) {
            return 0;
        }
        // 目标和不可能大于总和
        if (S > sum) {
            return 0;
        }
        int len = (sum + S) / 2;
        int[] dp = new int[len + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int i = len; i >= num; --i) {
                dp[i] += dp[i - num];
            }
        }

        return dp[len];
    }

}
