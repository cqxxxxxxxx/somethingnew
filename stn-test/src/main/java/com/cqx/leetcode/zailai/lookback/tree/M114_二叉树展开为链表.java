package com.cqx.leetcode.zailai.lookback.tree;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *  
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * <p>
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 */
public class M114_二叉树展开为链表 {

    public static void main(String[] args) {
        M114_二叉树展开为链表 o = new M114_二叉树展开为链表();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        o.flatten(root);

    }

    private TreeNode end;

    /**
     * 前序遍历的同时 展开为链表
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        dfs(root);
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        end = node;
        TreeNode rightTmp = node.right;
        if (node.left != null) {
            dfs(node.left);
            node.right = node.left;
        }
        if (rightTmp != null) {
            end.right = rightTmp;
            dfs(rightTmp);
        }
        node.left = null;
    }


    public static class TreeNode {
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

}
