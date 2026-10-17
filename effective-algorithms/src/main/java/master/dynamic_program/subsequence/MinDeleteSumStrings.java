package master.dynamic_program.subsequence;

// Minimum ASCII Delete Sum for Two Strings
// Given two strings s1 and s2,
// return the lowest ASCII sum of deleted characters to make two strings equal.
//
// 1 <= s1.length, s2.length <= 1000
// s1 and s2 consist of lowercase English letters.
public class MinDeleteSumStrings {

    // TODO. DP动态编程: 找最长的LCS公共序列，使得删除的字符最少(SUM最小)
    // s1 = "sea", s2 = "eat" -> 231
    // Deleting "s" from "sea" adds ASCII value of "s" (115)
    // Deleting "t" from "eat" adds ASCII value of "t" (116)
    //
    // s1 = "delete", s2 = "leet" -> 100+101+101+101 = 403
    // Deleting "dee" from "delete" to turn the string into "let",
    // Deleting "e" from "leet"
    //
    //     '' e    a    t    S2
    // ''  0  0    0    0
    // s   0  0    0    0
    // e   0  101  101  101
    // a   0  101  198  198 结果是最长公共子序列的ASCII和
    // S1
    //
    // O(M*N)
    // O(M*N)
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i=1; i <= m; i++) {
            for (int j=1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + s1.charAt(i-1);
                } else {
                    // 取排除char1或排除char2累计的更大ASCII和
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // 计算被扣除ASCII码值总和, 两倍扣除
        int total = 0;
        for (char c : s1.toCharArray()) total += c;
        for (char c : s2.toCharArray()) total += c;
        return total - 2 * dp[n][m];
    }
}