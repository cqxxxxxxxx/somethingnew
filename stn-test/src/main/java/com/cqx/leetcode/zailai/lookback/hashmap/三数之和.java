package com.cqx.leetcode.zailai.lookback.hashmap;

import java.util.*;

public class 三数之和 {
    //给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。
//
// 注意：答案中不可以包含重复的三元组。
//
//
//
// 示例：
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
//
// Related Topics 数组 双指针
// 👍 2531 👎 0


    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> r = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                int val0 = nums[i];
                int target = 0 - val0;
                //todo 这边可以提取出来优化掉，还有这样会有重复的结果
                Map<Integer, Integer> valueIndexMap = new HashMap<>();
                for (int j = i + 1; j < nums.length; j++) {
                    if (valueIndexMap.get(target - nums[j]) != null) {
                        ArrayList<Integer> matches = new ArrayList<>();
                        matches.add(val0);
                        matches.add(nums[j]);
                        matches.add(target - nums[j]);
                        r.add(matches);
                    } else {
                        valueIndexMap.put(nums[j], j);
                    }
                }
            }
            return r;
        }

        public List<List<Integer>> threeSum1(int[] nums) {
            List<List<Integer>> r = new ArrayList<>();
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                    int target = -nums[i];
                    int head = i + 1;
                    int tail = nums.length - 1;
                    while (head < tail) {
                        if (nums[head] + nums[tail] == target) {
                            ArrayList<Integer> match = new ArrayList<>();
                            match.add(nums[i]);
                            match.add(nums[head]);
                            match.add(nums[tail]);
                            r.add(match);
                            while (head < tail && nums[head] == nums[++head]) {
                            }
                            while (head < tail && nums[tail] == nums[--tail]) {
                            }
                        } else if (nums[head] + nums[tail] > target) {
                            while (head < tail && nums[tail] == nums[--tail]) {
                            }
                        } else {
                            while (head < tail && nums[head] == nums[++head]) {
                            }
                        }
                    }
                }
            }
            return r;
        }

        public List<List<Integer>> threeSum2(int[] num) {
            Arrays.sort(num);
            List<List<Integer>> res = new LinkedList<>();
            for (int i = 0; i < num.length - 2; i++) {
                if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
                    int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
                    while (lo < hi) {
                        if (num[lo] + num[hi] == sum) {
                            res.add(Arrays.asList(num[i], num[lo], num[hi]));
                            while (lo < hi && num[lo] == num[lo + 1]) lo++;
                            while (lo < hi && num[hi] == num[hi - 1]) hi--;
                            lo++;
                            hi--;
                        } else if (num[lo] + num[hi] < sum) lo++;
                        else hi--;
                    }
                }
            }
            return res;
        }
    }

}
