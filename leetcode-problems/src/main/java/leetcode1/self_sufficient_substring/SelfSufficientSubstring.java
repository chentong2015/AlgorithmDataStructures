package leetcode1.self_sufficient_substring;

import java.util.Arrays;

// Find Longest Self-Contained Substring
// A self-sufficient proper substring is one where
// - the substring is not the entire string s
// - no letter that occurs inside the substring also occurs outside the substring
//
// 2 <= s.length <= 50000
// s consists only of lowercase english letters
//
// amazonservices -> 11
// amazon      6  Yes
// services    8  Yes
// azonse      6  No
// zonservices 11 Yes
public class SelfSufficientSubstring {

    public static void main(String[] args) {
        System.out.println(findLongestLength("amazonservices")); // 11
        System.out.println(findLongestLength("abacd")); // 4
        System.out.println(findLongestLength("aabbcccabc")); // -1
        System.out.println(findLongestLength("abab")); // -1
    }

    // TODO. 最终子字符串一定以某个字符开头，最多起始位置只有26个，最多循环26次 ！！
    //         a  b  c  d  e ...
    // start = 0  1  -1 4  2
    // end =   4  7  0  0  2
    //
    // O(26*N) = O(N)
    // O(26) = O(1)
    public static int findLongestLength(String fullString) {
        int[] start = new int[26];
        int[] end = new int[26];
        Arrays.fill(start, -1);

        // 字符的起始位置只记录一次，结尾位置不断更新
        for (int index = 0; index < fullString.length(); index++) {
            int charIndex = fullString.charAt(index) - 'a';
            if (start[charIndex] == -1) {
                start[charIndex] = index;
            }
            end[charIndex] = index;
        }
        return getAns(fullString, start, end);
    }

    // TODO. 算法核心
    // - 没有出现的字符不可能作为Substring的开头字符
    // - 如果遍历的新字符在前面出现过，则以startIndex位置往后不可能构成有效结果
    // - 根据遍历的新字符，刷新最右边的endIndex
    // - 如果index==endIndex，说明遍历到了Substring的最右边位置
    // - 最终Substring最大的长度不能取整个字符串的长度
    private static int getAns(String fullString, int[] start, int[] end) {
        int maxLength = -1;
        for (int k = 0; k < 26; ++k) {
            int startIndex = start[k];
            if (startIndex == -1) {
                continue;
            }
            int endIndex = end[k];

            // TODO. 从当前startIndex位置开始遍历，单个字符也算有效长度
            for (int index = startIndex; index < fullString.length(); index++) {
                int newCharIndex = fullString.charAt(index) - 'a';
                if (start[newCharIndex] < startIndex) {
                    break;
                }
                endIndex = Math.max(endIndex, end[newCharIndex]);

                // TODO. 只有index == endIndex才是有效位置
                if (index == endIndex) {
                    int length = index - startIndex + 1;
                    if (length < fullString.length()) {
                        maxLength = Math.max(maxLength, length);
                    }
                }
            }
        }
        return maxLength;
    }
}