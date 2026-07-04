package linkedlist;

import linkedlist.node.ListNode;

// Reverse Linked List
// Given the head of a singly linked list, reverse the list, and return the reversed list
// input: 1 <-  3  -> 6 -> 7 -> 10
//       curr  head
// output: 10 -> 7 -> 6 -> 3 -> 1
public class LinkedListReverse {

    // TODO. “递归循环”然后反转指向, 直到最后节点
    //
    // O(N) O(1)
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 初始化一定要设置好指针指向 !!
        ListNode current = head;
        head = head.next;
        current.next = null;

        ListNode temp;
        while (head != null) {
            temp = head.next; // 临时保存后续的节点

            head.next = current;
            current = head; // 将current当前指向节点往后移动
            head = temp;    // 将head移动到后续节点
        }
        return current;
    }
}
