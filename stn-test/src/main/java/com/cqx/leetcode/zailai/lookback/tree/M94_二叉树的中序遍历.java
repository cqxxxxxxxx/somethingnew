package com.cqx.leetcode.zailai.lookback.tree;

import java.util.ArrayList;
import java.util.List;

public class M94_二叉树的中序遍历 {
    //给定一个二叉树，返回它的中序 遍历。
//
// 示例:
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2]
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
// Related Topics 栈 树 哈希表
// 👍 649 👎 0


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
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> r = new ArrayList<>();
            view(root, r);
            return r;
        }

        private void view(TreeNode node, List list) {
            if (node == null) {
                return;
            }
            view(node.left, list);
            list.add(node.val);
            view(node.right, list);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
