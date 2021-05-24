package com.cqx.leetcode.zailai.lookback.search;

/**
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * <p>
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * <p>
 * 链接：https://leetcode-cn.com/problems/next-permutation
 */
public class M31_下一个排列 {

    /**
     * 1. 后往前找到第一个 顺序对 i,i+1 其中 nums[i] < nums[i+1] => 此时就保证了 [i+1,end)之间都是降序
     * 2. 在[i+1,end)里面后往前找到第一个比nums[i]大的数 j
     * 3. i,j互换位置
     * 4. i+1, end 升序排列
     *
     * @param nums
     */
    public void nextPermutation0(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }


    //============= 错误，这种方案会将 1 3 2 = > 2 3 1 其实是应该 2 1 3的
    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                    return;
                }
            }
        }
        for (int i = 0; i < (nums.length - 1) >> 1; i++) {
            int j = nums.length - 1 - i;
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
}
