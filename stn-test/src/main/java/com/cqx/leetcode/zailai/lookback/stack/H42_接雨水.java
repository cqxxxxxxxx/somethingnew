package com.cqx.leetcode.zailai.lookback.stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 */
public class H42_接雨水 {

    /**
     * 暴力，遍历每个元素，向左，向右查找最大值，然后取最小值，计算该元素上面可以接多少雨水，结果就是所有计算之和
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int r = 0;
        for (int i = 0; i < height.length; i++) {
            int leftMax = 0;
            int rightMax = 0;
            for (int j = i; j >= 0; j--) {
                leftMax = Math.max(height[j], leftMax);
            }
            for (int j = i; j < height.length; j++) {
                rightMax = Math.max(height[j], rightMax);
            }
            r = r + Math.min(leftMax, rightMax) - height[i];
        }
        return r;
    }

    /**
     * dp寻找 下标i的最大左和最大右
     *
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        leftMax[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        rightMax[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int r = 0;
        for (int i = 0; i < height.length; i++) {
            r = r + Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return r;
    }
}
