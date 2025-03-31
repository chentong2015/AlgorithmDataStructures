package master.path_effort;

import java.util.Comparator;
import java.util.PriorityQueue;

// Path With Minimum Effort
// You are a hiker preparing for an upcoming hike.
// You are given heights, a 2D array of size rows x columns,
// where heights[row][col] represents the height of cell (row, col).
//
// You are situated in the top-left cell (0, 0)
// you hope to travel to the bottom-right cell (rows-1, columns-1)
// You can move "up, down, left, or right"
// you wish to find a route that requires the minimum effort.
//
// A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
// Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
//
// rows == heights.length
// columns == heights[i].length
// 1 <= rows, columns <= 100
// 1 <= heights[i][j] <= 10^6
public class PathMinimumEffort {

    // TODO. 路径规划问题 -> Graph图形问题
    // [1,2,2] -> [0,1,1]
    // [3,8,2]    [2,5,1]
    // [5,3,5]    [2,2,2]
    //
    // [1,2,1,1,1]
    // [1,2,1,2,1]
    // [1,2,1,2,1]
    // [1,2,1,2,1]
    // [1,1,1,2,1]
    //
    // O(n*m) n is rows, m is cols
    // O(n*m)
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        int[][] dist = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = 0;

        // 最小堆存储最小的Effort结果
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.add(new int[]{0, 0, 0});

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int effort = top[0];
            int x = top[1];
            int y = top[2];
            if (effort > dist[x][y]) {
                continue;
            }
            // 判断移动后右下侧的坐标位置
            if (x == rows - 1 && y == cols - 1) {
                return effort;
            }

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols) {
                    int absEffort = Math.abs(heights[x][y] - heights[nx][ny]);
                    int newEffort = Math.max(effort, absEffort);
                    if (newEffort < dist[nx][ny]) {
                        dist[nx][ny] = newEffort;
                        minHeap.add(new int[]{newEffort, nx, ny});
                    }
                }
            }
        }
        return -1;
    }
}
