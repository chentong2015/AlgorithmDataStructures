package graph.dijkstra;

import java.util.Comparator;
import java.util.PriorityQueue;

// Path With Minimum Effort
// A route's effort is the maximum absolute difference in heights
// between two consecutive cells of the route.
//
// Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
// 1 <= rows, columns <= 100
// 1 <= heights[i][j] <= 10^6
public class MinPathEffort {

    // TODO. Dijkstra最短路算法变体: DP + BFS从左上往右下扩散
    // [1,2,2], -> [1,3,5,3,5] -> 2
    // [3,8,2],
    // [5,3,5]
    //
    // O(N*M + N*M*log(NM)) 循环和排序是所有节点的级别
    // O(N*M + N*M)         dp数组和queue存储所有节点的级别

    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        // DP数组存储遍历过程中Effort最小记录
        int[][] dpMinEffort = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dpMinEffort[i][j] = Integer.MAX_VALUE;
            }
        }
        dpMinEffort[0][0] = 0;

        // TODO. 最小堆排序Effort同时携带对应坐标, 以便下一步移动
        PriorityQueue<NodeStep> minHeap = new PriorityQueue<>(Comparator.comparingInt(nodeStep -> nodeStep.currentMinEffort));
        minHeap.add(new NodeStep(0, 0, 0));

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!minHeap.isEmpty()) {
            NodeStep nodeStep = minHeap.poll();
            int x = nodeStep.x;
            int y = nodeStep.y;
            if (nodeStep.currentMinEffort > dpMinEffort[x][y]) {
                continue; // 移动到当前位置的effort大于DP中记录的数值, 无需再往后延伸
            }
            if (x == rows - 1 && y == cols - 1) {
                return nodeStep.currentMinEffort; // 终点坐标记录的effort结果
            }

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols) {
                    int absEffort = Math.abs(heights[x][y] - heights[nx][ny]);
                    int newEffort = Math.max(nodeStep.currentMinEffort, absEffort);
                    if (newEffort < dpMinEffort[nx][ny]) {
                        dpMinEffort[nx][ny] = newEffort;
                        minHeap.add(new NodeStep(nx, ny, newEffort)); // 传递到下个坐标上
                    }
                }
            }
        }
        return -1;
    }

    static class NodeStep {
        int x;
        int y;
        int currentMinEffort;
        public NodeStep(int x, int y, int currentMinEffort) {
            this.x = x;
            this.y = y;
            this.currentMinEffort = currentMinEffort;
        }
    }
}
