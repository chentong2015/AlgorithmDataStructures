package interview.graph_node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphNodeBuilding {

    // 构建一张完成的Graph图形
    public Map<String, Node> buildGraph() {
        Node eurNode = new Node("EUR", 0, null);
        Node chsNode = new Node("CHS", 1.2f, null);
        Node usdNode = new Node("USD", 2.2f, null);

        List<Node> children = new ArrayList<>();
        children.add(chsNode);
        children.add(usdNode);
        eurNode.children = children;

        // TODO. 将Name名称直接映射到Node节点
        Map<String, Node> mapping = new HashMap<>();
        mapping.put("EUR", eurNode);
        mapping.put("CHS", chsNode);
        mapping.put("USD", usdNode);
        return mapping;
    }

    public float getCurrencyValue(String start, String end) {
        Map<String, Node> nodeMap = buildGraph();

        // TODO. 使用BFS遍历Graph中的所有节点
        Node node = nodeMap.get(start);

        return 0;
    }

    class Node {
        String name;
        float value;
        List<Node> children;

        public Node(String name, float value, List<Node> children) {
            this.name = name;
            this.value = value;
            this.children = children;
        }
    }
}
