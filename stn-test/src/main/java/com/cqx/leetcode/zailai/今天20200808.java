package com.cqx.leetcode.zailai;

public class 今天20200808 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public boolean isValidBST(TreeNode root) {
        return recursion(root);
    }

    /**
     * 错了
     * TODO 错了
     *
     * @param root
     * @return
     */
    private boolean recursion(TreeNode root) {
        //terminator
        if (root == null) {
            return true;
        }
        //process
        if (root.left != null) {
            if (root.left.val >= root.val) {
                return false;
            }
        }
        if (root.right != null) {
            if (root.right.val <= root.val) {
                return false;
            }
        }
        return recursion(root.left) && recursion(root.right);
    }


}
