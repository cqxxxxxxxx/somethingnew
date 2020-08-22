package com.cqx.leetcode.zailai;

import java.util.ArrayList;
import java.util.List;

public class 今天20200819 {

    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }


    /**
     * 78. 子集
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return null;
        }
        dfs(result, nums, new ArrayList<>(), 0);
        return result;
    }

    private void dfs(List<List<Integer>> result, int[] nums, List<Integer> list, int index) {
        //terminate
        if (nums.length == index + 1) {
            result.add(list);
            return;
        }
        //process
        dfs(result, nums, list, index + 1);
        list.add(nums[index]);
        dfs(result, nums, list, index + 1);
        result.add(list);
        return;
    }
}
