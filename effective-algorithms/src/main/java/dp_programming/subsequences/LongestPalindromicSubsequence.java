package dp_programming.subsequences;

// Longest Palindromic Subsequence (LPS)
// Given a string s, find the longest palindromic subsequence's length in s
//
// 1 <= s.length <= 1000
// s consists only of lowercase English letters.
public class LongestPalindromicSubsequence {

    // // TODO: 最长"回文"子序列: 在中间字符序列的统计上往两边累计
    // s = "axbdba" -> "abdba" -> 5
    //    a  x  b  d  b  a
    // a  1  1  1  1  3  5  a和a相等，则在中间[xbdb]结果基础上+2
    // x     1  1  1  3  3
    // b        1  1  3’ 3  b和b相等，则在左下角[中间d]位置值上+2
    // d           1  1  1’ 字符不能，取[左边]或[下边]的最大值
    // b              1  1
    // a                 1  起点位置
    //
    // O(N*N)
    // O(N*N)
    public int longestPalindromeSubsequence(String s) {
        // DP数组暂存了中间过程累计的长度结果
        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // 累计找最长子序列的长度
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}
