package prefix_sum;

import java.util.HashMap;

// Remove Zero Sum Consecutive Nodes from Linked List
// Given the head of a linked list, we repeatedly delete consecutive sequences of nodes
// that sum to 0 until there are no such sequences.
// After doing so, return the head of the final linked list.
// You may return any such answer.
//
// The given linked list will contain between 1 and 1000 nodes.
// Each node in the linked list has -1000 <= node.val <= 1000.
public class RemoveZeroSumNodes {

    // TODO. ListNodes(i,j)= PrefixNodes(0,j) - PrefixNodes(0,i)
    // head -> 1,2,-3,3,1
    // head -> [3,1]
    // head -> [1,2,1]
    //
    // head -> 1,2,3,-3,4
    // head -> [1,2,4]
    //
    // head -> 1,2,3,-3,-2
    // head -> [1]
    //
    // O(n)
    // O(n)
    public ListNode removeZeroSumSubLists(ListNode head) {
        // 用于记录最后返回的head头部节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // TODO. PrefixSum -> ListNode 哈希映射值为前缀和对应的Node节点
        HashMap<Integer, ListNode> map = new HashMap<>();
        map.put(0, dummy);

        int prefixSum = 0;
        while (head != null) {
            prefixSum += head.val;

            // 一旦prefixSum已经存在于map中，则说明中间有片段和为0
            // 需要删除Node节点，同时删除对应的prefixSum历史值
            if (map.containsKey(prefixSum)) {
                ListNode start = map.get(prefixSum);
                ListNode temp = start;
                int sum = prefixSum;
                while (temp != head) {
                    temp = temp.next;
                    sum += temp.val;
                    if (temp != head) {
                        map.remove(sum);
                    }
                }
                start.next = head.next;
            } else {
                map.put(prefixSum, head);
            }
            head = head.next;
        }
        return dummy.next;
    }

    public class ListNode {
       int val;
       ListNode next;

       ListNode() {}

       ListNode(int val) {
           this.val = val;
       }

       ListNode(int val, ListNode next) {
           this.val = val; this.next = next;
       }
    }
}
