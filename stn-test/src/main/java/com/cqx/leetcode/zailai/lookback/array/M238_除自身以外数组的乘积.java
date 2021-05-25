package com.cqx.leetcode.zailai.lookback.array;

/**
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * <p>
 * 示例:
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * <p>
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 */
public class M238_除自身以外数组的乘积 {

    /**
     * 两个数组L[i], R[i]分别表示，i左边数乘积，i右边数乘积
     * 目标值除i以外的乘积就= L[i-1] *R[i+1]
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] l = new int[nums.length + 1];
        int[] r = new int[nums.length + 1];
        l[0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            l[i] = l[i - 1] * nums[i - 1];
        }
        r[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            r[i] = r[i + 1] * nums[i + 1];
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = l[i] * r[i];
        }
        return ans;
    }
}
