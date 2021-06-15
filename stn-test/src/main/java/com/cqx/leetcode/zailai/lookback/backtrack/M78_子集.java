package com.cqx.leetcode.zailai.lookback.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * <p>
 * 链接：https://leetcode-cn.com/problems/subsets
 */
public class M78_子集 {


    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> tmp = new ArrayList<>();
        backtrack(nums, tmp, 0);
        return res;
    }

    public void backtrack(int[] nums, List<Integer> tmp, int start) {
        res.add(new ArrayList<>(tmp)); // 所有的都要，所以不需要递归终止条件
        for (int i = start; i < nums.length; i++) {

            tmp.add(nums[i]);

            backtrack(nums, tmp, i + 1);

            tmp.remove(tmp.size() - 1);
        }
    }

//    public List<List<Integer>> subsets(int[] nums) {
//        int index = 0;
//        recursion(nums, index, new ArrayList<Integer>());
//        return r;
//    }
//
//    private void recursion(int[] nums, int index, ArrayList<Integer> tmp) {
//        //terminate
//        if (index >= nums.length) {
//            r.add(new ArrayList<>(tmp));
//            return;
//        }
//
//        //process
//        recursion(nums, index + 1, tmp);
//        tmp.add(nums[index]);
//        recursion(nums, index + 1, tmp);
//
//        //reverse
//        tmp.remove(tmp.size() - 1);
//    }
}
