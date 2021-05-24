package com.cqx.leetcode.zailai.lookback.tree;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class M105_从前序与中序遍历序列构造二叉树 {

    /**
     * dfs
     * 这个要学会
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = recursion(preorder, 0, inorder.length, inorder, 0, inorder.length);
        return root;
    }

    private TreeNode recursion(int[] preorder, int pStart, int pEnd, int[] inorder, int iStart, int iEnd) {
        //termination
        if (pStart >= pEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pStart]);
        int rootIndex = -1;
        //TODO 这边可以用hashMap优化啊
        for (int i = iStart; i <= iEnd; i++) {
            if (inorder[i] == preorder[pStart]) {
                rootIndex = i;
                break;
            }
        }
        int leftNum = rootIndex - iStart;
        TreeNode leftChild = recursion(preorder, pStart + 1, pStart + leftNum + 1, inorder, iStart, rootIndex - 1);
        TreeNode rightChild = recursion(preorder, pStart + leftNum + 1, pEnd, inorder, rootIndex + 1, iEnd);
        root.left = leftChild;
        root.right = rightChild;
        return root;
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
