package master.dynamic_program.dimension2;

// Regular Expression Matching
// Given an input string s and a pattern p, implement regular expression matching
// with support for '.' and '*' where:
//  - '.' Matches any single character.
//  - '*' Matches zero or more of the preceding element.
public class RegularExpressionMatching {

    // TODO. DP二维数组动态推理字符匹配
    // s = "ab", p = ".*"      -> true
    // s = "aab", p = "c*a*b"  -> true 第一个c字符有可能重复0次
    //
    //    '' c * a * b
    // '' T    T   T    初始化第一行
    // a       T T T
    // a       T T T
    // b       T   T T  结果位置
    //
    // O(N*M)
    // O(N*M)
    public boolean isMatchBottomUpForward(String text, String pattern) {
        int m = text.length();
        int n = pattern.length();

        // 初始化: 空字符串匹配空模式串
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // 初始化: 空字符串匹配a*、a*b*等模式
        for (int j = 2; j <= n; j++) {
            if (pattern.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j-2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char ch = text.charAt(i - 1);
                char p = pattern.charAt(j - 1);
                if (p == '*') {
                    dp[i][j] = dp[i][j-2]; // x* 匹配0次, 累计之前状态

                    char prevChar = pattern.charAt(j - 2);   // x* 匹配至少1次
                    if (ch == prevChar || prevChar == '.') { // 判断前个字符匹配
                        dp[i][j] = dp[i][j] || dp[i-1][j];   // 取上行同列的结果
                    }
                } else {
                    if (ch == p || p == '.') {   // 当前字符匹配的两种情况
                        dp[i][j] = dp[i-1][j-1]; // 累计左上角字符的判断
                    }
                }
            }
        }
        return dp[m][n];
    }
}
