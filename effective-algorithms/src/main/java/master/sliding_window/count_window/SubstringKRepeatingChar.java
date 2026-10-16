package master.sliding_window.count_window;

import java.util.Arrays;

// Longest Substring with At Least K Repeating Characters
// Given a string s and an integer k,
// return the length of the longest substring of s
// such that the frequency of each character in this substring is greater than or equal to k.
// if no such substring exists, return 0.
//
// 1 <= s.length <= 10^4
// s consists of only lowercase English letters.
// 1 <= k <= 10^5
public class SubstringKRepeatingChar {

    // TODO. 滑动窗口加强版: 唯一性字符统计
    //
    // "aaabb", k = 3    -> "aaa"   -> 3
    // "ababbc", k = 2   -> "ababb" -> 5
    // "bbaaacbd", k = 3 -> "aaa"   -> 3
    // "baaabcb", k = 3  -> "aaa"   -> 3
    //
    // O(26*N) 最大的离散字符为26个
    // O(1)
    public int longestSubstring(String s, int k) {
        int result = 0;
        char[] str = s.toCharArray();
        int[] countMap = new int[26];

        int numUniqueLetters = getNumUniqueLetters(s);
        for (int currUnique = 1; currUnique <= numUniqueLetters; currUnique++) {
            Arrays.fill(countMap, 0);

            int left = 0;
            int right = 0;
            int unique = 0;
            int countAtLeastK = 0;
            while (right < str.length) {
                if (unique <= currUnique) {
                    int idx = str[right] - 'a';
                    if (countMap[idx] == 0) {  // 只在起始首次点统计
                        unique++;
                    }
                    countMap[idx]++;
                    if (countMap[idx] == k) {  // 只在满足K频率点统计
                        countAtLeastK++;
                    }
                    right++;
                } else {
                    int idx = str[left] - 'a';
                    if (countMap[idx] == k) {
                        countAtLeastK--;
                    }
                    countMap[idx]--;
                    if (countMap[idx] == 0) {
                        unique--;
                    }
                    left++;
                }
                // TODO. 判断不同的字符数量，且每个字符都满足K频率的统计
                if (unique == currUnique && unique == countAtLeastK) {
                    result = Math.max(right - left, result);
                }
            }
        }
        return result;
    }

    int getNumUniqueLetters(String s) {
        boolean[] map = new boolean[26];
        int unique = 0;
        for (int i = 0; i < s.length(); i++) {
            int offset = s.charAt(i) - 'a';
            if (!map[offset]) {
                unique++;
                map[offset] = true;
            }
        }
        return unique;
    }
}
