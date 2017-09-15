package com.cqx.bst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by cqx on 2017/2/9.
 */
public class BST<T> {

    private Node<T> root;       //树的根节点
    private Node<T> rootCopy;   //根节点的copy
    private Comparator<T> comparator;


    /**
     * 查找
     *
     * @param t
     * @return
     */
    public Node<T> contains(T t) {
        rootCopy = root;
        while (true) {
            if (rootCopy == null) {
                break;
            }
            if (comparator.compare(t, rootCopy.value) == 0) {    //找到了
                return rootCopy;
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

    /**
     * 删除节点
     * @param t
     * @return
     */
    public void remove(T t) {
        Node<T> node = contains(t);
        if (node == null){
            return ;
        }
        //无左右子节点， 即是个叶子节点
        if (node.left == null && node.right == null){
            if (node == node.parent.left){
                node.parent.left = null;
            }else {
                node.parent.right = null;
            }
        }
        //删除节点有左子节点，无右子节点
        if (node.left != null && node.right == null){
            if (node == node.parent.left){
                node.parent.left = node.left;
                node.left.parent = node.parent.left;
            }else {
                node.parent.right = node.left;
                node.left.parent = node.parent.right;
            }
        }
        //删除节点有右子节点，无左子节点
        if (node.left == null && node.right != null){
            if (node == node.parent.left){
                node.parent.left = node.right;
                node.right.parent = node.parent.left;
            }else {
                node.parent.right = node.right;
                node.right.parent = node.parent.right;
            }
        }
        //删除节点左右子节点都存在
        if (node.left != null && node.right != null){
            Node<T> successor = node.right;     //删除节点的后继节点
            remove(successor.value);            //删除后继节点
            node.value = successor.value;       //吧后继节点取代删除节点
        }

    }

    /**
     * 树的节点
     * @param <T>
     */
    static class Node<T> {
        public T value;         //节点值
        public Node<T> parent;  //父节点
        public Node<T> left;    //左子节点
        public Node<T> right;   //右子节点
        public boolean visited = false; //是否访问过 用于前中后序遍历

        public Node(T t) {
            value = t;
        }
    }

    /**
     * 私有构造器
     * @param builder
     */
    private BST(Builder builder) {
        root = builder.root;
        comparator = builder.comparator;
    }

    /**
     * Builder模式
     * @param <T>
     */
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

        String find = bst.contains("ccc").value;
    }
}
