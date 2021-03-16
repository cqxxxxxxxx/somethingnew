package com.cqx.leetcode.zailai.lookback.linkedlist;

public class 剑指Offer18_删除链表的节点 {
    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                return dummy.next;
            }
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }
}
