package master.sliding_window.min_window;

// Minimum Window Substring
// Given two strings s and t, return the min window in s which will contain all characters in t.
// If there is no such window in s that covers all characters in t, return the empty string ""
// s and t consist of uppercase and lowercase English letters.
//
// m == s.length
// n == t.length
// 1 <= m, n <= 10^5
// s and t consist of uppercase and lowercase English letters.
public class MinSubstringWindow {

    // TODO. 将字符的包含转换到int数组中，判断字符统计的增减抵消
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
        // 直接取单个字符对应的ASCII整型值, 方便加减计算
        int[] countArray = new int[128];
        for (char c : t.toCharArray()) {
            countArray[c]++;
        }

        // 记录结果的起始点和长度
        int startIndex =0;
        int minLen = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;
        int countLeft = t.length();  // 剩余抵消的数目

        char[] charArray = s.toCharArray();
        while (right < charArray.length) {
            if (countArray[charArray[right]] > 0) { // 如果大于0说明是t中需要被抵消字符
                countLeft--;
            }
            countArray[charArray[right]]--;
            right++;

            // TODO. 一旦数量抵消完成, 则构成一个有效的Windows, 记录最值坐标
            while (countLeft == 0) {
                if (right - left < minLen) {
                    startIndex = left;
                    minLen = right - left;
                }
                // TODO. 将left坐标右移, 不断的缩小窗口大小
                if (countArray[charArray[left]] == 0) { // 如果为0则说明是被抵消到0的
                    countLeft++; // 从左侧窗口移除的字符，需要在窗口往后移动时再被抵消
                }
                countArray[charArray[left]]++; // 将字符的统计恢复+1
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "": new String(charArray, startIndex, minLen);
    }
}