package linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// Sort List
// Given the head of a linked list, return the list after sorting it in ascending order.
public class LinkedListSort {

    // TODO. 数据的排序不可能低于O(NlogN)时间复杂度
    // head = [4,2,1,3] -> [1,2,3,4]
    // head = [-1,5,3,4,0] -> [-1,0,3,4,5]
    //
    // O(N + NlogN + N)
    // O(N)
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        Queue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));
        while (head != null) {
            heap.add(head);
            head = head.next;
        }

        ListNode node = heap.poll();
        ListNode tempHead = node;
        while (!heap.isEmpty()) {
            node.next = heap.poll();
            node = node.next;
        }
        node.next = null;
        return tempHead;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
