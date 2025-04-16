package prefix_sum;

// Plates Between Candles
// You are given a 0-indexed string s consisting of characters '*' and '|' only,
// where a '*' represents a plate and a '|' represents a candle.
// You are also given a 0-indexed 2D integer array queries
// where queries[i] = [lefti, righti] denotes the substring s[lefti...righti] (inclusive).
//
// For each query, you need to find the number of plates between candles that are in the substring.
// A plate is considered between candles if there is at least one candle to its left
// and at least one candle to its right in the substring.
//
// Return an integer array answer where answer[i] is the answer to the ith query.
// 3 <= s.length <= 10^5
// s consists of '*' and '|' characters.
// 1 <= queries.length <= 10^5
// queries[i].length == 2
// 0 <= lefti <= righti < s.length
public class PlatesBetweenCandles {

    // TODO. Prefix Sum前缀和算法：典型"预处理"数据
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
