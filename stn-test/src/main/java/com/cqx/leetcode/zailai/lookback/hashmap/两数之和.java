package com.cqx.leetcode.zailai.lookback.hashmap;

import java.util.HashMap;
import java.util.Map;

public class 两数之和 {
    //给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
//
//
//
// 示例:
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
//
// Related Topics 数组 哈希表
// 👍 9006 👎 0


    class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> valueIndexMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (valueIndexMap.get(target - nums[i]) != null) {
                    return new int[]{i, valueIndexMap.get(target - nums[i])};
                } else {
                    valueIndexMap.put(nums[i], i);
                }
            }
            return nums;
        }
    }

}
