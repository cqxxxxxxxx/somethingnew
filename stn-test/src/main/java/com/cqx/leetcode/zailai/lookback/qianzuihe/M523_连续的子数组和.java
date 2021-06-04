package com.cqx.leetcode.zailai.lookback.qianzuihe;


import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
 * 子数组大小 至少为 2 ，且
 * 子数组元素总和为 k 的倍数。
 * 如果存在，返回 true ；否则，返回 false 。
 * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
 * <p>
 *  
 * 示例 1：
 * 输入：nums = [23,2,4,6,7], k = 6
 * 输出：true
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/continuous-subarray-sum
 */
public class M523_连续的子数组和 {

    /**
     * 同余定理
     * a - b = c , 如果c % k = 0， 则 a % k = b % k
     * 前缀和 + hashmap
     *
     * @param nums
     * @param k
     * @return
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        //当数组从0-N之和就是K的倍数时候，余数就是0，需要用这个来处理
        map.put(0, -1);
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            int remain = preSum % k;
            if (map.get(remain) != null) {
                if (i - map.get(remain) > 1) {
                    return true;
                }
            } else {
                map.put(preSum % k, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkSubarraySum(new int[]{23, 2, 4, 6, 6}, 7));
    }

    /**
     * 我的思考：
     * 1. 前缀和+遍历， 时间复杂度 O(n^2)
     * 2. 不前缀和+遍历， 时间复杂度好像也是O(n^2) ...所以感觉没什么区别啊
     * 先简单写个暴力的 第二种
     * 好吧 暴力写法 超时了
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum1(int[] nums, int k) {
        for (int i = 0; i < nums.length - 1; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
