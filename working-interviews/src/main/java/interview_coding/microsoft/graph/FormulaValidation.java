package interview_coding.microsoft.graph;

import java.util.*;

public class FormulaValidation {

    // Find node by key from the graph
    private HashMap<String, Node> keyNodeMap = new HashMap<>();

    public void insertMapping(Node node, Node relyNode) {
        node.getRelyNodes().add(relyNode);
        this.keyNodeMap.put(node.getKey(), node);
        this.keyNodeMap.put(relyNode.getKey(), relyNode);
    }

    public boolean isValidFormulaForNode(Node node) {
        return bfs(node);
        // Set<String> visitedNode = new HashSet<>();
        // return dfs(node, visitedNode);
    }

    // DFS 遍历Graph图形节点: 递归 + 传递验证过的节点
    private boolean dfs(Node node, Set<String> visitedNode) {
        if (visitedNode.contains(node.getKey())) {
            return false;
        }

        visitedNode.add(node.getKey());
        for (Node n : node.getRelyNodes()) {
            if (!dfs(n, visitedNode)) {
                return false;
            }
        }
        return true;
    }

    // BFS 遍历Graph图形节点: 循环 + Queue来层级遍历
    private boolean bfs(Node node) {
        Set<String> visitedNode = new HashSet<>();
        Queue<Node> nodeQueue = new ArrayDeque<>(node.getRelyNodes());

        visitedNode.add(node.getKey());
        while (!nodeQueue.isEmpty()) {
            Node relyNode = nodeQueue.poll();
            for (Node n: relyNode.getRelyNodes()) {
                if (visitedNode.contains(n.getKey())) {
                    return false;
                }
                // 记录并遍历下一层行的rely节点
                nodeQueue.add(n);
                visitedNode.add(n.getKey());
            }
        }
        return true;
    }
}
