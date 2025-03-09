package bfs_search.zero_one_matrix;

import java.util.LinkedList;
import java.util.Queue;

// As Far from Land as Possible
// Given an n x n grid containing only values 0 and 1
// where 0 represents water and 1 represents land,
// find a water cell such that its distance to the nearest land cell is maximized
// and return the distance. If no land or water exists in the grid, return -1.
//
// The distance used in this problem is the Manhattan distance:
// the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
//
// n == grid.length
// n == grid[i].length
// 1 <= n <= 100
// grid[i][j] is 0 or 1
public class ZeroOneLand {

    // TODO. 从所有Land位置同时开始BFS广度优先遍历
    // [1,0,1], -> (1,1) -> 1+1=2
    // [0,0,0],
    // [1,0,1]
    //
    // [1,0,0], -> (2,2) -> 2+2=4
    // [0,0,0],
    // [0,0,0]
    //
    // [0,0,0,0,0] -> (4,0) -> 3+1 = 3
    // [1,0,0,1,0]
    // [0,0,1,1,0]
    // [0,0,0,0,0]
    // [0,0,0,1,0]
    //
    // O(N*N)+O(N/2 * N/2) 第二遍计算距离是倍数关系
    // O(N) 存储位置的空间开销
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        // 不满足0和1的条件
        if (queue.isEmpty() || queue.size() == n * n) {
            return -1;
        }

        int result = -1;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            // 每从Queue中遍历一层数据，统计的距离则加1
            result++;

            // 取队列中现有的位置进行层级遍历
            int size = queue.size();
            while (size-- > 0) {
                int[] cell = queue.poll();
                int x = cell[0];
                int y = cell[1];
                // 从(row, col)位置移动的四个方向，移动位置后标记land
                for (int[] direction : directions) {
                    int i = x + direction[0];
                    int j = y + direction[1];
                    if (i >= 0 && i < n && j >= 0 && j < n && grid[i][j] == 0) {
                        grid[i][j] = 1;
                        queue.offer(new int[]{i, j});
                    }
                }
            }
        }
        return result;
    }
}
