package com.cqx.leetcode.zailai.lookback.linkedlist;



import java.util.HashMap;
import java.util.Map;

public class 剑指Offer35_复杂链表的复制 {


    // Definition for a Node.
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * originNode -> newCopiedNode
     */
    private Map<Node, Node> nodeMap = new HashMap<>();
    public Node copyRandomList(Node head) {
        Node headCopy = copy(head);
        return headCopy;
    }

    private Node copy(Node node) {
        if(node == null) {
            return null;
        }
        Node nodeCopy = new Node(node.val);
        nodeMap.put(node, nodeCopy);
        if (node.next != null) {
            if (nodeMap.get(node.next) == null) {
                nodeCopy.next= copy(node.next);
            } else {
                nodeCopy.next = nodeMap.get(node.next);
            }
        }
        if (node.random != null) {
            if (nodeMap.get(node.random) == null) {
                nodeCopy.random= copy(node.random);
            } else {
                nodeCopy.random = nodeMap.get(node.random);
            }
        }
        return nodeCopy;
    }


}
