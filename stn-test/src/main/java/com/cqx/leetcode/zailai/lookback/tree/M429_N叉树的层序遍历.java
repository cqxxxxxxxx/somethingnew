package com.cqx.leetcode.zailai.lookback.tree;

import java.util.*;

public class M429_N叉树的层序遍历 {
    //给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
//
// 例如，给定一个 3叉树 :
//
//
//
//
//
//
//
// 返回其层序遍历:
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
//
//
//
//
// 说明:
//
//
// 树的深度不会超过 1000。
// 树的节点总数不会超过 5000。
// Related Topics 树 广度优先搜索
// 👍 107 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }


    class Solution {
        /**
         * BFS 广度优先搜索
         * DFS 深度优先搜索
         *
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrder(Node root) {
            if (root == null) {
                return Collections.EMPTY_LIST;
            }
            List<List<Integer>> r = new ArrayList<>();
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (queue.size() > 0) {
                List<Integer> inner = new ArrayList<>();
                final int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Node node = queue.poll();
                    inner.add(node.val);
                    for (Node child : node.children) {
                        queue.add(child);
                    }
                }
                r.add(inner);
            }
            return r;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
