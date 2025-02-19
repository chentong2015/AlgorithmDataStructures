package todos.prefix_sum;

// 使用Prefix Sum算法结构来解决特定统计的问题
public class PrefixSumTemplate {

    // Method to calculate the number of plates between candles based on a set of queries
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int length = s.length(); // Length of the string

        // Array to hold the prefix sum of plates up to each position
        int[] prefixSum = new int[length + 1];
        // Calculate the prefix sum of plates
        for (int i = 0; i < length; ++i) {
            prefixSum[i + 1] = prefixSum[i] + (s.charAt(i) == '*' ? 1 : 0);
        }

        // Arrays to hold the index of the nearest left and right candles to each position
        int[] nearestLeftCandle = new int[length];
        int[] nearestRightCandle = new int[length];

        // Calculate the nearest left candle positions
        for (int i = 0, lastCandleIndex = -1; i < length; ++i) {
            if (s.charAt(i) == '|') {
                lastCandleIndex = i; // Update last seen candle
            }
            nearestLeftCandle[i] = lastCandleIndex;
        }

        // Calculate the nearest right candle positions
        for (int i = length - 1, nextCandleIndex = -1; i >= 0; --i) {
            if (s.charAt(i) == '|') {
                nextCandleIndex = i; // Update next seen candle
            }
            nearestRightCandle[i] = nextCandleIndex;
        }

        // Array to hold the results of queries
        int[] answer = new int[queries.length];

        // Iterate over each query to determine the number of plates between candles
        for (int k = 0; k < queries.length; ++k) {
            // Find the nearest right candle index from the start of the current query
            int startIndex = nearestRightCandle[queries[k][0]];
            // Find the nearest left candle index from the end of the current query
            int endIndex = nearestLeftCandle[queries[k][1]];

            // If valid candle indices are found and the start index is before the end index
            if (startIndex >= 0 && endIndex >= 0 && startIndex < endIndex) {
                // Calculate the number of plates by subtracting the prefix sums
                answer[k] = prefixSum[endIndex] - prefixSum[startIndex + 1];
            }
        }

        return answer;
    }
}
