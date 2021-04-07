package com.cqx.leetcode.zailai.lookback.tree;

/**
 * https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
 */
public class 剑指Offer26_树的子结构 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    private boolean r = false;
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        dfs(A, B);
        return r;
    }

    private void dfs(TreeNode a, TreeNode b) {
        if (a == null) {
            return;
        }
        if (a.val == b.val && isSubTreeHelper1(a, b)) {
            r = true;
            return;
        }
        dfs(a.left, b);
        dfs(a.right, b);
    }

    public boolean isSubTreeHelper1(TreeNode A, TreeNode B) {
        if (A == null && B != null) {
            return false;
        }
        if (B == null){
            return true;
        }
        return A.val == B.val && isSubTreeHelper1(A.left, B.left) && isSubTreeHelper1(A.right, B.right);
    }


    private boolean isSubTreeHelper(TreeNode a, TreeNode b) {
        if (a == null && b != null) {
            return false;
        }
        if (a == null && b == null) {
            return true;
        }
        //a树即大树不为空  小树空了 那么也是true
        if (a != null && b == null) {
            return true;
        }
        return a.val == b.val && isSubTreeHelper(a.left, b.left) && isSubTreeHelper(a.right, b.right);

    }


}
