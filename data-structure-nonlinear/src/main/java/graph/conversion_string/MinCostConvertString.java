package graph.conversion_string;

import java.util.HashMap;

// 提供图形解题思路，并非最终的答案
public class MinCostConvertString {

    public static void main(String[] args) {
        char[] original = {'a','b','c','c','e','d'};
        char[] changed = {'b','c','b','e','b','e'};
        int[] cost = {2,  5,  5,  1,  2,  20};
        MinCostConvertString instance = new MinCostConvertString();
        System.out.println(instance.minimumCost("abcd", "acbe", original, changed, cost));
    }

    private long minCost = 0;

    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        HashMap<Character, Node> charNodeMap = new HashMap<>();
        for (int index = 0; index < original.length; index++) {
            char cStart = original[index];
            if (!charNodeMap.containsKey(cStart)) {
                Node startNode = new Node(cStart, 0);
                charNodeMap.put(cStart, startNode);
            }

            char cEnd = changed[index];
            if (!charNodeMap.containsKey(cEnd)) {
                Node endNode = new Node(cEnd, cost[index]);
                charNodeMap.put(cEnd, endNode);
            }
            charNodeMap.get(cStart).setNext(charNodeMap.get(cEnd));
        }

        long resultCost = 0;
        for (int index = 0; index < source.length(); index++) {
            char cStart = source.charAt(index);
            char cEnd = target.charAt(index);
            if (cStart == cEnd) {
                continue;
            }
            if (!charNodeMap.containsKey(cStart)) {
                return -1; // 没有起始节点则不可能转换
            }

            this.minCost = Long.MAX_VALUE;
            Node startNode = charNodeMap.get(cStart);
            for (Node node: startNode.nextList) {
                if (node != null) {
                    dfsCost(node, cStart, cEnd, 0);
                }
            }
            if (this.minCost == Long.MAX_VALUE) {
                return -1; // 说明从起点没有找到目标节点
            }
            resultCost += this.minCost;
        }
        return resultCost;
    }

    private void dfsCost(Node node, char startChar, char endChar, long cost) {
        // 如果递归到起始节点则返回，避免无限循环
        if (node.c == startChar) {
            return;
        }

        // 如果找到目标节点，则统计路径的cost值
        cost += node.cost;
        if (node.c == endChar) {
            minCost = Math.min(minCost, cost);
        }

        // 继续递归子节点，只递归非空的节点
        for (Node n: node.nextList) {
            if (n != null) {
                dfsCost(n, startChar, endChar, cost);
            }
        }
    }

    static class Node {
        char c;
        int cost;
        Node[] nextList;

        public Node(char c, int cost) {
            this.c = c;
            this.cost = cost;
            this.nextList = new Node[26];
        }

        public void setNext(Node next) {
            this.nextList[next.c - 'a'] = next;
        }
    }
}
