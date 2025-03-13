package dp_programming.subsequence;

// Longest Common Subsequence
// Given two strings text1 and text2, return the length of their longest common subsequence.
// If there is no common subsequence, return 0.
// A common subsequence of two strings is a subsequence that is common to both strings.
public class LongestCommonSubsequence {

    // TODO. 二维DP数组解决LCS的问题，不断迭代最优的值
    // text1 = "abcde", text2 = "ace" -> ace -> 3
    // text1 = "abc", text2 = "abc" -> abc -> 3
    // text1 = "abc", text2 = "def" -> 0
    //
    // text1="agcat", text2="gac" -> (ac),(gc),and(ga) -> 2
    //     A  G	 C	A  T   +
    // G   0  0	 0	0  0   0
    // A   0  0  1  1  1   1
    // C   0  1  1  1  2   2
    // +   0  1  1  2  2   2
    //
    // O(n*m) 循环的次数
    // O(n*m) 通过DP数组来记录“历史组合序列”的所有数据
    public int longestCommonSubsequence(String str1, String str2) {
        // DP二维数组需要多出一行和一列的数据
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        // 如果相等，则在dp[i][j]的基础上累计公共长度+1
        // 如果不等，则取左侧(拿掉char1)和取上侧(拿掉char2)的更大值
        for (int i = 0; i < str1.length(); ++i) {
            for (int j = 0; j < str2.length(); ++j) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }
}