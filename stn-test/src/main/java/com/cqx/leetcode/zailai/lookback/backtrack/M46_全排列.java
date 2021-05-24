package com.cqx.leetcode.zailai.lookback.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * <p>
 * https://leetcode-cn.com/problems/permutations/
 */
public class M46_全排列 {


    /**
     * 回溯 + used[]数组枝剪
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        recursion1(used, nums, new ArrayList<Integer>(), r);
        return r;
    }

    private void recursion1(boolean[] used, int[] nums, ArrayList<Integer> tmp, List<List<Integer>> r) {
        if (tmp.size() == nums.length) {
            r.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (used[i]) {
                continue;
            }
            used[i] = true;
            tmp.add(val);
            recursion1(used, nums, tmp, r);
            used[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }


//===================================== 用的set，其他一样，性能差=======

    /**
     * //set 改成数组 性能好很多
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        HashSet<Integer> used = new HashSet<>();
        recursion(used, nums, new ArrayList<Integer>(), r);
        return r;
    }

    private void recursion(HashSet<Integer> used, int[] nums, ArrayList<Integer> tmp, List<List<Integer>> r) {
        if (used.size() == nums.length) {
            r.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (used.contains(val)) {
                continue;
            }
            used.add(val);
            tmp.add(val);
            recursion(used, nums, tmp, r);
            used.remove(val);
            tmp.remove(tmp.size() - 1);
        }
    }
}
