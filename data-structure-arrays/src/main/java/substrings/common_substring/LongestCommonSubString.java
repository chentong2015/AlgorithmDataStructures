package substrings.common_substring;

// Longest Common Substring
// Given two strings str1 and str2,
// find the longest common substring for two strings
// return "" if there is no common substring
public class LongestCommonSubString {

    // TODO. 等效Longest Common Subsequence算法
    // "dgdsaeactyeyk" -> "actyey"
    // "abractyeyt"
    //
    // "123456789" -> "3456"
    // "4513456821"
    //
    // O(N*M) 使用动态编程避免了循环判断子字符串的次数
    // O(N*M)
    public int longestCommonSubString(String str1, String str2) {
        int maxLength = 0;
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    // 在前面的子字符串统计上累计结果
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        return maxLength;
    }
}
