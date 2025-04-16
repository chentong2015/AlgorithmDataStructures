package substrings;

import java.util.Arrays;

// Count Unique Characters of All Substrings of a Given String
// Define a function countUniqueChars(s) that returns the number of unique characters in s.
//
// Given a string s, return the sum of countUniqueChars(t) where t is a substring of s.
// The test cases are generated such that the answer fits in a 32-bit integer.
//
// Notice that some substrings can be repeated so in this case
// you have to count the repeated ones too.
//
// 1 <= s.length <= 10^5
// s consists of uppercase English letters only.
public class TotalUniqueCharacters {

    public static void main(String[] args) {
        TotalUniqueCharacters instance = new TotalUniqueCharacters();
        System.out.println(instance.uniqueLetterString("ABA"));
    }

    // TODO. Unique 唯一表示: 不统计重复的字符
    // s = "ABC" -> 10
    // "A","B","C","AB","BC" and "ABC"
    // 1 + 1 + 1 + 2 + 2 + 3 = 10
    //
    // s = "ABA" -> 8
    // "A","B","A","AB","BA" and "ABA"
    // 1 + 1 + 1 + 2 + 2 + 1 = 8
    //
    // s = "LEETCODE" -> 92
    //
    // O(N)
    // O(1)
    public int uniqueLetterString(String s) {
        int[] lastIndex = new int[26];
        Arrays.fill(lastIndex, -1);

        int result = 0;
        int length = s.length();
        for (int index = 0; index < length; index++) {
            int charIndex = s.charAt(index) - 'A';
            result += (index - lastIndex[charIndex]) * (length - index);
            lastIndex[charIndex] = index;
        }
        return result;
    }
}
