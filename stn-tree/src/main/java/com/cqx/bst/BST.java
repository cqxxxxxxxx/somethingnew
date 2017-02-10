package com.cqx.bst;

import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by cqx on 2017/2/9.
 */
public class BST<T> {

    private Node<T> root;
    private Node<T> rootCopy;

    private Comparator<T> comparator;


    /**
     * 查找
     *
     * @param t
     * @return
     */
    public Node<T> findNode(T t) {
        rootCopy = root;
        while (true) {
            if (rootCopy == null) {
                break;
            }
            if (comparator.compare(t, rootCopy.value) == 0) {    //找到了
                return root;
            }
            if (comparator.compare(t, rootCopy.value) < 0) { //比父节点小
                rootCopy = rootCopy.left;
            }
            if (comparator.compare(t, rootCopy.value) > 0) { //比节点大
                rootCopy = rootCopy.right;
            }
        }
        return null;
    }

    /**
     * 插入
     *
     * @param t
     */
    public void add(T t) {
        rootCopy = root;
        Node<T> node = new Node<T>(t);
        Node<T> parent = null;
        while (true) {
            if (rootCopy == null) break;
            parent = rootCopy;
            if (comparator.compare(t, rootCopy.value) <= 0) {    //比该节点小或者等于 向左查找
                rootCopy = rootCopy.left;
            } else {
                rootCopy = rootCopy.right;
            }
        }
        if (parent != null) {
            if (comparator.compare(t, parent.value) <= 0) {  //比父节点小或者等于 放左边
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
    }


    static class Node<T> {
        public T value;         //节点值
        public Node<T> parent;  //父节点
        public Node<T> left;    //左子节点
        public Node<T> right;   //右子节点

        public Node(T t) {
            value = t;
        }
    }

    private BST(Builder builder) {
        root = builder.root;
        comparator = builder.comparator;
    }

    public static class Builder<T> {
        private Comparator<T> comparator;
        private Node<T> root;

        public Builder(T t) {
            root = new Node<T>(t);
        }

        public Builder withComparator(Comparator<T> comparator) {
            this.comparator = comparator;
            return this;
        }

        public BST build() {
            return new BST(this);
        }
    }


    public static void main(String[] args) {
        String root = "a";
        Comparator<String> comparator =
                (str1, str2) -> {
                    if (str1.length() < str2.length()) return -1;
                    else if (str1.length() == str2.length()) return 0;
                    else return 1;
                };
        BST<String> bst = new BST.Builder(root)
                .withComparator(comparator)
                .build();
        bst.add("bb");
        bst.add("ccc");
        bst.add("dddd");
        bst.add("eee");
        bst.add("c");

        String find = bst.findNode("ccc").value;
    }
}
