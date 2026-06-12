package linkedlist;

import linkedlist.node.ListNode;

// Merge Two Linked List
// Merge two sorted linked lists and return it as a sorted list
// Both l1 and l2 are sorted in non-decreasing order
// 1 -> 4 -> 8 -> 9
// 2 -> 5 -> 20
public class LinkedListMergeTwo {

    // TODO. 哪个链表的值小便移动哪个链表，直到遍历完其中一个链表
    //
    // O(n+m) 必须遍历链表中所有元素
    // O(1)   最后的结果直接利用原数据结构空间
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // dummy留住新链表的起使位置，作为创建出来的暂存节点
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                node.next = l2;
                break;
            } else if (l2 == null) {
                node.next = l1;
                break;
            } else {
                if (l1.value < l2.value) {
                    node.next = l1;
                    node = node.next;
                    l1 = l1.next;
                } else {
                    node.next = l2;
                    node = node.next;
                    l2 = l2.next;
                }
            }
        }
        return dummy.next;
    }
}
