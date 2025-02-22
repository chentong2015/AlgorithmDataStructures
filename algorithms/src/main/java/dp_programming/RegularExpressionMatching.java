package dp_programming;

// Regular Expression Matching
// Given an input string s and a pattern p, implement regular expression matching
// with support for '.' and '*' where:
//  - '.' Matches any single character.
//  - '*' Matches zero or more of the preceding element.
//
// s = "ab", p = ".*"      -> true
// s = "aab", p = "c*a*b"  -> true 第一个c字符有可能重复0次
public class RegularExpressionMatching {

    // TODO. Up-Bottom Variation 自顶向下
    // 分而治之，拆分问题，使用二维数组来存储判断的结果值，避免递归 O(TP) O(TP)
    public boolean isMatchUpBottom(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;
        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                boolean first_match = (i < text.length()
                        && (text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    // TODO. Bottom-Up Variation 自底向上
    private PatternResult[][] memo;

    public boolean isMatchBottomUp(String text, String pattern) {
        memo = new PatternResult[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == PatternResult.TRUE;
        }
        boolean ans;
        if (j == pattern.length()) {
            ans = i == text.length();
        } else {
            boolean first_match = (i < text.length() && (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));
            if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                ans = (dp(i, j + 2, text, pattern) || first_match && dp(i + 1, j, text, pattern));
            } else {
                ans = first_match && dp(i + 1, j + 1, text, pattern);
            }
        }
        memo[i][j] = ans ? PatternResult.TRUE : PatternResult.FALSE;
        return ans;
    }

    enum PatternResult {
        TRUE,
        FALSE
    }
}
