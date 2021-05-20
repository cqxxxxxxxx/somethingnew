package com.cqx.leetcode.zailai.lookback.linkedlist;

public class M19_删除链表的倒数第N个结点 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }

    /**
     * 1. 一次遍历统计长度n， 然后第二次遍历 n-k个节点
     * 2. 两个指针，第一个先走k步，然后两个一起一步一步的走
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int k) {
        //设置哑节点
        ListNode yummy = new ListNode(0, head);
        yummy.next = head;
        //初始化快慢指针在哑节点处
        ListNode slow = yummy, fast = yummy, slowPre = yummy;
        //快指针先走k步
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        //当快指针不为空时，快、慢指针同步向前走
        while (fast != null) {
            fast = fast.next;
            slowPre = slow;
            slow = slow.next;
        }
        //当快指针为空（指向链表末端即最后一节点的next域）时，慢指针所指节点即为所求
        slowPre.next = slow.next;
        return yummy.next;

    }



    public ListNode removeNthFromEnd1(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < k; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

}
