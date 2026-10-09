package master.dynamic_program.dimension2;

// Wildcard Matching
// Given an input string (s) and a pattern (p),
// implement wildcard pattern matching with support for '?' and '*' where:
//  '?' Matches any single character.
//  '*' Matches any sequence of characters (including the empty sequence).
// The matching should cover the entire input string (not partial).
//
// s contains only lowercase English letters.
// p contains only lowercase English letters, '?' or '*'.
public class WildcardMatching {

    // TODO. DP二维数组动态推理: 关键在于'*'字符推理
    // s = "aa", p = "*"  -> true
    // s = "cb", p = "?a" -> false
    //
    //    '' *  a  *  b
    // '' T  T  F  F  F
    // a  F  T  T
    // d
    // c

    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true; // match empty with empty char

        // '?'不能匹配空字符
        for (int j=1; j <= m; j++) {
            if (p.charAt(j-1) == '*') {
                dp[0][j] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char ch = s.charAt(i - 1);
                char pCh = p.charAt(j - 1);
                if (pCh == '*') {
                    // TODO. "ab", "ab*" 或 "abc", "ab*" 两种模式
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                } else {
                    if (ch == pCh || pCh == '?') {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
            }
        }
        return dp[n][m];
    }
}
