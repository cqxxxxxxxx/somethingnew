package com.cqx.leetcode.zailai.lookback.tree;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/cousins-in-binary-tree/
 */
public class E993_二叉树的堂兄弟节点 {

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

    public boolean isCousins(TreeNode root, int x, int y) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean findX = false, findY = false;
            for (int i = 0; i < size; i++) {
                boolean findXInRound = false, findYInRound = false;
                TreeNode node = queue.pop();
                if (node.left != null) {
                    if (node.left.val == x) {
                        findX = true;
                        findXInRound = true;
                    }
                    if (node.left.val == y) {
                        findY = true;
                        findYInRound = true;
                    }
                    queue.add(node.left);
                }
                if (node.right != null) {
                    if (node.right.val == x) {
                        findX = true;
                        findXInRound = true;
                    }
                    if (node.right.val == y) {
                        findY = true;
                        findYInRound = true;
                    }
                    queue.add(node.right);
                }
                if (findXInRound && findYInRound) {
                    return false;
                }
                if (findX && findY) {
                    return true;
                }
            }
            if (findX && findY) {
                return true;
            }
        }
        return false;
    }

}
