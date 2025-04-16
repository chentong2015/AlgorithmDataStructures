package substrings;

// Vowels of All Substrings
// Given a string word, return the sum of the number of vowels
// ('a', 'e', 'i', 'o', and 'u') in every substring of word.
//
// A substring is a contiguous (non-empty) sequence of characters within a string.
// Note: Due to the large constraints, the answer may not fit in a signed 32-bit integer.
//
// 1 <= word.length <= 10^5
// word consists of lowercase English letters.
public class CountVowelsSubstrings {

    // TODO. 结果的统计具有很明显的特征点：累计字符出现在所有子字符串中的次数
    // word = "aba"
    // "a", "ab", "aba", "b", "ba", and "a".
    // 1 + 1 + 2 + 0 + 1 + 1 = 6
    //
    // word = "abc"
    // "a", "ab", "abc", "b", "bc", and "c".
    // 1 + 1 + 1 + 0 + 0 + 0 = 3
    //
    // word = "ltcd" -> 0
    //
    // 以字符作为分割线，组合左右子字符串的所有可能 ！
    // b b b ‘a’ b b c c
    // b b b  a
    //        * b b c c
    //
    // O(N)
    // O(1) 直接使用"aeiou"字符串来判断元音字符
    public long countVowels(String word) {
        long res = 0;
        int n = word.length();
        for (int i = 0; i < n; ++i) {
            char c = word.charAt(i);
            if ("aeiou".indexOf(c) >= 0) {
                // 使用数学公式快速累加
                res += (long) (i + 1) * (n - i);
            }
        }
        return res;
    }
}
