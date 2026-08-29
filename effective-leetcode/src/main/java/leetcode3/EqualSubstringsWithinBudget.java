package leetcode3;

// Get Equal Substrings Within Budget
// You are given two strings s and t of the same length and an integer maxCost.
// You want to change s to t
// Changing the ith character of s to ith character of t costs |s[i] - t[i]|
// is the absolute difference between the ASCII values of the characters
//
// Return the maximum length of a substring of s
// that can be changed to be the same as the corresponding substring of t
// with a cost less than or equal to maxCost.
//
// If there is no substring from s that
// can be changed to its corresponding substring from t, return 0.
//
// 1 <= s.length <= 10^5
// t.length == s.length
// 0 <= maxCost <= 10^6
// s and t consist of only lowercase English letters
public class EqualSubstringsWithinBudget {

    // TODO. 对每个Index坐标位置的距离执行滑动窗口算法
    // s = "abcd", t = "bcdf", maxCost = 3 -> 3
    //      1112
    //
    // s = "abcd", t = "cdef", maxCost = 3 -> 2
    //      2222
    //
    // s = "abcd", t = "acde", maxCost = 0 -> 1
    //      0111
    //
    // s = "abcd", t = "cdef", maxCost = 1 -> 0
    //      2222
    public int equalSubstring(String s, String t, int maxCost) {
        int result = 0;
        int totalCost = 0;
        int left = 0;
        for(int index = 0; index < s.length(); index++) {
            totalCost += Math.abs(s.charAt(index) - t.charAt(index));
            while (totalCost > maxCost) {
                totalCost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }
            // 如果left移动到index位置前面，说明无解
            if (index < left) {
                continue;
            }
            result = Math.max(result, index - left + 1);
        }
        return result;
    }
}
