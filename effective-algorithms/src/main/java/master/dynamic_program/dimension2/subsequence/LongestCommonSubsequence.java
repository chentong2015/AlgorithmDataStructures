package master.dynamic_program.dimension2.subsequence;

// Longest Common Subsequence (LCS)
// Given two strings text1 and text2, return the length of their longest common subsequence.
// If there is no common subsequence, return 0.
// A common subsequence of two strings is a subsequence that is common to both strings.
public class LongestCommonSubsequence {

    // TODO. Longest Common Subsequence 最长公共子序列
    // 如果相等，则在dp[i][j]基础上累计公共长度+1
    // 如果不等，则取拿掉char1)或拿掉char2的最长值
    //     A  G	 C	A  T   +
    // G   0  0	 0	0  0   0
    // A   0' 0  1  1’ 1   1
    // C   0  1  1  1  2’  2
    // +   0  1  1  2  2   2
    //
    // text1 = "abcde", text2 = "ace" -> ace -> 3
    // text1 = "abc", text2 = "abc" -> abc -> 3
    // text1 = "abc", text2 = "def" -> 0
    // text1="agcat", text2="gac" -> (ac),(gc),and(ga) -> 2
    //
    // O(n*m) 循环的次数
    // O(n*m) 通过DP数组来记录“历史组合序列”的所有数据
    public int longestCommonSubsequence(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp[n][m];
    }
}