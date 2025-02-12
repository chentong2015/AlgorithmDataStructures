package leetcode2;

import java.lang.reflect.Array;
import java.util.Arrays;

// Sum in a Matrix
// You are given a 0-indexed 2D integer array nums
// Initially, your score is 0.
// Perform the following operations until the matrix becomes empty:
// - From each row in the matrix, select the largest number and remove it.
//   In the case of a tie, it does not matter which number is chosen.
// - Identify the highest number amongst all those removed in step 1
//   Add that number to your score.
// Return the final score.
//
// 1 <= nums.length <= 300
// 1 <= nums[i].length <= 500
// 0 <= nums[i][j] <= 10^3
public class SumInMatrix {

    // TODO. 每行的最值和最值比较，第二大值和第二大值比较，取所有的最大值
    // [7,2,1], -> 15
    // [6,4,2],
    // [6,5,3],
    // [3,2,1],
    //
    // 7, 6, 6, 3 -> 7 第一大值
    // 2, 4, 5, 2 -> 5 第二大值
    // 1, 2, 3, 1 -> 3
    //
    // O(R*C*logC + R*C) 对行数据排序，依次取对应位置的值
    // O(1)              无需额外存储空间，直接在原数组操作
    public int matrixSum(int[][] nums) {
        for (int[] row : nums) {
            Arrays.sort(row);
        }

        // 排序完成后直接取对应位置比较，取每行的指定列
        int result  = 0;
        for (int col = 0; col < nums[0].length; col++) {
            int maxValue = nums[0][col];
            for (int[] num : nums) {
                if (maxValue < num[col]) {
                    maxValue = num[col];
                }
            }
            result += maxValue;
        }

        return result;
    }
}
