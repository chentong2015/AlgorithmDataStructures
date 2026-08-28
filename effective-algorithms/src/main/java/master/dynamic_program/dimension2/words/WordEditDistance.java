package master.dynamic_program.dimension2.words;

// Edit Distance
// Given two strings word1 and word2,
// return the minimum number of operations required to convert word1 to word2.
// You have the following three operations permitted on a word:
//  - Insert a character
//  - Delete a character
//  - Replace a character
//
// 0 <= word1.length, word2.length <= 500
// word1 and word2 consist of lowercase English letters
public class WordEditDistance {

    // TODO. 金典DB二维数组推导规律, 多创建一行一列用于累计计算
    // word1 = "horse", word2 = "ros" -> 3
    //       h  o  r  s  e
    //    0  1  2  3  4  5 /第一行直接计算
    // r  1  1  2  2  3  4
    // o  2  2  1  2  3  4
    // s  3  3  2  2  2  3 > answer
    //    /第一列直接计算
    //
    public static int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        int[][] dp = new int[length2 + 1][length1 + 1];
        for (int i = 1; i <= length2; ++i) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= length1; ++j) {
            dp[0][j] = j;
        }

        for (int row = 1; row <= length2; row++) {
            for (int col = 1; col <= length1; col++) {
                if (word2.charAt(row - 1) == word1.charAt(col - 1)) {
                    // 取左上角值(使用当前字符)，不做累加
                    dp[row][col] = dp[row-1][col-1];
                } else {
                    // 取三个周围最小值，做累加1
                    int tempMin = Math.min(dp[row-1][col], dp[row][col-1]);
                    dp[row][col] = Math.min(tempMin, dp[row-1][col-1]) + 1;
                }
            }
        }
        return dp[length2][length1];
    }
}