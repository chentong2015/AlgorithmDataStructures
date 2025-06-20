package leetcode3;

// Best Sightseeing Pair
// You are given an integer array values where values[i] represents the value of the ith sightseeing spot.
// The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j:
// the sum of the values of the sightseeing spots, minus the distance between them.
// Return the maximum score of a pair of sightseeing spots.
//
// 2 <= values.length <= 5 * 10^4
// 1 <= values[i] <= 1000
public class BestSightseeingPair {

    // TODO. (values[i] + i) + (values[j] - j)
    //  本质上结果 = 起点 + 终点，遍历过程中不断选择更大的起点
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
        // 初始化起点的计算公式
        int startSpot = values[0] + 0;
        int maxScore = Integer.MIN_VALUE;

        for (int i = 1; i < values.length; i++) {
            // TODO. 计算index位置所能得到的最大Score值
            if (startSpot + values[i] - i > maxScore) {
                maxScore = startSpot + values[i] - i;
            }

            // TODO. 更新起始点以获取后面计算的更大值
            if (values[i] + i > startSpot) {
                startSpot = values[i] + i;
            }
        }
        return maxScore;
    }
}
