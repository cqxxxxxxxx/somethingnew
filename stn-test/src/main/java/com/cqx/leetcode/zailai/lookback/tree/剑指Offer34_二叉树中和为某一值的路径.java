package com.cqx.leetcode.zailai.lookback.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
 * DFS 回溯
 */
public class 剑指Offer34_二叉树中和为某一值的路径 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private List<List<Integer>> r = new ArrayList<>();
    private Integer target = 0;

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        this.target = target;
        recursion(root, new ArrayList<Integer>(), 0);
        return r;
    }

    private void recursion(TreeNode root, List<Integer> solution, int sum) {
        if (root == null)  {
            return;
        }
        sum += root.val;
        solution.add(root.val);
        if (sum == target && root.left == null && root.right == null) {
            r.add(new ArrayList<>(solution));
            return;
        } else {
            recursion(root.left, solution, sum);
            recursion(root.right, solution, sum);
        }
        solution.remove(solution.size() - 1);
    }
}
