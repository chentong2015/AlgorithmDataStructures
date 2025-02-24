package leetcode2.heart_beats;

// Count the Hidden Sequences
// You are given a 0-indexed array of n integers differences
// which describes the differences between each pair of consecutive integers of a hidden sequence of length (n + 1)
// More formally, call the hidden sequence hidden, then we have that differences[i] = hidden[i + 1] - hidden[i].
// You are further given two integers lower and upper that describe the inclusive range of values [lower, upper]
// that the hidden sequence can contain.
//
// given differences = [1, -3, 4], lower = 1, upper = 6
// the hidden sequence is a sequence of length 4 whose elements are in between 1 and 6 (inclusive).
// [3, 4, 1, 5] and [4, 5, 2, 6] are possible hidden sequences.
// [5, 6, 3, 7] is not possible since it contains an element greater than 6.
// [1, 2, 3, 4] is not possible since the differences are not correct.
//
// Return the number of possible hidden sequences there are.
// If there are no possible sequences, return 0.
//
// n == differences.length
// 1 <= n <= 10^5
// -10^5 <= differences[i] <= 10^5
// -10^5 <= lower <= upper <= 10^5
public class CountHiddenSequences {

    // TODO. 设置初始取值为X，遍历diff数组计算它的最大上下偏差(范围)
    // differences = [1,-3,4], lower = 1, upper = 6 -> 2
    // - [3, 4, 1, 5]
    // - [4, 5, 2, 6]
    //
    // differences = [3,-4,5,1,-2], lower = -4, upper = 5 -> 4
    // - [-3, 0, -4, 1, 2, 0]
    // - [-2, 1, -3, 2, 3, 1]
    // - [-1, 2, -2, 3, 4, 2]
    // - [ 0, 3, -1, 4, 5, 3]
    //     1  4   0  5  ko
    //
    // 考虑极其特殊的情况: 单个数据或diff同正或同负
    // differences = [4,-7,2], lower = 3, upper = 6 -> 0
    // differences = [-40], lower =-46, upper =53 -> 60
    // differences = [83702,-5216], lower =-82788, upper =14602 -> 13689
    //
    // O((U-L) * N) -> O(N) 最佳的时间复杂度
    // O(1) no extra space
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long upperDiff = Integer.MIN_VALUE;
        long lowerDiff = Integer.MAX_VALUE;

        int startValue = 0;
        for (int index =0; index < differences.length; index++) {
            startValue += differences[index];

            upperDiff = Math.max(upperDiff, startValue);
            lowerDiff = Math.min(lowerDiff, startValue);
        }

        // TODO. 根据不同情况取rangeDiff最大的偏值
        long rangeDiff;
        if (upperDiff == lowerDiff) {
            rangeDiff = Math.abs(upperDiff); // 取相同值的绝对值
        } else {
            if (lowerDiff >= 0) {
                rangeDiff = upperDiff;
            } else if (upperDiff <= 0) {     // 判断同正或同负数的情况
                rangeDiff = Math.abs(lowerDiff);
            } else {
                rangeDiff = upperDiff - lowerDiff; // 取总的range便值
            }
        }

        int rangeTotal = upper - lower + 1;
        if (rangeTotal <= rangeDiff) {
            return 0;
        }
        return rangeTotal - (int)rangeDiff;
    }
}
