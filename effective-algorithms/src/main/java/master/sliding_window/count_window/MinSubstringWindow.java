package master.sliding_window.count_window;

// Minimum Window Substring
// Given two strings s and t, return the min window in s
// which will contain all characters in t.
//
// If there is no such window in s that covers all characters in t,
// return the empty string ""
//
// m == s.length
// n == t.length
// 1 <= m, n <= 10^5
// s and t consist of uppercase and lowercase English letters.
public class MinSubstringWindow {

    // TODO. 滑动窗口加强版: 统计抵消
    // 判断t中字符是否抵消完成, 构成有效窗口, 将left坐标右移缩小窗口
    // 全英文字符则直接取ASCII整型值范围的数组
    //
    // s="ADOBECODEBANC", t="ABC" -> "BANC"
    // s = "a", t = "a"  -> "a"
    // s = "a", t = "aa" -> ""
    //
    // O(N+M)
    // O(N)
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.isEmpty() || t.isEmpty() || s.length() < t.length()) {
            return "";
        }
        int[] countArray = new int[128];
        for (char c : t.toCharArray()) {
            countArray[c]++;
        }

        String result = "";
        int resultLength = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;
        int countLeft = t.length();
        while (right < s.length()) {
            if (countArray[s.charAt(right)] > 0) {
                countLeft--; // 记录t中有效统计的抵消
            }
            countArray[s.charAt(right)]--; // 直接扣除统计(可能导致改位置统计为负)
            right++;

            while (countLeft == 0) {
                if (right - left < resultLength) {
                    result = s.substring(left, right);
                    resultLength = right - left;
                }
                if (countArray[s.charAt(left)] == 0) {
                    countLeft++; // 添加新的待抵消的字符
                }
                countArray[s.charAt(left)]++; // 直接还原统计(将负数的统计回复)
                left++;
            }
        }
        return result;
    }
}