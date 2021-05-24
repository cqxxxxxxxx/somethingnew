package com.cqx.leetcode.zailai.lookback.tree;

/**
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * <p>
 * 提醒一下，二叉搜索树满足下列约束条件：
 * <p>
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * <p>
 * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
 */
public class M538_把二叉搜索树转换为累加树 {

    private int nodeSum = 0;

    /**
     * 二叉树中序遍历 左-中-右 递增的
     * 倒着中序遍历 那么 右-中-左 是不是就递减了
     * 期间维护nodeSum，记录遍历过的节点的总和，用于计算节点值
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    /**
     * @param node
     * @return
     */
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.right);
        node.val = nodeSum + node.val;
        nodeSum = node.val;
        dfs(node.left);
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
