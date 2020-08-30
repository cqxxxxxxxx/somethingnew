package com.cqx.leetcode.zailai.lookback.tree;

public class E101_对称二叉树 {

    //给定一个二叉树，检查它是否是镜像对称的。
//
//
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
//
//
//
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
//
//
//
//
// 进阶：
//
// 你可以运用递归和迭代两种方法解决这个问题吗？
// Related Topics 树 深度优先搜索 广度优先搜索
// 👍 990 👎 0


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
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return isSymmetric(root.left, root.right);
        }

        private boolean isSymmetric(TreeNode left, TreeNode right) {
            //termination
//            if ((left == null) != (right == null)) {
//                return false;
//            }
//            if ((left == null) == (right == null)) {
//                return true;
//            }
            if (left == null && right == null) {
                return true;
            }
            if (left == null || right == null) {
                return false;
            }
            //processing and drill down
            return left.val == right.val && isSymmetric(left.left, right.right)
                    && isSymmetric(left.right, right.left);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
