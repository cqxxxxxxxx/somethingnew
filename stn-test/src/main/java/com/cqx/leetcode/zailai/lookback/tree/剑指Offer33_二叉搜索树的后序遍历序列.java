package com.cqx.leetcode.zailai.lookback.tree;

/**
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 */
public class 剑指Offer33_二叉搜索树的后序遍历序列 {

    /**
     * 后序遍历
     * 最后一个是根节点
     * 从前往后找第一个比根节点大的，划分左右区块，左区块都比根节点小，右区块都比根节点大
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null) {
            return false;
        }
        return recursion(postorder, 0, postorder.length - 1);
    }

    private boolean recursion1(int[] postorder, int start, int end) {
        if (start >= end) {
            return true;
        }
        int splitIndex = start;
        while (postorder[splitIndex] < postorder[end]) {
            splitIndex++;
        }
        int tmp = splitIndex;
        while (tmp < end) {
            if (postorder[tmp++] < postorder[end]) {
                return false;
            }
        }
        /**
         * splitIndex-1 是左树区块，如果不减 则splitIndex节点 左右区块重复了一边
         */
        return recursion(postorder, start, splitIndex - 1) && recursion(postorder, splitIndex, end - 1);

    }

    private boolean recursion(int[] postorder, int start, int end) {
        if (start >= end) {
            return true;
        }
        int rootVal = postorder[end];
        int splitIndex = start;
        for (int i = start; i < end; i++) {
            splitIndex = i;
            if (postorder[i] > rootVal) {
                break;
            }
        }
        for (int i = splitIndex; i < end; i++) {
            if (postorder[i] < rootVal) {
                return false;
            }
        }
        return recursion(postorder, start, splitIndex) && recursion(postorder, splitIndex, end--);

    }
}
