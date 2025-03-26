package leetcode4;

// Number of Substrings With Only 1s
// Given a binary string s, return the number of substrings with all characters 1's.
// Since the answer may be too large, return it modulo 109 + 7.
//
// 1 <= s.length <= 10^5
// s[i] is either '0' or '1'
public class NumberSubstringsWith1s {

    // TODO. 统计连续1的数目，然后使用数学公式计算
    // "0110111" -> 9
    // "1" -> 5 times.
    // "11" -> 3 times.
    // "111" -> 1 time.
    //
    // "101" -> 2
    //
    //
    // 1 -> 1
    // 11 -> 2+1=3
    // 111 -> 3+2+1=6
    // 1111 -> 4+3+2+1=10
    // 11111 -> 5+4+3+2+1=15
    // 111111 -> 6+5+4+3+2+1=21
    //
    // O(N)
    // O(1)
    public int numSub(String s) {
        long result = 0;
        int index = 0;
        while (index < s.length()) {
            if (s.charAt(index) == '1') {
                int length = 1;
                int right = index + 1;
                while (right < s.length() && s.charAt(right) == '1') {
                    length++;
                    right++;
                }
                // 公式统计有多少种子字符串的可能
                result += (long) length * (length + 1) / 2;
                index = right;
            } else {
                index++;
            }
        }
        int module = 1000000007;
        return (int) (result % module);
    }
}
