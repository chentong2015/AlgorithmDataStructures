package linkedlist;

import linkedlist.node.ListNode;

public class LinkedListCycle {

    // TODO. 龟兔赛跑原理 -> Fast and Slow Pointer
    // 如果链表中出现循环，则必然跑得快的会追上跑得慢的
    // pos表示链表尾部节点的下一个节点，并非作为参数使用
    //
    // Linked List Cycle
    // Given the head of a linked list, determine if the linked list has a cycle in it
    // pos is -1 or a valid index in the linked-list
    // head = [3,2,0,-4], pos = 1 -> true
    //
    // O(n) O(1)
    public boolean hasCycle(ListNode head) {
        ListNode node = head;
        while (head != null && head.next != null) {
            node = node.next;      //
            head = head.next.next; //
            if (head != null && head.equals(node)) {
                return true;
            }
        }
        return false;
    }
}
