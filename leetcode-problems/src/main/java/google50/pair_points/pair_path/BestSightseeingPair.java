package google50.pair_points.pair_path;

// Best Sightseeing Pair
// You are given an integer array values where values[i] represents the value of the ith sightseeing spot.
// The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j:
// the sum of the values of the sightseeing spots, minus the distance between them.
// Return the maximum score of a pair of sightseeing spots.
//
// 2 <= values.length <= 5 * 10^4
// 1 <= values[i] <= 1000
public class BestSightseeingPair {

    // TODO. 变换计算公式: 一维空间(left, right)挑选两个位置的值
    //  values[i] + values[j] + i - j = (values[i] + i) + (values[j] - j)
    //
    // [8,1,5,2,6]
    //  0 1 2 3 4
    // 8+5+0-2 = (8+0)+(5-2) = 11
    //
    // O(N)
    // O(1)
    public int maxScoreSightseeingPair(int[] values) {
        // 初始化起点的计算公式
        int startSpot = values[0] + 0;
        int maxScore = Integer.MIN_VALUE;

        for (int i = 1; i < values.length; i++) {
            // TODO. 计算index作为右侧点能得到的值
            if (startSpot + (values[i] - i) > maxScore) {
                maxScore = startSpot + (values[i] - i);
            }

            // TODO. 判断index位置能够作为左侧起始点
            if (values[i] + i > startSpot) {
                startSpot = values[i] + i;
            }
        }
        return maxScore;
    }
}
