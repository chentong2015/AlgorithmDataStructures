package data_structure.dynamic_program.one_dimension;

import java.util.Arrays;

// Russian Doll Envelopes
// You are given a 2D array of integers envelopes
// where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
// One envelope can fit into another if and only if
// both the width and height of one envelope are greater than the other envelope's width and height.
// Return the maximum number of envelopes you can Russian doll (put one inside the other).
//
// Note: You cannot rotate an envelope.
// 1 <= envelopes.length <= 10^5
// envelopes[i].length == 2
// 1 <= wi, hi <= 10^5
public class RussianDollEnvelopes {

    // TODO. DP算法: DP数组在循环过程中记录二分查找序列，仅维持结果长度的数组
    // [5,4],[6,4],[6,7],[2,3] -> 3
    // [2,3],[5,4],[6,4],[6,7]
    //
    // [1,1],[1,1],[1,1] -> 1
    //
    // [2,100],[3,200],[4,300],[5,500],[5,400],[5,250],[6,370],[6,360],[7,380]
    //    1       2       3       4       4      3        4       4       5
    // [2,100],[3,200],[4,300],[6,370],[7,380] -> 5
    //    100 -> 200 -> 300 -> 370 -> 380
    //
    // O(N*logN)
    // O(1)
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (arr1, arr2) -> {
            if (arr1[0] == arr2[0]) {
                return arr2[1] - arr1[1];
            } else {
                return arr1[0] - arr2[0];
            }
        });

        int maxLength = 0;
        int[] dp = new int[envelopes.length];
        for (int[] envelope : envelopes) {
            int index = Arrays.binarySearch(dp, 0, maxLength, envelope[1]);
            if (index < 0) {
                index = -(index + 1);
            }
            dp[index] = envelope[1];

            // 当追加的位置在扩容位置则累加长度
            if (index == maxLength) {
                maxLength++;
            }
        }
        return maxLength;
    }
}
