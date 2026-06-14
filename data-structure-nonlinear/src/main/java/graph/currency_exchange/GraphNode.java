package graph.currency_exchange;

import java.util.HashMap;

public class GraphNode {

    private final String name;
    private final HashMap<String, GraphNode> exchangeNodeMap; // 存储后继执行的所有节点
    private final HashMap<String, Float> exchangeRateMap;     // 存储执行每个金额的汇率

    public GraphNode(String name) {
        this.name = name;
        this.exchangeNodeMap = new HashMap<>();
        this.exchangeRateMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public HashMap<String, GraphNode> getExchangeNodeMap() {
        return exchangeNodeMap;
    }

    public HashMap<String, Float> getExchangeRateMap() {
        return exchangeRateMap;
    }
}
