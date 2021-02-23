package com.cqx.leetcode.zailai.lookback.divideAndConquer;

import java.util.ArrayList;
import java.util.List;

public class M78_子集 {
    //给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
//
//
// 示例 2：
//
//
//输入：nums = [0]
//输出：[[],[0]]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10
// nums 中的所有元素 互不相同
//
// Related Topics 位运算 数组 回溯算法
// 👍 986 👎 0


    //leetcode submit region begin(Prohibit modification and deletion)
    public static class Solution {
        List<List<Integer>> r = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            int i = 0;
            recursion(nums, i, new ArrayList<>());
            return r;
        }

        private void recursion(int[] nums, int i, List<Integer> subset) {
            // terminate
            if (i >= nums.length) {
                r.add(new ArrayList<>(subset));
                return;
            }

            // process
            recursion(nums, i + 1, subset);
            subset.add(nums[i]);
            recursion(nums, i + 1, subset);

            //drill down

            //reverse
            subset.remove(subset.size() - 1);
        }


    }
//leetcode submit region end(Prohibit modification and deletion)


    public static class Solution1 {
        List<List<Integer>> r = new ArrayList<>();

        public List<List<Integer>> subsets(int[] nums) {
            int index = 0;
            recursion(nums, index, new ArrayList<Integer>());
            return r;
        }

        private void recursion(int[] nums, int index, ArrayList<Integer> tmp) {
            //terminate
            if (index >= nums.length) {
                r.add(new ArrayList<>(tmp));
                return;
            }

            //process
            recursion(nums, index + 1, tmp);
            tmp.add(nums[index]);
            recursion(nums, index + 1, tmp);

            //reverse
            tmp.remove(tmp.size() - 1);
        }


    }

}
