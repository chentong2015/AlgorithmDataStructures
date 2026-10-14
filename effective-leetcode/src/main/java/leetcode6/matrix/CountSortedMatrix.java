package leetcode6.matrix;

public class CountSortedMatrix {

    // TODO. 从"右上角"开始遍历, 统计比value更小的值(找的值不一定存在)
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
                count += col + 1; // 说明一行的数据都比target小
                row++;            // 移动到下一行
            } else {
                col--;            // 往左侧移动进行统计
            }
        }
        return count;
    }
}
