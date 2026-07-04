package linkedlist;

import linkedlist.node.ListNode;

import java.util.*;

public class MergeKSortedList {

    // TODO. 直接利用PriorityQueue堆排序
    // Merge k Sorted Lists
    // An array of k linked-lists lists, each linked-list is sorted in ascending order
    // Merge all the linked-lists into one sorted linked-list and return it
    //
    // lists = [[1,4,5],[1,3,4],[2,6],[7]]
    // output = [1,1,2,3,4,4,5,6,7]
    //
    // O(N)+O(N*logN)+O(N)
    // O(N)
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.add(list);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();

            current.next = smallest;
            current = current.next;
            if(smallest.next != null){
                minHeap.add(smallest.next);
            }
        }
        return dummy.next;
    }

    // TODO. 直接对Node值排序, 最后构建成结果列表
    public ListNode mergeKSortedLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        List<Integer> values = new ArrayList<>();
        for (ListNode list : lists) {
            ListNode node = list;
            while (node != null) {
                values.add(node.value);
                node = node.next;
            }
        }
        if (values.isEmpty()) {
            return null;
        }
        Collections.sort(values);

        ListNode head = new ListNode(values.get(0));
        ListNode current = head;
        for (int index = 1; index < values.size(); index++) {
            current.next = new ListNode(values.get(index));
            current = current.next;
        }
        return head;
    }
}