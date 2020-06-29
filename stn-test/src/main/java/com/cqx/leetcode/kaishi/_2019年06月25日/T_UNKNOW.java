package com.cqx.leetcode.kaishi._2019年06月25日;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/6/25
 */
public class T_UNKNOW {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class Solution {
        public ListNode middleNode(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode step1 = head, step2 = head;
            while (true) {
                step2 = step2.next;
                if (step2 == null) {
                    return step1;
                }
                step1 = step1.next;
                step2 = step2.next;
                if (step2 == null) {
                    return step1;
                }
            }
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(new Solution().middleNode(head).val);

    }
}
