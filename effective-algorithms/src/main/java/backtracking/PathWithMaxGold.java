package backtracking;

// Path with Maximum Gold
// In a gold mine grid of size m x n, each cell in this mine has an integer
// representing the amount of gold in that cell, 0 if it is empty.
//
// Return the maximum amount of gold you can collect under the conditions:
// - Every time you are located in a cell you will collect all the gold in that cell.
// - From your position, you can walk one step to the left, right, up, or down.
// - You can't visit the same cell more than once.
// - Never visit a cell with 0 gold.
// - You can start and stop collecting gold from any position in the grid that has some gold.
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 15
// 0 <= grid[i][j] <= 100
// There are at most 25 cells containing gold.
public class PathWithMaxGold {

    // TODO. 在DFS的过程中进行回溯，结果和起始位置有关
    // [0,6,0], -> 9+8+7 = 24
    // [5,8,7],
    // [0,9,0]]
    //
    // [1,0,7], -> 1+2+3+4+5+6+7 = 28
    // [2,0,6],
    // [3,4,5],
    // [0,3,0],
    // [9,0,20]
    //
    // O(M*N * M*N) 最差情况是每个位置做一遍DFS
    // O(M*N)       递归深度造成的Stack栈空间开销

    private int result = 0;

    // TODO. 回溯: 在递归的过程中修改值，同时有将其恢复
    public int getMaximumGold(int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] > 0) {
                    backtracking(grid, row, col, 0);
                }
            }
        }
        return result;
    }

    // current表示每条递归线路上的累计值
    private void backtracking(int[][] grid, int row, int col, int current) {
        int originalValue = grid[row][col];
        current += originalValue;
        result = Math.max(result, current);

        // 修改起始位置上值，避免无限递归
        grid[row][col] = 0;

        if (row > 0 && grid[row - 1][col] > 0) {
            backtracking(grid, row - 1, col, current);
        }
        if (row < grid.length - 1 && grid[row + 1][col] > 0) {
            backtracking(grid, row + 1, col, current);
        }
        if (col > 0 && grid[row][col - 1] > 0) {
            backtracking(grid, row, col - 1, current);
        }
        if (col < grid[0].length - 1 && grid[row][col + 1] > 0) {
            backtracking(grid, row, col + 1, current);
        }

        // 在DFS的过程中进行回溯处理
        grid[row][col] = originalValue;
    }
}
