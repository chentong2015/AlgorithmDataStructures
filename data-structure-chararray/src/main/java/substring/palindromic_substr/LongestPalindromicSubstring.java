package substring.palindromic_substr;

// Longest Palindromic Substring
// Given a string s, return the longest palindromic substring in s.
//
// 1 <= s.length <= 1000
// s consist of only digits and English letters.
public class LongestPalindromicSubstring {

    // TODO. 从某个中心点往两边扩散
    // s = "babad" -> "bab", "aba"
    // s = "cbbd" -> "bb"
    //
    // O(N*N)
    // O(1)
    public String longestPalindrome(String s) {
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int odd = expandAroundCenter(s, i, i);
            int even = expandAroundCenter(s, i, i + 1);
            int length = Math.max(odd, even);

            // 用起始位置记录最后结果
            if (length > end - start) {
                start = i - (length - 1) / 2;
                end = i + length / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    // 注意有两种扩散方式: 总字符单数和双数
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
