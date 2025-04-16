package graph.adjacent_pairs;

import java.util.HashMap;
import java.util.HashSet;

// Restore the Array From Adjacent Pairs
// There is an integer array nums that consists of n unique elements, but you have forgotten it.
// However, you do remember every pair of adjacent elements in nums.
//
// You are given a 2D integer array adjacentPairs of size n - 1
// where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.
//
// It is guaranteed that
// - every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs
// - either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]].
// The pairs can appear in any order.
//
// Return the original array nums.
// If there are multiple solutions, return any of them.
//
// ums.length == n
// adjacentPairs.length == n - 1
// adjacentPairs[i].length == 2
// 2 <= n <= 10^5
// -10^5 <= nums[i], ui, vi <= 10^5
// There exists some nums that has adjacentPairs as its pairs
public class RestoreArrayAdjacentPairs {

    // TODO. 学会构建图形的遍历关联，通过引用来循环(优化时间复杂度)
    // adjacentPairs = [[2,1],[3,4],[3,2]]   -> [1,2,3,4]
    // adjacentPairs = [[4,-2],[1,4],[-3,1]] -> [-2,4,1,-3] or [-3,1,4,-2]
    // adjacentPairs = [[100000,-100000]]    -> [100000,-100000]
    //
    // O(N + N + N) 使用三次N级别的循环
    // O(2N + N)    构建的图形结构 + 结果数组
    public int[] restoreArray(int[][] adjacentPairs) {
        // 将唯一值映射到图结构中的Node节点
        HashMap<Integer, Node> graphMap = buildGraphMap(adjacentPairs);
        // 找到出发节以开始遍历
        int nextValue = findStartValue(adjacentPairs);
        int size = adjacentPairs.length + 1;

        int index = 0;
        int[] result = new int[size];
        while (index < size) {
            result[index] = nextValue;

            // TODO. 如果只有一个相邻节点，则一定是首尾。反之取它的新值节点继续
            Node node = graphMap.get(nextValue);
            if (node.right == null) {
                nextValue = node.left.value;
            } else {
                if (result[index - 1] == node.left.value) {
                    nextValue = node.right.value;
                } else {
                    nextValue = node.left.value;
                }
            }
            index++;
        }
        return result;
    }

    // 找到开头的数字节点Value
    private int findStartValue(int[][] adjacentPairs) {
        HashSet<Integer> set = new HashSet<>();
        for (int[] adjacentPair : adjacentPairs) {
            int firstValue = adjacentPair[0];
            int secondValue = adjacentPair[1];
            if (set.contains(firstValue)) {
                set.remove(firstValue);
            } else {
                set.add(firstValue);
            }
            if (set.contains(secondValue)) {
                set.remove(secondValue);
            } else {
                set.add(secondValue);
            }
        }
        for (int value: set) {
            return value;
        }
        return Integer.MIN_VALUE;
    }

    // 构建一个Node节点的左右两个相邻节点, 边缘节点只有left指向
    private HashMap<Integer, Node> buildGraphMap(int[][] adjacentPairs) {
        HashMap<Integer, Node> graphMap = new HashMap<>();
        for (int[] adjacentPair : adjacentPairs) {
            int value1 = adjacentPair[0];
            int value2 = adjacentPair[1];
            Node node1 = graphMap.getOrDefault(value1, new Node(value1));
            Node node2 = graphMap.getOrDefault(value2, new Node(value2));
            if (node1.left == null) {
                node1.left = node2;
            } else {
                node1.right = node2;
            }
            if (node2.left == null) {
                node2.left = node1;
            } else {
                node2.right = node1;
            }
            graphMap.put(value1, node1);
            graphMap.put(value2, node2);
        }
        return graphMap;
    }

    static class Node {
        int value;
        Node left;
        Node right;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
