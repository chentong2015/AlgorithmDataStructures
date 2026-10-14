package leetcode6.matrix.path_matrix;

import java.util.HashSet;
import java.util.Set;

// TODO. DP算法的复杂实现: 动态记录并更新matrixCalculated中的值
// matrixCalculated和visitedCell的功能重合，只需要要来一个记录即可 !!
public class LongestIncreasingPathMatrixDP {

    private int maxLength = 0;
    private int[][] matrixCalculated;
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        matrixCalculated = new int[n][m];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (matrixCalculated[row][col] == 0) {
                    int length = dfs(matrix, new HashSet<>(), row, col, 1);
                    maxLength = Math.max(maxLength, length);
                } else {
                    maxLength = Math.max(maxLength, matrixCalculated[row][col]);
                }
            }
        }
        return maxLength;
    }

    private int dfs(int[][] matrix, Set<String> visitedCell, int row, int col, int length) {
        visitedCell.add(row + "-" + col);
        int currentLength = length;
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (newRow < 0 || newRow == matrix.length || newCol < 0 || newCol == matrix[0].length) {
                continue;
            }

            if (matrix[newRow][newCol] > matrix[row][col]) {
                if (matrixCalculated[newRow][newCol] > 0) {
                    currentLength = Math.max(currentLength, length + matrixCalculated[newRow][newCol]);
                    continue;
                }

                // TODO. 立刻记录移动的下一步所计算的结果 !!
                if (!visitedCell.contains(newRow + "-" + newCol)) {
                    int newLength = dfs(matrix, new HashSet<>(visitedCell), newRow, newCol, 1);
                    matrixCalculated[newRow][newCol] = newLength;
                    currentLength = Math.max(currentLength, length + newLength);
                }
            }
        }
        matrixCalculated[row][col] = currentLength;
        return currentLength;
    }
}