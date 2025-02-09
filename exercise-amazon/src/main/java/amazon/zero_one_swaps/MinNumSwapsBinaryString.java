package amazon.zero_one_swaps;

// Minimum Number of Swaps to Make the Binary String Alternating
// Given a binary string s, return the minimum number of character swaps to make it alternating,
// or -1 if it is impossible.
//
// The string is called alternating if no two adjacent characters are equal.
// For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
//
// Any two characters may be swapped, even if they are not adjacent.
// 1 <= s.length <= 1000
// s[i] is either '0' or '1'.
public class MinNumSwapsBinaryString {

    // TODO. 如果中间的推理计算(交换的过程)太复杂，则直接关注输入和输出的不同
    //  如果有结果，那么必然是两种结果中的一种，其中一种必定是最优解
    //  直接根据最优解来比较不同，至少要经过这些“不同位置”的步数才能成功
    // "111000" -> "101010" -> 1
    // "010"    -> 0
    // "1110"   -> -1
    // "100"    -> 1
    //
    // 1 1 1 0 0 0 input
    // 1 0 1 0 1 0 output1 => 1 num
    // 0 1 0 1 0 1 output2 => 2 num
    //
    // Time: O(n+n+n)=O(n) do loop 3 times
    // Space: O(1) no extra space
    public int minSwaps(String s) {
        int countZero = 0;
        int countOne = 0;
        for (char c: s.toCharArray()) {
            if (c == '0') {
                countZero++;
            } else {
                countOne++;
            }
        }
        // The difference of num 1 and num 0 is < 2
        if (countZero > countOne + 1 || countOne > countZero + 1) {
            return -1; // not possible
        }

        int countDiff1 = countDiff('0', s);
        int countDiff2 = countDiff('1', s);
        return Math.min(countDiff1, countDiff2);
    }

    // The count must be Even number 交换的数目必须是偶数
    private int countDiff(char indexChar, String s) {
        int count = 0;
        for (char c: s.toCharArray()) {
            if (c != indexChar) {
                count++;
            }
            if (indexChar == '0') {
                indexChar = '1';
            } else {
                indexChar = '0';
            }
        }
        // 统计出来如果交换的次数是奇数，则无效
        if (count % 2 == 1) {
            return Integer.MAX_VALUE;
        }
        return count / 2;
    }
}
