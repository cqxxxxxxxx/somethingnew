package com.cqx.leetcode.zailai.lookback.tree;

public class 翻转二叉树 {
    //翻转一棵二叉树。
//
// 示例：
//
// 输入：
//
//      4
//   /   \
//  2     7
// / \   / \
//1   3 6   9
//
// 输出：
//
//      4
//   /   \
//  7     2
// / \   / \
//9   6 3   1
//
// 备注:
//这个问题是受到 Max Howell 的 原问题 启发的 ：
//
// 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
// Related Topics 树
// 👍 556 👎 0


    //leetcode submit region begin(Prohibit modification and deletion)
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) {
                return root;
            }
            invertTree(root.left);
            invertTree(root.right);
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            return root;
        }

        public TreeNode invertTree2(TreeNode root) {
            invert(root);
            return root;
        }

        private void invert(TreeNode node) {
            //termination
            if (node == null) {
                return;
            }

            //processing
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            invertTree(node.left);
            invertTree(node.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
