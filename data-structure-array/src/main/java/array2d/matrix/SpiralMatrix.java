package array2d.matrix;

import java.util.ArrayList;
import java.util.List;

// Spiral Matrix 螺旋矩阵
// Given an m x n matrix, return all elements of the matrix in spiral order.
public class SpiralMatrix {

    // TODO. 创建多变量标识Index变量路径方向
    // [1,2,3], -> [1,2,3,6,9,8,7,4,5]
    // [4,5,6],
    // [7,8,9]]
    //
    // Boundary initialization: Set top=0, bottom=rows-1, left=0, right=cols-1
    // 1. Right traversal: Move left to right along top row, then increment top
    // 2. Down traversal: Move top to bottom along right column, then decrement right
    // 3. Left traversal: Check if layer still valid, move right to left along bottom, decrement bottom
    // 4. Up traversal: Check if layer still valid, move bottom to top along left, increment left
    // Layer completion: Repeat until boundaries cross
    //
    // O(m*n)
    // O(m*n)
    public List<Integer> spiralOrder(int[][] matrix) {
        // 初始化矩形的"上下左右"四个坐标边界
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        List<Integer> spiral = new ArrayList<>();
        while (top <= bottom && left <= right) { // 判断矩形还有移动区间
            for (int i = left; i <= right; i++) {
                spiral.add(matrix[top][i]);
            }
            top++; // 上边

            for (int j = top; j <= bottom; j++) {
                spiral.add(matrix[j][right]);
            }
            right--; // 右侧

            if (top <= bottom) { // 判断上下是否有区间
                for (int k = right; k >= left; k--) {
                    spiral.add(matrix[bottom][k]);
                }
                bottom--; // 下边
            }

            if (left <= right) { // 判断左右是否有区间
                for (int l = bottom; l >= top; l--){
                    spiral.add(matrix[l][left]);
                }
                left++; // 左侧
            }
        }
        return spiral;
    }
}
