package array2d.matrix;

import java.util.Arrays;

// Set Matrix Zeroes
// Given an m x n integer matrix,
// if an element is 0, set its entire row and column to 0's.
//
// You must do it in place.
// 1 <= m, n <= 200
// -2^31 <= matrix[i][j] <= 2^31 - 1
//
// A straightforward solution using O(mn) space is probably a bad idea.
// A simple improvement uses O(m + n) space, but still not the best solution.
// Could you devise a constant space solution?
//
// 必须直接在二维数组上直接替换，但没有中间替换值
// 且不能创建额外的空间
public class SetMatrixZeroes {

    // TODO. 把首行首列标记位, 第一行和第一列必须额外判断
    // [0,1,2,0],     [0,0,0,0]
    // [3,4,5,2], ->  [0,4,5,0]
    // [1,3,1,5],     [0,3,1,0]
    //
    // [1,1,1],    [1,0,1],    [1,0,1],
    // [1,0,1], -> [0,0,1], -> [0,0,0],
    // [1,1,1]     [1,1,1],    [1,0,1],
    //
    // [1,2, 3, 4],
    // [5,0, 7, 8],
    // [0,10,11,12],
    // [13,14,15,0]
    //
    // O(N*M + N*M + N + M)
    // O(1)
    public void setZeroes(int[][] matrix) {
        boolean shouldSetFirstRow = false;
        boolean shouldSetFirstCol = false;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    // 只有第一次循环时才能判断第一个整行是否应该被设置
                    if (i == 0) {
                        shouldSetFirstRow = true;
                    }
                    if (j == 0) {
                        shouldSetFirstCol = true;
                    }
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // 排除第一行第一列的位置, 根据标记位判断设置
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 最后检查第一行和第一列是否需要设置
        if (shouldSetFirstRow) {
            Arrays.fill(matrix[0], 0);
        }
        if (shouldSetFirstCol) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}