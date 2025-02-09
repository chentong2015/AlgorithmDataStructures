package arrays.array2;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    // Spiral Matrix 螺旋矩阵
    // Given an m x n matrix, return all elements of the matrix in spiral order.
    // matrix = [
    //  [1,2,3],
    //  [4,5,6],
    //  [7,8,9]]
    //  -> [1,2,3,6,9,8,7,4,5]
    // 从边缘逐渐往中间收缩, 对上下左右四个方向进行约束，依次输出
    // O(m*n) O(m*n) 最优的复杂度 !!
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> results = new ArrayList<>();
        int row = matrix.length;
        int col = matrix[0].length;
        int top = 0, right = 0, bottom = 0, left = 0;
        while (top + bottom < row && left + right < col) {
            for (int j = left; j < col - right; j++) {
                results.add(matrix[top][j]);
            }
            top++;
            if (top + bottom == row) break;                 // 只要所有的行读过，所有的列读过，则算完全
            for (int i = top; i < row - bottom; i++) {
                results.add(matrix[i][col - 1 - right]);    // col - 1 - right 每一个下次的读取都是在前一个个基础上移动
            }
            right++;
            if (left + right == col) break;
            for (int j = col - 1 - right; j >= left; j--) { // col - 1 - right 和上前一步的方向相反，从大到小
                results.add(matrix[row - 1 - bottom][j]);
            }
            bottom++;
            if (top + bottom == row) break;
            for (int i = row - 1 - bottom; i >= top; i--) {
                results.add(matrix[i][left]);
            }
            left++;
            if (left + right == col) break;
        }
        return results;
    }
}
