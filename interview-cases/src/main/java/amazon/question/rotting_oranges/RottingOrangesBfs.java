package amazon.question.rotting_oranges;

import java.util.ArrayDeque;
import java.util.Queue;

// TODO. 算法进阶2: BFS从一个点开始以Level层级遍历，层级的深度就是时间
// [2,1,1],   [2,2,1],   [2,2,2],   [2,2,2],   [2,2,2],
// [1,1,0], > [2,1,0], > [2,2,0], > [2,2,0], > [2,2,0],
// [0,1,1]    [0,1,1],   [0,1,1],   [0,2,1],   [0,2,2],
public class RottingOrangesBfs {

    class Position {
        public int row;
        public int col;
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int orangesRotting(int[][] grid) {
        int maxDeep = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j=0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    int deep = bfsDeep(grid, i, j);
                    maxDeep = Math.max(maxDeep, deep);
                }
            }
        }
        return hasFreshOrange(grid) ? -1: maxDeep;
    }

    // 在从Queue中移除的时候，追加下一层的数据
    private int bfsDeep(int[][] grid, int i, int j) {
        int deep = 0;
        grid[i][j] = 0;

        Queue<Position> queue = new ArrayDeque<>();
        addRelatedPositions(queue, grid, i, j);
        while (!queue.isEmpty()) {
            deep++;
            int size = queue.size();
            for (int index = 0; index < size; index++) {
                Position position = queue.poll();
                addRelatedPositions(queue, grid, position.row, position.col);
            }
        }
        return deep;
    }

    // 在追加相邻的四个方向位置的数据时，直接将数据修改
    // 避免之后重复被添加到Queue队列中
    private void addRelatedPositions(Queue<Position> queue, int[][] grid, int i, int j) {
        if (i > 0 && grid[i-1][j] == 1) {
            queue.add(new Position(i-1, j));
            grid[i-1][j] = 0;
        }
        if (i < grid.length- 1 && grid[i+1][j] == 1) {
            queue.add(new Position(i+1, j));
            grid[i+1][j] = 0;
        }
        if (j > 0 && grid[i][j-1] == 1) {
            queue.add(new Position(i, j-1));
            grid[i][j-1] = 0;
        }
        if (j < grid[0].length- 1 && grid[i][j + 1] == 1) {
            queue.add(new Position(i, j + 1));
            grid[i][j + 1] = 0;
        }
    }

    private boolean hasFreshOrange(int[][] grid) {
        for (int i = 0; i< grid.length; i++) {
            for (int j=0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
