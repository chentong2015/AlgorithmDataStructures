package interview.graph_node;

import java.util.*;

// TODO. 将Name名称映射到构建出来的Graph图中节点
// Time: O(n)    时间复杂度可能遍历图形中所有Node节点
// Space: O(n+n) 空间复杂度存储所有的Key和Node节点(包括Key名称)
public class GraphNodeHandler {

    private float result;
    Map<String, GraphNode> nameNodeMap = new HashMap<>();

    public static void main(String[] args) {
        GraphNodeHandler handler = new GraphNodeHandler();
        handler.buildGraph("EUR", "USD", 1.2f);
        handler.buildGraph("CHS", "RMB", 1.4f);
        handler.buildGraph("USD", "RMB", 2.0f);

        System.out.println(handler.getCurrencyValue("EUR", "RMB"));
    }

    // Build Graph based on the line {"EUR", "CHS", 1.2}
    public void buildGraph(String source, String target, float value) {
        GraphNode targetNode = new GraphNode(target, value, new ArrayList<>());
        if (nameNodeMap.containsKey(source)) {
            GraphNode node = nameNodeMap.get(source);
            node.getChildren().add(targetNode);
        } else {
            GraphNode sourceNode = new GraphNode(source, 0, null);
            sourceNode.setChildren(List.of(targetNode));
            nameNodeMap.put(source, sourceNode);
        }
        nameNodeMap.put(target, targetNode);
    }

    public float getCurrencyValue(String source, String target) {
        if (!nameNodeMap.containsKey(source)) {
            return 0;
        }
        dfs(nameNodeMap.get(source), target, 0);
        return this.result;
    }

    // TODO. DFS从根节点递归查找Graph中节点: 同步统计路径的长度
    // EUR -> USD -> RMB
    // 0    1.2     2.0
    private void dfs(GraphNode node, String target, float count) {
        count += node.getValue();
        if (node.getName() == target) {
            this.result = count;
            return;
        }

        for (GraphNode child: node.getChildren()) {
            dfs(child, target, count);
        }
        count -= node.getValue();
    }
}
