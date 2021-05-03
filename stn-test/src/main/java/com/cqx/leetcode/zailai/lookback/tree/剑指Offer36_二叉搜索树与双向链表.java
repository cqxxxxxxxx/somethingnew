package com.cqx.leetcode.zailai.lookback.tree;

/**
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/solution/zhong-xu-bian-li-gou-zao-shuang-xiang-li-w1cx/
 */
public class 剑指Offer36_二叉搜索树与双向链表 {


    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    Node head = null;
    Node tail = null;
    /**
     * 中序遍历
     * 指针问题
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if(root == null) return root;
        dfs(root);
        head.left = tail;
        tail.right = head;
        return head;
    }


    private void dfs(Node node) {
        if (node != null) {
            dfs(node.left);
            if (head == null) {
                head = tail = node;
            } else {
                tail.right = node;
                node.left = tail;
                tail = node;
            }
            dfs(node.right);
        }
    }

}

