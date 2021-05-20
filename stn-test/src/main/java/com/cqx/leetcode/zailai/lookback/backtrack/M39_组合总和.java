package com.cqx.leetcode.zailai.lookback.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combination-sum/
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2：
 * <p>
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *  
 */
public class M39_组合总和 {

    private List<List<Integer>> r = new ArrayList<>();

    /**
     * TODO 目前这样做会有重复， 比如结果输出是 [[2,2,3],[2,3,2],[3,2,2],[7]]， 实际只需要[[2,2,3],[7]]
     * 解决：去重
     * 1. hashMap去重, 怎么去重？排序后拼接字符串 然后去重。。。。只能这样
     *
     * 2. ？？参考 https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        recursion(target, candidates, new ArrayList<>());
        return r;
    }

    private void recursion(int target, int[] candidates, ArrayList<Integer> tmp) {
        if (target == 0) {
            r.add(new ArrayList<>(tmp));
        }
        if (target < 0) {
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            tmp.add(candidates[i]);
            recursion(target - candidates[i], candidates, tmp);
            //backtrace
            tmp.remove(tmp.size() - 1);
        }
    }
}
