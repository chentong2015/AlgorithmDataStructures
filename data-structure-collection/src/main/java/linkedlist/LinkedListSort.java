package linkedlist;

import linkedlist.node.ListNode;

// TODO. 链表排序也遵循常规Sorting排序算法逻辑
public class LinkedListSort {

    // Sort List
    // Given the head of a linked list, return the list after sorting it in ascending order
    // head = [-1,5,3,4,0] -> [-1,0,3,4,5]
    public ListNode sortList(ListNode head) {
        // 正确理解：1. Top-Bottom 自顶向下算法：将长的链表切分成小的一到两个链表进行处理，最后再合成(递归回来执行merge)的每个小结果 !!
        //         2. TODO: Bottom-Up 自底向上算法, 从底往上开始恢复
        //           O(nlog(n)) O(1)
        if (head == null || head.next == null) { // 至少有两个节点，都在没有排序的必要
            return head;
        }
        ListNode midNode = getMidNode(head);
        ListNode leftHead = sortList(head);
        ListNode rightHead = sortList(midNode);
        return merge(leftHead, rightHead);
    }

    // TODO: 链表的排序合成，构造一个dummyHead作为结果链表head结点的指引
    // 将原始问题简化成合成两个有序链表的问题, 比较算法排序 !!
    // leftHead : -1 -> 5
    // rightHead:  0 -> 3 -> 4
    // node:      -1 -> 0 -> 3 -> 4 -> 5
    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;               // tail是合成出来的整个链条的最尾部的节点
        while (list1 != null && list2 != null) {
            if (list1.value < list2.value) {     // 如果list1的值更小，则将小节点添加到总合成链表的最后，反之添加list2的节点 !!
                tail.next = list1;               // list1和list2各自的节点标识往后移动
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        // 如果因为while循环导致其中一个链表读到最后，则将另一个链表剩余的部分接上 !!
        tail.next = (list1 != null) ? list1 : list2;
        return dummyHead.next;
    }

    // -1 -> 5 -> 3 -> 4      => midNode = 5
    // -1 -> 5 -> 3 -> 4 -> 0 => midNode = 3
    private ListNode getMidNode(ListNode head) {
        ListNode midNode = null;
        while (head != null && head.next != null) {
            midNode = (midNode == null) ? head : midNode.next; // 中间Node每移动一步，head往前移动两步，最终取到中间的位置
            head = head.next.next;
        }
        ListNode middle = midNode.next; // 在midNode将整个链表切分成两段，后面一段的head就是midNode的下一个节点
        middle.next = null;             // 截断前面一个子链表
        return middle;
    }
}