package amazon.rotting_oranges;

import java.util.ArrayDeque;
import java.util.Queue;

// TODO. 算法进阶3: BFS不同位置的2可以"同步出发"，以保证消耗的时间最少
// [2,1,1],   [2,2,1],   [2,2,2],
// [1,1,1], > [2,1,2], > [2,2,2],
// [0,1,2]    [0,2,2],   [0,2,2], 右侧底部的2可以"同步出发"
public class RottingOrangesBfsMaster {

    public static void main(String[] args) {
        int[][] grid = {{2, 1, 1},
                        {1, 1, 1},
                        {0, 1, 2}};
        RottingOrangesBfsMaster rottingOranges = new RottingOrangesBfsMaster();
        System.out.println(rottingOranges.orangesRotting(grid));
    }

    class Position {
        public int row;
        public int col;
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int orangesRotting(int[][] grid) {
        int minMinutes = 0;
        int countRefresh = 0;
        Queue<Position> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    countRefresh++;
                }
                if (grid[i][j] == 2) {
                    queue.add(new Position(i, j));
                }
            }
        }

        while (!queue.isEmpty()) {
            // 队列的大小一定要使用遍历临时存储，避免动态变动
            int size = queue.size();
            for (int index = 0; index < size; index++) {
                Position position = queue.poll();
                int row = position.row;
                int col = position.col;

                // 往四个方向移动，并且追加到下一层的Queue队列中
                if (row > 0 && grid[row-1][col] == 1) {
                    queue.add(new Position(row-1, col));
                    grid[row-1][col] = 0;
                    countRefresh--; // 统计从1->0的消除效果
                }
                if (row < grid.length- 1 && grid[row+1][col] == 1) {
                    queue.add(new Position(row+1, col));
                    grid[row+1][col] = 0;
                    countRefresh--;
                }
                if (col > 0 && grid[row][col-1] == 1) {
                    queue.add(new Position(row, col-1));
                    grid[row][col-1] = 0;
                    countRefresh--;
                }
                if (col < grid[0].length- 1 && grid[row][col + 1] == 1) {
                    queue.add(new Position(row, col + 1));
                    grid[row][col + 1] = 0;
                    countRefresh--;
                }
            }
            // 第一轮的Queue存储的是2位置的数据，只能在for循环一轮后判断统计
            if (!queue.isEmpty()) {
                minMinutes++;
            }
        }

        // 直接在遍历的过程中统计Refresh数量，避免后续再次遍历和判断
        return countRefresh > 0 ? -1: minMinutes;
    }
}
