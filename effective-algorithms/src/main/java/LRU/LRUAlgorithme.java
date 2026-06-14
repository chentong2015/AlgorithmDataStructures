package LRU;

import java.util.HashMap;
import java.util.Map;

// Least Recently Used
// Design a data structure that follows the constraints of LRU
//
// The functions get and put must each run in O(1) average time complexity.
public class LRUAlgorithme {

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head;
    private final Node tail;

    // Initialize the LRU cache with positive size capacity.
    public LRUAlgorithme(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    // Return the value of the key if the key exists, otherwise return -1.
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        remove(node);
        insertAtFront(node);
        return node.value;
    }

    // Update the value of the key if the key exists.
    // Otherwise, add the key-value pair to the cache.
    // If the number of keys exceeds the capacity from this operation, evict the LRU key.
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            insertAtFront(node);
        } else {
            if (map.size() == capacity) {
                // 删除最末尾的最近最少使用的节点
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }

            Node newNode = new Node(key, value);
            insertAtFront(newNode);
            map.put(key, newNode);
        }
    }

    // TODO. Node被包含在首尾节点之中, 直接修改前后继的指向
    // Head <-> Node <-> Tail
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertAtFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    // 自定义Node节点, 构建链表结构方便快速删除
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
