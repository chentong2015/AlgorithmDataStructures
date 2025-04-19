package dp_programming.subsequences;

// Minimum Insertion Steps to Make a String Palindrome
// In one step you can insert any character at any index of the string
// Return the minimum number of steps to make s palindrome
//
// 1 <= s.length <= 500
//s consists of lowercase English letters.
public class MinInsertionStringPalindrome {

    // TODO: 利用LCS和自身的反转字符串进行比较
    // 使用LCS比较原字符串和反转字符串有多少公共的char序列
    // 排除掉公共字符序列的数量，不同字符的数量则必须通过插入char来形成"回文效果"
    //
    // s = "zzazz" -> 0
    // s = "mbadm" -> "mbdadbm" or "mdbabdm" -> 2
    //      mdabm
    // s = "leetcode" -> "leetcodocteel" -> 5 需要插入5个字符才能构成回文
    //      edocteel
    //
    // O(N + N*N)
    // O(N*N)
    public int minInsertions(String str) {
        String reverseStr = new StringBuilder(str).reverse().toString();
        int lcsLength = longestCommonSubsequence(str, reverseStr);
        return str.length() - lcsLength;
    }

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
