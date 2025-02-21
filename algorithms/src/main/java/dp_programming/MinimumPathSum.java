package dp_programming;

public class MinimumPathSum {

    // TODO. 问题的本质：动态编程，需要统计出格子中到达每一个位置的最短path值
    // Minimum Path Sum
    // Given a m x n grid filled with non-negative numbers,
    // find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
    // Note: You can only move either down or right at any point in time.
    //
    // O(n & m) O(1)
    // 1 3 1     1 4 5
    // 1 5 1 =>  2 7 6
    // 4 2 1     6 8 7
    public static int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 && j == 0) {
                    // do nothing 起始位置没有必要做任何计算
                } else if (i > 0 && j == 0) {
                    // 第一列只能往下，累计前一行的结果
                    grid[i][j] += grid[i-1][j];
                } else if (i == 0) {
                    // 第一行只能往右，累计前一列的结果
                    grid[i][j] += grid[i][j-1];
                } else {
                    // 任意位置，取左边和上边路径的最小值
                    grid[i][j] += Math.min(grid[i][j-1], grid[i-1][j]);
                }
            }
        }
        return grid[rows-1][cols-1];
    }
}
