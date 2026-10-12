package array2d.matrix;

// Search a 2D Matrix II
// Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
// This matrix has the following properties:
// - Integers in each row are sorted in ascending from left to right.
// - Integers in each column are sorted in ascending from top to bottom.
//
// 1 <= n, m <= 300
// -10^9 <= matrix[i][j] <= 10^9
// All the integers in each row are sorted in ascending order.
// All the integers in each column are sorted in ascending order
public class Search2DMatrixII {

    // TODO. 需要从反斜角位置遍历, 才能根据值的大小判断位置移动
    //  不能从正对角遍历, 因为无法判断位置的移动
    //
    // [1, 4, 7, 11,15],  target = 5  -> true
    // [2, 5, 8, 12,19],  target = 20 -> false
    // [3, 6, 9, 16,22],
    // [10,13,14,17,24],
    // [18,21,23,26,30]]
    //
    // [1, 4, 7]
    // [3, 6, 9]
    //
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length -1;
        while (col >= 0 && row < matrix.length) {
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] > target) {
                col--; // 往左侧移动找更小值
            } else {
                row++; // 往下面移动找更大值
            }
        }
        return false;
    }
}
