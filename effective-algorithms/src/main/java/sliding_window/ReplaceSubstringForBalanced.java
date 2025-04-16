package sliding_window;

import java.util.HashMap;

// Replace the Substring for Balanced String
// You are given a string s of length n
// containing only four kinds of characters: 'Q', 'W', 'E', and 'R'.
// A string is said to be balanced if each of its characters appears n / 4 times
// where n is the length of the string.
//
// Return the minimum length of the substring that can be replaced
// with any other string of the same length to make s balanced.
// If s is already balanced, return 0.
//
// n == s.length
// 4 <= n <= 10^5
// n is a multiple of 4.
// s contains only 'Q', 'W', 'E', and 'R'.
public class ReplaceSubstringForBalanced {

    // TODO. 替换最短的子字符串片段，不是统计替换的字符数目
    // "QWER" -> 0
    // "QQWE" -> "RQWE" (or "QRWE") -> 1
    // "QQQW" -> "ERQW" -> 2
    //
    // "WWEQERQWQWWRWWERQWEQ"
    // Q  R  E  W
    // 5  3  4  8
    // 5  5  5  5
    //    +2 +1 -3
    //
    // O(N + N)
    // O(1)
    public int balancedString(String s) {
        HashMap<Character, Integer> mapCount = new HashMap<>();
        for (char c: s.toCharArray()) {
            int baseCount = mapCount.getOrDefault(c, 0);
            mapCount.put(c, baseCount + 1);
        }

        // 'Q', 'W', 'E', and 'R'
        int[] counts = new int[4];
        int target = s.length() / 4;
        boolean hasDiff = false;
        for (char key: mapCount.keySet()) {
            if (mapCount.get(key) > target) {
                hasDiff = true;
                int index = getIndex(key);
                counts[index] = mapCount.get(key) - target;
            }
        }
        // 排除掉没有变化的特殊情况
        if (!hasDiff) {
            return 0;
        }

        // TODO. 使用滑动窗口统计满足特定数目的字符
        int result = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int[] temps = new int[4];
        while (right < s.length()) {
            int index = getIndex(s.charAt(right));
            temps[index]++;
            while (isTempEnough(temps, counts)) {
                result = Math.min(result, right - left + 1);

                index = getIndex(s.charAt(left));
                temps[index]--;
                left++;
            }
            right++;
        }
        return result;
    }

    private int getIndex(char c) {
        if (c == 'Q') {
            return 0;
        } else if (c == 'W') {
            return 1;
        } else if (c == 'E') {
            return 2;
        } else {
            return 3;
        }
    }

    private boolean isTempEnough(int[] temp, int[] counts) {
        for (int index = 0; index < 4; index++) {
            if (temp[index] < counts[index]) {
                return false;
            }
        }
        return true;
    }
}
