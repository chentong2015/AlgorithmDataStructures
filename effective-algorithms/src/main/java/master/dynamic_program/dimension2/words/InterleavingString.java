package master.dynamic_program.dimension2.words;

// Interleaving String
// Find whether s3 is formed by an interleaving of s1 and s2
// s = s1 + s2 + ... + sn
// t = t1 + t2 + ... + tm
// The interleaving is
//  s1 + t1 + s2 + t2 + s3 + t3 + ...
//  or t1 + s1 + t2 + s2 + t3 + s3 + ...
public class InterleavingString {

    // TODO. 利用DP二维数组来累计拼接的完整通路, 罗列所有组合情况
    //
    // s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
    //    '' d  b  b  c  a
    // '' T  F  F  F  F  F
    // a  T  F  F  F  F  F
    // a  T  T  T  T  T  F
    // b  F  T  T  F  T  F
    // c  F  F  T  T  T  T
    // c  F  F  F  T  F  True
    //    a a d b b c b c a c
    //
    // O(2^m+n) 结果的每一个位置可能来自S或者T
    public boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        if (l1 + l2 != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[l1 + 1][l2 + 1];
        dp[0][0] = true;

        // Fill first column (using only s1)
        for (int i = 1; i <= l1; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        // Fill first row (using only s2)
        for (int j = 1; j <= l2; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        // Fill rest of the DP table 组合两种通路的可能
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                boolean fromS1 = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                boolean fromS2 = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                dp[i][j] = fromS1 || fromS2;
            }
        }
        return dp[l1][l2];
    }
}