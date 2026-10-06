package amazonTop50;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Copy List with Random Pointer
// Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
// where each new node has its value set to the value of its corresponding original node.
//
// if there are two nodes X and Y in the original list, where X.random --> Y,
// then for the corresponding two nodes x and y in the copied list, x.random --> y.
public class CopyListRandomPointer {

    // TODO. 必须先将Node对象创建出来，再设置Random的引用
    //
    // head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
    // copy = [[7,null],[13,0],[11,4],[10,2],[1,0]]
    //
    // O(N+N) 至少两次循环
    // O(N+N) 存储Node和坐标的关系
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node tempHead = head;
        Node tempHeadResult;

        int index = 0;
        HashMap<Node, Integer> oldNodeIndex = new HashMap<>(); // node -> index
        List<Node> newNodeList = new ArrayList<>(); // index -> node

        oldNodeIndex.put(head, index);

        Node node = new Node(head.val); // Deep Copy
        tempHeadResult = node;
        newNodeList.add(node);

        while (head.next != null) { // 第一次循环将基本next链条创建出来
            oldNodeIndex.put(head.next, ++index);
            Node nextNode = new Node(head.next.val); // Deep Copy
            newNodeList.add(nextNode);

            node.next = nextNode;
            node = nextNode;
            head = head.next;
        }

        // 第二次循环设置Random节点引用: 通过Index找到节点(对象已创建)
        head = tempHead;
        node = tempHeadResult;

        if (head.random != null) {
            node.random = newNodeList.get(oldNodeIndex.get(head.random));
        }
        while (head.next != null) {
            head = head.next;
            node = node.next;
            if (head.random != null) {
                node.random = newNodeList.get(oldNodeIndex.get(head.random));
            }
        }
        return tempHeadResult;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
