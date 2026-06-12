package linkedlist;

import linkedlist.node.ListNode;

// Delete the Middle Node of a Linked List
// You are given the head of a linked list.
// Delete the middle node, and return the head of the modified linked list.
//
// For n = 1, 2, 3, 4, and 5,
// the middle nodes are 0, 1, 1, 2, and 2, respectively.
//
// The number of nodes in the list is in the range [1, 10^5].
public class LinkedListDeleteMiddle {

    // TODO. Fast and Slow Pointer 双指针移动金典算法(双倍移动)
    // head = [1,3,4,7,1,2,6] -> [1,3,4,1,2,6]
    // head = [1,2,3,4] ->  [1,2,4]
    // head = [1] -> []
    //
    // O(N) O(1)
    public ListNode deleteMiddle(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }

        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // 位置slow的节点就是middle节点
        prev.next = prev.next.next;
        return head;
    }


    // TODO. 第一遍找出总数, 第二遍删除中间节点
    // O(N+N) O(1)
    public ListNode deleteMiddle2(ListNode head) {
        if (head == null || head.next == null) {
            // 如果一个节点，则直接删除
            return null;
        }

        ListNode dummy = head;
        int countNode = 0;
        while (head != null) {
            countNode++;
            head = head.next;
        }

        int indexMiddle = countNode / 2;
        ListNode targetNode = dummy;
        ListNode current = targetNode;

        // 计算往后移动的次数, 让target节点被删除
        int index = 0;
        while (index < indexMiddle) {
            current = targetNode;
            targetNode = targetNode.next;
            index++;
        }

        // 删除目标节点, 重制指针指向
        if (targetNode == null) {
            current.next = null;
        } else {
            current.next = targetNode.next;
        }
        return dummy;
    }

}
