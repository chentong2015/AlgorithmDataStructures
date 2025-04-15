package array.array2.painted_row_column;

import java.util.HashMap;
import java.util.Map;

// First Completely Painted Row or Column
// You are given a 0-indexed integer array arr, and an m x n integer matrix mat
// arr and mat both contain all the integers in the range [1, m * n].
// - Go through each index i in arr starting from index 0
// - paint the cell in mat containing the integer arr[i]
// Return the smallest index i
// at which either a row or a column will be completely painted in mat
//
// 注意数据的约束范围和唯一性
// m == mat.length
// n = mat[i].length
// arr.length == m * n
// 1 <= arr[i], mat[r][c] <= m * n
// All the integers of arr are unique.
// All the integers of mat are unique.
public class FirstPaintedRowColumn {

    // TODO. 利用数据值约束 + 利用映射存储，快速判断数据"成Row"或"成Col"
    // [1,4], -> [1,3,4,2] -> 2
    // [2,3]
    //
    // [3,2,5], -> [2,8,7,4,1,3,5,6,9] -> 3
    // [1,4,6],
    // [8,7,9]
    //
    // [4,3,5], -> [1,4,5,2,6,3] -> 1
    // [1,2,6]
    //
    // 1 -> 1,0
    // 4 -> 0,0
    //
    // rows: +1 +1   >= n 比较列数目
    // cols: +2 0 0  >= m 比较行数目
    //
    // O(M*N + L)      最佳时间复杂度
    // O(M*N + M + N)  Map和数组的空间开销
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int rowLength = mat.length;
        int colLength = mat[0].length;

        // Loop to get mapping indexes
        Map<Integer, int[]> map = new HashMap<>();
        for (int i=0; i < rowLength; i++) {
            for (int j=0; j < colLength; j++) {
                map.put(mat[i][j], new int[] {i, j});
            }
        }

        int[] rows = new int[rowLength];
        int[] cols = new int[colLength];
        for (int index = 0; index < arr.length; index++) {
            int[] values = map.get(arr[index]);
            rows[values[0]]++;
            cols[values[1]]++;
            // TODO. 以列长度判断rows统计，以行长度判断cols统计
            if (rows[values[0]] >= colLength || cols[values[1]] >= rowLength) {
                return index;
            }
        }
        return arr.length - 1;
    }
}
