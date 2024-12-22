package amazon.question;

import java.util.*;

// TODO. 相邻字符不相等: 本质是不同字符之间抵消特性, 和字符的顺序无关
// Reorganize String
// Given a string s, rearrange the characters of s
// so that any two adjacent characters are not the same.
// Return any possible rearrangement of s or return "" if not possible.
//
// "aab"  -> "aba" 抵消后的排布结果
// "aaab" -> ""    相邻的字符抵消后，最后剩余的两个字符必然重叠
// "baa"  -> "aba" 原始字符串的第一个字符并不一定是结果的第一个字符
//
// 1 <= s.length <= 500
// s consists of lowercase English letters.
public class ReorganizeString {

    // TODO. 必须从统计较多的字符先开始抵消
    //
    // v v v l o -> vlvov
    // [0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,3,0,0,0,0]
    public static String reorganizeString(String s) {
        int[] counts = new int[26];
        for (char c: s.toCharArray()) {
            counts[c - 'a']++;
        }
        Arrays.sort(counts);

        int index = 25;
        StringBuilder stringBuilder = new StringBuilder();
        while (index >= 0) {
            int currentCount = counts[index];
            if (currentCount > 0) {
                for (int j = index - 1; j >= 0; j--) {
                    int nextCount = counts[j];
                    while (currentCount > 0 && nextCount > 0) {
                        stringBuilder.append((char) ('a' + index));
                        stringBuilder.append((char) ('a' + j));
                        currentCount--;
                        nextCount--;
                    }
                }

                // 如果后续的字符无法抵消当前的字符数量，则返回空字符串
                if (currentCount == 1) {
                    stringBuilder.append((char) ('a' + index));
                    break;
                } else if (currentCount > 1){
                    return "";
                }
            }
            index++;
        }
        return stringBuilder.toString();
    }
}
