package leetcode.right_triangles;

// Right Triangles
// A collection of 3 elements of grid is a right triangle
// if one of its elements is in the same row with another element
// and in the same column with the third element. The 3 elements may not be next to each other.
//
// Return an integer that is the number of right triangles
// that can be made with 3 elements of grid such that all of them have a value of 1.
public class RightTriangles {

    // TODO. 问题本质: 每一个坐标1都是构成直角三角形的不同选择
    // [1,0,1],
    // [1,0,0],
    // [1,0,0]
    //
    // O(R * C * 2) 循环两次全部的数据
    // O(R + C)     只保留行数和列数的数量统计
    public long numberOfRightTriangles(int[][] grid) {
        int[] countRows = new int[grid.length];
        int[] countCols = new int[grid[0].length];

        // 提前遍历统计，避免在Loop时再次循环
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    countRows[row]++;
                    countCols[col]++;
                }
            }
        }

        long count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    count += (long) (countRows[row] - 1) * (countCols[col] - 1);
                }
            }
        }
        return count;
    }
}
