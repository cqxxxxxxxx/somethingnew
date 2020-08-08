package com.cqx.leetcode.zailai;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 今天20200804 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        inorder(root, r);
        return r;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        preorder(root, r);
        return r;
    }

    private void preorder(TreeNode node, List list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preorder(node.left, list);
        preorder(node.right, list);
    }

    private void inorder(TreeNode node, List list) {
        if (node == null) {
            return;
        }
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

//  ======================


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

    ;

    public List<Integer> postorder(Node root) {
        List<Integer> r = new ArrayList<>();
        postOrder(root, r);
        return r;
    }

    private void postOrder(Node node, List list) {
        if (node == null) {
            return;
        }
        List<Node> children = node.children;
        if (children != null) {
            for (Node child : children) {
                postOrder(child, list);
            }
        }
        list.add(node.val);
    }

    public List<Integer> preorder(Node root) {
        List<Integer> r = new ArrayList<>();
        preorder(root, r);
        return r;
    }

    private void preorder(Node node, List list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        List<Node> children = node.children;
        if (children != null) {
            for (Node child : children) {
                preorder(child, list);
            }
        }
    }


    //  层序遍历
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);
                queue.addAll(node.children);
            }
            result.add(level);
        }
        return result;
    }


}
