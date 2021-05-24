package com.cqx.leetcode.zailai.lookback.sort;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 进阶：
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * <p>
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * <p>
 * 链接：https://leetcode-cn.com/problems/sort-list
 */
public class M148_排序链表 {
    public static void main(String[] args) {
        ListNode root = new ListNode(4);
        root.next = new ListNode(2);
        root.next.next = new ListNode(1);
        root.next.next.next = new ListNode(3);
        M148_排序链表 o = new M148_排序链表();
        ListNode listNode = o.sortList(root);
    }

    /**
     * 归并排序 时间复杂度O(nlogn)， 空间复杂度 O(1) 链表只要调整指针不需要额外空间
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        return head == null ? head : mergeSort(head);
    }

    private ListNode mergeSort(ListNode node) {
        //拆分到只有一个元素 返回
        if (node.next == null) {
            return node;
        }
        ListNode slow = node;
        ListNode fast = node;
        //快慢指针找中点，奇数中间，偶数左边
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode leftHead = mergeSort(node);
        ListNode rightHead = mergeSort(tmp);
        //归并阶段
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (leftHead != null || rightHead != null) {
            if (leftHead != null && rightHead != null) {
                if (leftHead.val < rightHead.val) {
                    cur.next = leftHead;
                    leftHead = leftHead.next;
                } else {
                    cur.next = rightHead;
                    rightHead = rightHead.next;
                }
            } else if (leftHead != null) {
                cur.next = leftHead;
                leftHead = leftHead.next;
            } else if (rightHead != null) {
                cur.next = rightHead;
                rightHead = rightHead.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }


//================ 暴力 超时了 =======================+

    /**
     * 构建个新链表，遍历老的，进行插入操作
     * 时间复杂度 O(N*N) 每个插入操作都需要N次比较
     *
     * @param head
     * @return
     */
    public ListNode sortList1(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = new ListNode(head.val);
        while (head.next != null) {
            int val = head.next.val;
            ListNode cur = dummy;
            //找到第一个大于val的节点，插入到他之前
            while (cur.next != null && cur.next.val < val) {
                cur = cur.next;
            }
            ListNode tmp = cur.next;
            cur.next = new ListNode(val);
            cur = cur.next;
            cur.next = tmp;
            head = head.next;
        }
        return dummy.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
