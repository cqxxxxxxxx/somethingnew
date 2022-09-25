package com.cqx.leetcode.zailai.lookback.backtrack;

import com.google.common.hash.BloomFilter;

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

    private final List<List<Integer>> R = new ArrayList<>();

    private void recursion(int i, int[] s, ArrayList<Integer> tmp) {
        if (i >= s.length) {
            R.add(new ArrayList<>(tmp));
            return;
        }
        //不选
        recursion(i + 1, s, tmp);

        //选
        tmp.add(s[i]);
        recursion(i + 1, s, tmp);
        tmp.remove(tmp.size() - 1);
    }

    public List<List<Integer>> subsets(int[] nums) {
        recursion(0, nums, new ArrayList<Integer>());
        return R;
    }

}
