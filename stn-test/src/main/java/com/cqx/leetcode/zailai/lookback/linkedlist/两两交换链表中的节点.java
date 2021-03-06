package com.cqx.leetcode.zailai.lookback.linkedlist;

public class 两两交换链表中的节点 {
//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
//
//
// 示例:
//
// 给定 1->2->3->4, 你应该返回 2->1->4->3.
//
// Related Topics 链表
// 👍 606 👎 0

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode first = head;
            ListNode second = head.next;
            first.next = swapPairs(second.next);
            second.next = first;
            return second;
        }

        public ListNode swapPairs1(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode first = head;
            ListNode second = head.next;
            first.next = swapPairs(second.next);
            second.next = first;
            return second;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
