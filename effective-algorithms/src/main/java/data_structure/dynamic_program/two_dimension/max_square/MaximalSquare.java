package data_structure.dynamic_program.two_dimension.max_square;

// Maximal Square
// Given an m x n binary matrix filled with 0's and 1's,
// find the largest square containing only 1's and return its area.
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 300
// matrix[i][j] is '0' or '1'.
public class MaximalSquare {

    // TODO. 正方形边长问题, 从左上往右下累计最大边长
    //  1 4 9 16 25 ...
    //
    // ["1","0","1","0","0"], -> 4
    // ["1","0","1","1","1"],
    // ["1","1","1","1","1"],
    // ["1","0","0","1","0"]
    //
    // ["0","1"], -> 1
    // ["1","0"]]
    //
    // O(N*M)
    // O(N*M)
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        int maxLength = 0;
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        // TODO. 取相邻三个位置的最小值
                        dp[i][j] = 1 + Math.min(dp[i - 1][j],
                                Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                    }
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        return maxLength;
    }
}
