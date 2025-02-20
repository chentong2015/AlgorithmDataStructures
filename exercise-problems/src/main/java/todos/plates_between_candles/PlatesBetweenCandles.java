package todos.plates_between_candles;

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

    // TODO. 找到影响时间复杂度的两个本质原因: 循环可能造成O(N)复杂度
    // "||**||**|*"
    //     *||**|, query[3, 8]
    //
    // "**|**|***|",
    //    |**|, query[2,5]
    //       |***|, query[5,9]
    //
    // *||**| 判断每个子区间中左边和右边第一个"|"的出现
    // |***|
    //
    // O(n1+n2+n3+n4....) 时间复杂度取决于要取的片段总长
    // O(length)  空间复杂度为Query的数量
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int idResult = 0;
        int[] result = new int[queries.length];

        for (int[] query: queries) {
            // TODO. 无需循环完整个Query区间范围内的数据
            // TODO. 不能使用二分查找，可能错过要查找的目标字符
            int idFirstCandle = -1;
            for (int index = query[0]; index <= query[1]; index++) {
                if (s.charAt(index) == '|')  {
                    idFirstCandle = index;
                    break;
                }
            }
            int idLastCandle = -1;
            if (idFirstCandle != -1) {
                for (int index = query[1]; index >= query[0]; index--) {
                    if (s.charAt(index) == '|')  {
                        idLastCandle = index;
                        break;
                    }
                }
            }

            // TODO. 循环Query区间范围内的数据也可以避免
            int count = 0;
            if (idFirstCandle != -1 && idLastCandle != -1 && idFirstCandle + 1 < idLastCandle) {
                for (int index = idFirstCandle + 1; index < idLastCandle; index++) {
                    if (s.charAt(index) == '*') {
                        count++;
                    }
                }
            }
            result[idResult++] = count;
        }
        return result;
    }
}
