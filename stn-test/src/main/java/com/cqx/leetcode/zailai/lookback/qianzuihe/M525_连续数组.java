package com.cqx.leetcode.zailai.lookback.qianzuihe;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * <p>
 * 示例 1:
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 *  
 * <p>
 * 链接：https://leetcode-cn.com/problems/contiguous-array
 */
public class M525_连续数组 {

    public static void main(String[] args) {
        int maxLength0 = findMaxLength0(new int[]{0, 1, 0, 0, 1});
        System.out.println(maxLength0);
    }

    /**
     * 0 1  0 1
     * -1 0 -1 0
     *
     * @param nums
     * @return
     */
    public static int findMaxLength0(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        // 下标 0 之前的位置是 -1
        map.put(0, -1);
        int res = 0;
        int preSum = 0;
        // 把数组中的 0 都看成 -1
        for (int i = 0; i < nums.length; i++) {
            // 把 0 视为 -1，pre 是先加了，所以后面计算的时候是 i - map.get(preSum)，注意下标 + 1 和不加 1 的差别
            if (nums[i] == 1) {
                preSum += 1;
            } else {
                preSum += -1;
            }
            if (map.containsKey(preSum)) {
                res = Math.max(res, i - map.get(preSum));
            } else {
                // 只记录这个数字第 1 次出现的下标
                map.put(preSum, i);
            }
        }
        return res;
    }

    /**
     * 前缀和 + hashmap
     * 把0看做-1，就是求最长连续子数组和为0
     *
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int preSum = 0;
        int max = 0;
        map.put(0, 0);
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i] == 0 ? -1 : 1;
            if (map.get(0 - preSum) != null) {
                Integer index = map.get(0 - preSum);
                max = Math.max(max, i - index + 1);
            }
            if (map.get(preSum) == null) {
                map.put(preSum, i);
            }
        }
        return max;
    }
}
