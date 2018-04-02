package com.cqx.leetcode;

/**
 * Created by BG307435 on 2018/4/2.
 */
public class DAY6 {

    /**
     * https://leetcode.com/problems/linked-list-cycle/discuss/44489/O(1)-Space-Solution
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        //solution 1
//        Set<ListNode> nodeSet = new HashSet<>();
//        if (head == null) {
//            return false;
//        }
//
//        for (ListNode tmp = head.next; tmp != null; tmp = tmp.next) {
//            ListNode next = tmp;
//            if (nodeSet.contains(next)) {
//                return true;
//            }
//            nodeSet.add(next);
//        }
//        return false;


//      炒来的
//        1、Use two pointers, walker and runner.
//        2、walker moves step by step. runner moves two steps at time.
//        3、if the Linked List has a cycle walker and runner will meet at some
//        point.
        if (head == null) return false;
        ListNode walker = head;
        ListNode runner = head;
        while (runner.next != null && runner.next.next != null) {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner) return true;
        }
        return false;
    }

    //Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
