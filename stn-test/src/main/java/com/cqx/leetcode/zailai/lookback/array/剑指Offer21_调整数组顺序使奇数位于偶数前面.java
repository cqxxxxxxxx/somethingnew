package com.cqx.leetcode.zailai.lookback.array;

/**
 * https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 */
public class 剑指Offer21_调整数组顺序使奇数位于偶数前面 {

    public int[] exchange(int[] nums) {
        int[] r = new int[nums.length];
        int start = 0, end = nums.length - 1;
        for (int num : nums) {
            if (num % 2 == 0) {
                r[end] = num;
                end--;
            } else {
                r[start] = num;
                start++;
            }
        }
        return r;
    }


    public int[] exchange1(int[] nums) {
        int begin = 0, end = nums.length - 1;
        while (begin < end) {
            while (nums[begin] % 2 != 0 && begin < end) {
                begin++;
            }
            while (nums[end] % 2 == 0 && begin < end) {
                end--;
            }
            int tmp = nums[begin];
            nums[begin] = nums[end];
            nums[end] = tmp;
        }
        return nums;
    }
}
