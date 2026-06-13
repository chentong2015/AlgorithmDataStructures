package linkedlist;

import linkedlist.node.ListNode;

public class LearnLinkedList1 {

    // Swap Nodes in Pairs
    // Swap every two adjacent nodes and return its head
    // Solve the problem without modifying the values in the list's nodes
    // head = [1,2,3,4] -> [2,1,4,3]
    //
    // 直接使用递归，每次递归都为它的调用函数返回Pair中的首节点
    // O(n) 递归调用造成栈空间的开销
    // O(1)
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode nextNode = head.next;
        head.next = swapPairs(nextNode.next);
        nextNode.next = head;
        // 返回新的头部Node
        return nextNode;
    }

    // Remove Duplicates from Sorted Linked List
    // 保证升序排列的链表，其中的int类型的值可正可负
    // head = [1,2,3,3,4,4,5] -> [1,2,3,4,5]
    // O(n) 最多递归n-1次的造成的时间复杂度
    // O(1)
    public ListNode removeDuplicated(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head.next;
        while (node != null && head.value == node.value) {
            node = node.next;
        }
        head.next = removeDuplicated(node);
        return head;
    }

    // TODO. 在链表头部添加dummy节点，方便后续的移动操作
    //  dummy -> head -> first -> second -> third -> ...
    // Remove Nth Node From End of List
    // Given the head of a linked list, remove the nth node from the end of the list
    // and return its head.
    // head = [1,2,3,4,5], n = 2  ->  [1,2,3,5] 1位置就是head位置
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // TODO. 需要统计node节点数目才能确定正向移动的距离
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        // 正确的位置是(L - n + 1)
        length = length - n;
        node = dummy;
        while (length > 0) {
            node = node.next;
            length--;
        }
        // 执行删除节点的操作，并返回正确的head头部节点
        node.next = node.next.next;
        return dummy.next;
    }
}
