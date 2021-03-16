package com.cqx.leetcode.zailai.lookback.array;

/**
 * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 */
public class 剑指Offer22_链表中倒数第k个节点 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
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
    public ListNode getKthFromEnd(ListNode head, int k) {
        //设置哑节点
        ListNode yummy = new ListNode(0);
        yummy.next = head;
        //初始化快慢指针在哑节点处
        ListNode slow = yummy, fast = yummy;
        //快指针先走k步
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        //当快指针不为空时，快、慢指针同步向前走
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        //当快指针为空（指向链表末端即最后一节点的next域）时，慢指针所指节点即为所求
        return slow;

    }
}
