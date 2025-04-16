package substrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Total Appeal of A String
// The appeal of a string is the number of distinct characters found in the string.
// The appeal of "abbca" is 3 because it has 3 distinct characters: 'a', 'b', and 'c'.
//
// Given a string s, return the total appeal of all of its substrings.
// A substring is a contiguous sequence of characters within a string.
//
// 1 <= s.length <= 10^5
// s consists of lowercase English letters.
public class TotalDistinctCharacters {

    // TODO. 只考虑每个元素对于子字符串统计的“贡献”
    // There are i - last[s[i]] possible start position,
    // and n - i possible end position,
    // So s[i] can contribute (i - last[s[i]]) * (n - i) points.
    //
    // a  b  b   c   a  c  f  g
    // 0  1  2   3   4  5  6  7
    //    x  x   x   a
    //                  -  -  -
    //    从前面非a的位置出发
    //               结合后面的数目
    //
    // O(n)
    // O(1) 不造成时间复杂度
    public static long appealSum(String s) {
        // 记录char字符上一次出现的位置
        int[] lastIndex = new int[26];
        Arrays.fill(lastIndex, -1);

        long res = 0, n = s.length();
        for (int i = 0; i < s.length(); ++i) {
            int charId = s.charAt(i) - 'a';
            // 统计char能够在子字符串中贡献多少次统计
            res += (i - lastIndex[charId]) * (n - i);
            lastIndex[charId] = i;
        }
        return res;
    }
}
