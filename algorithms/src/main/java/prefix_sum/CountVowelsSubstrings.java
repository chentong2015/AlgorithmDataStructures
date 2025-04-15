package prefix_sum;

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

    // TODO. Prefix Sum 前缀和: 关于特定数据的统计
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
    // a b a c
    // 1 0 1 0
    //
    // a a a a a e e e e
    // 0 1 2 3 4
    //
    public long countVowels(String word) {


        return 0;
    }
}
