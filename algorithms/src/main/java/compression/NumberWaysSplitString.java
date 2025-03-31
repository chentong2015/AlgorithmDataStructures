package compression;

// Number of Ways to Split a String
// Given a binary string s, you can split s into 3 non-empty strings s1, s2, and s3
// where s1 + s2 + s3 = s.
//
// Return the number of ways s can be split such that
// the number of ones is the same in s1, s2, and s3
// Since the answer may be too large, return it modulo 10^9 + 7.
//
// 3 <= s.length <= 10^5
// s[i] is either '0' or '1'.
public class NumberWaysSplitString {

    // TODO. 本质上是统计中间两段0数目的组合结果
    //  1/3  + 0? +  1/3  + 0? + 1/3 压缩三分之一数目
    //
    // "10101" -> 4
    // "1|010|1"
    // "1|01|01"
    // "10|10|1"
    // "10|1|01"
    //
    // "1001" -> 0
    //
    // "0000" -> 2 + 1 = 3
    // "0|0|00"
    // "0|00|0"
    // "00|0|0"
    //
    // 1 0 1 0 1
    //   2 * 2 = 4
    //
    // 1001 000 101 00 110
    //      4    *  3 = 12
    //
    // O(N)
    // O(1)
    public int numWays(String s) {
        int countOnes = 0;
        for (char c: s.toCharArray()) {
            if (c == '1') {
                countOnes++;
            }
        }
        // 无法平均分配的情况
        if (countOnes % 3 != 0) {
            return 0;
        }
        // 全部是0的情况: 直接计算两个间隔线位置的可能
        if (countOnes == 0) {
            int length = s.length() - 2;
            long result = (long) length * (length + 1) / 2;
            return (int) (result % 1_000_000_007);
        }

        long countLefZero = 0;
        long countRightZero = 0;
        int count = 0;
        int index = 0;
        while (index < s.length()) {
            if (s.charAt(index) == '1') {
                count++;
                if (count == countOnes / 3) {
                    int right = index + 1;
                    while (right < s.length() && s.charAt(right) == '0') {
                        countLefZero++;
                        right++;
                    }
                    index = right;
                    continue;
                } else if (count == countOnes / 3 * 2) {
                    int right = index + 1;
                    while (right < s.length() && s.charAt(right) == '0') {
                        countRightZero++;
                        right++;
                    }
                    break; // 已经获取两个要统计的数目, 结束后面循环
                }
            }
            index++;
        }

        // 计算间隔位置的组合可能情况
        long result = (countLefZero + 1) * (countRightZero + 1);
        return (int) (result % 1_000_000_007);
    }
}
