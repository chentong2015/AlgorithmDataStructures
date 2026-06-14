package graph.currency_exchange;

import java.util.*;

public class GraphNodeHandler {

    // 该属性用于目标查找累计
    private float exchangeRate;

    // 使用HashMap是为了快速定位图形中Node节点
    private Map<String, GraphNode> nodesMap = new HashMap<>();

    public void addExchange(String source, String target, float rate) {
        if (!nodesMap.containsKey(source)) {
            GraphNode node = new GraphNode(source);
            nodesMap.put(source, node);
        }

        HashMap<String, GraphNode> exchangeNodeMap = nodesMap.get(source).getExchangeNodeMap();
        HashMap<String, Float> exchangeRateMap = nodesMap.get(source).getExchangeRateMap();
        if (!exchangeRateMap.containsKey(target)) {
            GraphNode node = new GraphNode(target);
            exchangeNodeMap.put(target, node);
            nodesMap.put(target, node);
        }
        exchangeRateMap.put(target, rate); // add new rate or update old rate
    }

    public float getExchangeRate(String source, String target) {
        if (!nodesMap.containsKey(source) || !nodesMap.containsKey(target)) {
            return 0; // not possible to get exchange rate
        }

        this.exchangeRate = -1;
        findTargetDFS(source, target, 1);
        return this.exchangeRate;
    }


    // 在图形中DFS深度优先查询，直到找到目标节点(才设置属性)
    private void findTargetDFS(String current, String target, float currentRate) {
        GraphNode currentNode = nodesMap.get(current);
        if (currentNode.getName().equals(target)) {
            this.exchangeRate = currentRate;
            return;
        }

        // TODO. 避免DFS遍历时出现无限循环
        if (this.exchangeRate != -1) {
            return; // Has already found target node
        }

        for (GraphNode graphNode : currentNode.getExchangeNodeMap().values()) {
            String nextName = graphNode.getName();
            float nextRate = currentNode.getExchangeRateMap().get(nextName) * currentRate;
            findTargetDFS(nextName, target, nextRate);
        }
    }
}
