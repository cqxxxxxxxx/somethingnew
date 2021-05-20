package com.cqx.leetcode.zailai.lookback.linkedlist;

import com.cqx.sourcecodelearn.List;

import java.util.HashSet;
import java.util.Set;

/**
 *https://leetcode-cn.com/problems/linked-list-cycle-ii/
 */
public class M142_环形链表II {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public ListNode detectCycle1(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<ListNode> visited = new HashSet<>();
        while (head.next != null) {
            if (!visited.add(head)) {
                return head;
            }
            head = head.next;
        }
        return null;
    }

    /**
     * 快慢指针
     * 第一圈 相遇后  fast指向head，开始匀速遍历i，然后再次相遇就是入口点
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;

    }
}
