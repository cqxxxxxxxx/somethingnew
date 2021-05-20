package com.cqx.leetcode.zailai.lookback.qianzuihe;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/subarray-sum-equals-k/
 */
public class M560_和为K的子数组 {

    /**
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     * <p>
     * 示例 1 :
     * <p>
     * 输入:nums = [1,1,1], k = 2
     * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
     * 说明 :
     * <p>
     * 数组的长度为 [1, 20,000]。
     * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
     *
     * @param nums
     * @param k
     * @return
     */
    /**
     * hashMap + 前缀和
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum1(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap<Integer, Integer> mp = new HashMap<>();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }



    public int subarraySum(int[] nums, int k) {
        int r = 0;
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            if (sum[i] == k) {
                r++;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (sum[j] - sum[i] == k) {
                    r++;
                }
            }
        }
        return r;
    }



}
