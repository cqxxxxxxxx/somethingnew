package com.cqx.leetcode.zailai.lookback.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/solution/
 */
public class 剑指Offer54_二叉搜索树的第k大节点 {



      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }


    /**
     * BST 中序遍历 递增
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        List<Integer> sortResult = new ArrayList<>();
        inOrderSort(root, sortResult);
        return sortResult.get(sortResult.size() - k);
    }

    private void inOrderSort(TreeNode root, List<Integer> sortResult) {
        if (root == null) {
            return;
        }
        inOrderSort(root.left, sortResult);
        sortResult.add(root.val);
        inOrderSort(root.right, sortResult);
    }

//    public int kthLargest1(TreeNode root, int k) {
//        List<Integer> sortResult = new ArrayList<>();
//        inOrderSort1(root, sortResult);
//        return sortResult.get(sortResult.size() - k);
//    }
//
//    private int inOrderSort1(TreeNode root, int k) {
//        if (root == null) {
//        }
//        inOrderSort1(root.right, k);
//        if (--k == 0) {
//            return root.val;
//        }
//        inOrderSort1(root.left, k);
//    }
}
