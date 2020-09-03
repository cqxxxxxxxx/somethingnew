package com.cqx.leetcode.zailai.lookback.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class M515_在每个树行中找最大值 {

    //您需要在二叉树的每一行中找到最大的值。
//
// 示例：
//
//
//输入:
//
//          1
//         / \
//        3   2
//       / \   \
//      5   3   9
//
//输出: [1, 3, 9]
//
// Related Topics 树 深度优先搜索 广度优先搜索
// 👍 87 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

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

    class Solution {
        public List<Integer> largestValues(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            List<Integer> r = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int max = Integer.MIN_VALUE;
                final int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    max = Math.max(max, node.val);
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                r.add(max);
            }
            return r;
        }
    }

}
