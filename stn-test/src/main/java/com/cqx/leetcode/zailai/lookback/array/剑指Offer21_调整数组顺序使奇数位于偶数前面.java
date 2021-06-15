package com.cqx.leetcode.zailai.lookback.array;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * <p>
 * 示例：
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *  
 * 提示：
 * 0 <= nums.length <= 50000
 * 1 <= nums[i] <= 10000
 * <p>
 * 链接：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof
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
