package leetcode6.matrix.ksmallest;

public class CountSortedMatrix {

    // TODO. 从"右上角"开始遍历, 往"左下角移动"
    //  统计比value更小的值(找的值不一定存在)
    //
    // [1, 5, 9], k = 8 -> 13
    // [10,11,13],
    // [12,13,15]
    //
    // O(N+N) Row和Col最大只能到N
    // O(1)
    public static int countSmallerValues(int[][] matrix, int value) {
        int count = 0;
        int row = 0;
        int col = matrix.length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] <= value) {
                count += col + 1; // 说明col前面数据都小于等于value
                row++;            // 移动到下一行，col列位置不变
            } else {
                col--;            // 往左侧移动进行统计
            }
        }
        return count;
    }

    // TODO. 从"右上角"开始遍历, 往"左下角移动"
    //  根据坐标构建的大小值排序，计算index位置的值
    // m = 3, n = 3
    // 1  2  3   n
    // 2  4  6   2n
    // 3  6  9   3n
    // m  2m 3m
    //
    // O(N+N) Row和Col最大只能到N
    // O(1)
    public static int countSmallerValuesMN(int m, int n, int value) {
        int count = 0;
        int row = 1;
        int col = n;
        while (row <= m && col >= 1) {
            if (row * col <= value) {
                count += col;
                row++;
            } else {
                col--;
            }
        }
        return count;
    }
}
