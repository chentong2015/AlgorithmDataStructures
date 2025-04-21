package graph.clone_graph;

import java.util.*;

// Clone Graph
// Given a reference of a node in a connected undirected graph.
// Return a deep copy (clone) of the graph.
// Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
//
// 1. The given node will always be the first node with val = 1
// 2. The Graph is connected and all nodes can be visited starting from the given node
public class CloneGraph {

    // TODO. 本质是在Copy节点和它的相邻关系，只需设置Node节点引用
    // Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
    // Output: [[2,4],[1,3],[2,4],[1,3]]
    //
    // 输入的时每个#Node的相邻节点
    //   1 - 2
    //   |   |
    //   4 - 3
    // 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
    // 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
    // 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
    // 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
    //
    // O(N) 遍历所有的节点
    // O(N) 存储所有key值和节点的映射关系
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (node.neighbors == null) {
            return new Node(1);
        }

        HashMap<Integer, Node> cloneNodeMap = new HashMap<>();
        return clone(node, cloneNodeMap);
    }

    private Node clone(Node node, HashMap<Integer, Node> cloneNodeMap) {
        // 如果克隆的节点已经被创建，则直接返回引用
        if (cloneNodeMap.containsKey(node.val)) {
            return cloneNodeMap.get(node.val);
        }
        Node cloneNode = new Node(node.val);
        cloneNodeMap.put(node.val, cloneNode);

        // TODO. 递归克隆当前节点所有的相邻节点
        List<Node> cloneNeighbors = new LinkedList<>();
        for (Node neighbor: node.neighbors) {
            Node cloneNeighbor = clone(neighbor, cloneNodeMap);
            cloneNeighbors.add(cloneNeighbor);
        }
        cloneNode.neighbors = cloneNeighbors;
        return cloneNode;
    }

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
