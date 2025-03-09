package arrays.prefix_sum.plates_between_candles;

public class PlatesBetweenCandlesPrefixSum {

    // TODO. 提前三次遍历数组并"预处理"之后被用于查询的数据
    // "||**||**|*"
    //     *||**|, query[3, 8]
    //
    // "**|**|***|"
    //    |**|, query[2,5]
    //       |***|, query[5,9]
    //
    // *||**| 判断每个子区间中左边和右边第一个"|"的出现
    // |***|
    //
    // O(N+N+N+M)
    // O(N+N+N+M)
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int length = s.length();
        int[] prefixSum = new int[length + 1];
        for (int i = 0; i < length; ++i) {
            prefixSum[i + 1] = prefixSum[i];
            if (s.charAt(i) == '*') {
                prefixSum[i + 1]++;
            }
        }

        // Calculate the nearest left candle positions
        int[] nearestLeftCandle = new int[length];
        for (int i = 0, lastCandleIndex = -1; i < length; i++) {
            if (s.charAt(i) == '|') {
                lastCandleIndex = i;
            }
            nearestLeftCandle[i] = lastCandleIndex;
        }

        // Calculate the nearest right candle positions
        int[] nearestRightCandle = new int[length];
        for (int i = length - 1, nextCandleIndex = -1; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                nextCandleIndex = i;
            }
            nearestRightCandle[i] = nextCandleIndex;
        }

        // TODO. 找到前后两个Candle坐标位置，通过前缀和计算中间剩余的数目
        int[] answer = new int[queries.length];
        for (int k = 0; k < queries.length; k++) {
            int startIndex = nearestRightCandle[queries[k][0]];
            int endIndex = nearestLeftCandle[queries[k][1]];
            if (startIndex >= 0 && endIndex >= 0 && startIndex < endIndex) {
                answer[k] = prefixSum[endIndex] - prefixSum[startIndex + 1];
            }
        }
        return answer;
    }
}
