package leetcode2;

// Best Sightseeing Pair
// You are given an integer array values where values[i] represents the value of the ith sightseeing spot.
// The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j:
// the sum of the values of the sightseeing spots, minus the distance between them.
// Return the maximum score of a pair of sightseeing spots.
//
// 2 <= values.length <= 5 * 10^4
// 1 <= values[i] <= 1000
public class BestSightseeingPair {

    // TODO. 只需存储最佳起始点，不断更新通项公式 (values[i] + i) + (values[j] - j)
    // [8,1,15,2,6]
    //  0 1 2 3 4
    // 8 + 5 + 0 - 2 = 11
    //
    // [1,2]
    // 1 + 2 + 0 - 1 = 2
    //
    // O(N)
    // O(1)
    public int maxScoreSightseeingPair(int[] values) {
        int maxScore = Integer.MIN_VALUE;
        int startSpot = values[0];

        for (int i = 1; i < values.length; i++) {
            // 从最佳的起始点计算adding累计效果
            if (startSpot + values[i] - i > maxScore) {
                maxScore = startSpot + values[i] - i;
            }

            // TODO. 此时index位置所能得到的最大Score已经计算出来
            //  如果有更大的起始位置，则从更大的起始位置往后计算才最有可能得出最大值
            if (values[i] + i > startSpot) {
                startSpot = values[i] + i;
            }
        }
        return maxScore;
    }
}
