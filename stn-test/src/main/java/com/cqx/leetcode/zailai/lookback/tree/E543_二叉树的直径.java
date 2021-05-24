package com.cqx.leetcode.zailai.lookback.tree;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * <p>
 *  
 * <p>
 * 示例 :
 * 给定二叉树
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * <p>
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 */
public class E543_二叉树的直径 {
    private int res;

    /**
     * 节点最大数量= 左边子树最大节点数 + 右子树最大节点数 + 1（自身节点）
     * 节点最大长度 = 节点数量 - 1
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        res = 1;
        dfs(root);
        return res - 1;
    }

    /**
     * 返回以该节点为根，最大经过的节点数量
     *
     * @param node
     * @return
     */
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int l = dfs(node.left);
        int r = dfs(node.right);
        res = Math.max(l + r + 1, res);
        return Math.max(l, r) + 1;
    }


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
}
