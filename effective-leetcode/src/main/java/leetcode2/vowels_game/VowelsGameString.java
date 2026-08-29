package leetcode2.vowels_game;

// Vowels Game in a String
//
// Alice  has to remove any non-empty substring from s that contains an odd number of vowels. 奇数
// Bob has to remove any non-empty substring from s that contains an even number of vowels. 偶数
// We assume that both Alice and Bob play optimally.
// 每次都选择最长的子字符串来移除，直到无法移除为止则分出胜负
//
// 1 <= s.length <= 105
// s consists only of lowercase English letters.
public class VowelsGameString {

    // TODO. 题目的本质和移动的子字符串长度无关
    //  只与元音字符的个数有关，只要有元音存在，则一定Alice赢
    //
    // 0 -> B
    // 1 -> A
    // 2 -> A
    // 3 -> A
    // 4 -> 3 + 1 -> A 根据奇偶查分的原理，偶数=奇数+1
    // 5 -> A
    // 6 -> 5 + 1 -> A
    // 7 -> A
    public boolean doesAliceWin(String s) {
        for (char c: s.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                return true;
            }
        }
        return false;
    }
}
