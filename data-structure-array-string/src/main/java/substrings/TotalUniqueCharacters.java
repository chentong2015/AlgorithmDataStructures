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
        System.out.println(instance.uniqueLetterStringPlus("LEETCODE"));
    }

    // TODO. 判断每个字符对于结果的贡献: 判断字符前后的起始点
    // Unique表示字符只能在子字符串中出现一次
    // countUniqueChars("ABC") = 3
    // countUniqueChars("ABA") = 1
    //
    // s = "ABC" -> 10
    // "A","B","C","AB","BC" and "ABC"
    // 1 + 1 + 1 + 2 + 2 + 3 = 10
    //
    // s = "ABA" -> 8 = 2 + 2 + 4
    // "A","B","A","AB","BA" and "ABA"
    // 1 + 1 + 1 + 2 + 2 + 1 = 8
    //
    // s = "LEETCODE" -> 92
    //
    // O(26*N)
    // O(1)
    public int uniqueLetterString(String s) {
        int result = 0;
        for (int offset = 0; offset < 26; offset++) {
            char c = (char) ('A' + offset);
            int lastIndex = -1;
            int index = 0;
            while (index < s.length()) {
                if (c == s.charAt(index)) {
                    int nextIndex = index + 1;
                    while (nextIndex < s.length() && s.charAt(nextIndex) != c) {
                        nextIndex++;
                    }
                    // TODO. 必须使用两个坐标来计算贡献
                    result += (index - lastIndex) * (nextIndex - index);
                    lastIndex = index;
                    index = nextIndex;
                } else {
                    index++;
                }
            }
        }
        return result;
    }

    public int uniqueLetterStringPlus(String s) {
        // [prev, last] positions for each char
        int[][] index = new int[26][2];
        for (int[] row : index) {
            Arrays.fill(row, -1);
        }

        // 在循环的过程中记录[prev, last]双坐标，并累加计算
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'A';
            int prev = index[c][0];
            int last = index[c][1];
            result += (last - prev) * (i - last);
            index[c][0] = last; // set last to prev
            index[c][1] = i;    // set index to last
        }

        // 计算最后一轮坐标产生的贡献
        for (int c = 0; c < 26; c++) {
            int prev = index[c][0];
            int last = index[c][1];
            result += (last - prev) * (s.length() - last);
        }
        return result;
    }
}
