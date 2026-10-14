package leetcode6.matrix.increasing_path;

// Longest Increasing Path in a Matrix
// Given an m x n integers matrix, return the length of the longest increasing path in matrix.
//
// From each cell, you can either move in four directions: left, right, up, or down
// You may not move diagonally or move outside the boundary
// 1 <= m, n <= 200
// 0 <= matrix[i][j] <= 2^31 - 1
public class LongestIncreasingPathMatrix {

    // TODO. DP在递归遍历过程中随时记录Cell的计算结果
    // [9,9,4], -> [1, 2, 6, 9]
    // [6,6,8],
    // [2,1,1]
    //
    // O(N*M + N*M) 每个位置最多被计算一次
    // 0(N*M)       DP动态记录的数组

    private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int longestIncreasingPath(int[][] matrix) {
        int maxLength = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] matrixDP = new int[n][m];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                maxLength = Math.max(maxLength, dfs(matrix, matrixDP, row, col));
            }
        }
        return maxLength;
    }

    // 本质上是递归方法, 需要带返回值, 每个Cell初始值为1
    private int dfs(int[][] matrix, int[][] matrixDP, int row, int col) {
        if (matrixDP[row][col] > 0) {
            return matrixDP[row][col]; // 直接返回计算过的位置
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int currentLength = 1;
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (newRow >= 0 && newRow < n && newCol >=0 && newCol < m
                    && matrix[newRow][newCol] > matrix[row][col]) {
                int newLength = dfs(matrix, matrixDP, newRow, newCol);
                currentLength = Math.max(currentLength, 1 + newLength);
            }
        }
        matrixDP[row][col] = currentLength; // 立即设置当前位置的计算结果
        return currentLength;
    }
}
