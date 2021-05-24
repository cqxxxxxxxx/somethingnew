package com.cqx.leetcode.zailai.lookback.dp;

/**
 * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
 * <p>
 * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
 * <p>
 * nums1[i] == nums2[j]
 * 且绘制的直线不与任何其他连线（非水平线）相交。
 * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
 * <p>
 * 以这种方法绘制线条，并返回可以绘制的最大连线数。
 * <p>
 * https://leetcode-cn.com/problems/uncrossed-lines/
 */
public class M1035_不相交的线 {


    /**
     * 跟这题很像 一样 ？{@link M1143_最长公共子序列}
     * <p>
     * <p>
     * dp[i][j]定义，i，j下标索引位置 最大的连接数
     * <p>
     * nums[i]==nums[j] : dp[i][j] = dp[i-1][j-1] + 1
     * nums[i]!=nums[j] : dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     * <p>
     * 画个图比较清晰
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        //铺上一层0 方便计算?
        int[][] dp = new int[l1 + 1][l2 + 1];
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[l1][l2];
    }
}
