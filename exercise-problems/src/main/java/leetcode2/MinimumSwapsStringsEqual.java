package leetcode2;

import java.util.ArrayList;
import java.util.List;

// Minimum Swaps to Make Strings Equal
// You are given two strings s1 and s2 of equal length
// consisting of letters "x" and "y" only.
//
// Your task is to make these two strings equal to each other.
// You can swap any two characters that belong to different strings,
// which means: swap s1[i] and s2[j].
//
// Return the minimum number of swaps required to make s1 and s2 equal,
// or return -1 if it is impossible to do so.
//
// 1 <= s1.length, s2.length <= 1000
// s1.length == s2.length
// s1, s2 only contain 'x' or 'y'.
public class MinimumSwapsStringsEqual {

    // TODO. 相等位置不考虑，只统计不同位置的特殊情况
    // ("xx", "yy") => 1 swap  优先应用这个交换策略
    // ("xy", "yx") => 2 swaps 再应用该策略
    //
    // s1 = "xx", s2 = "yy" -> 1
    // s1 = "yx", s2 = "yx".
    //
    // s1 = "xy", s2 = "yx" -> 2
    // s1 = "yy", s2 = "xx".
    // s1 = "xy", s2 = "xy".
    //
    // s1 = "xx", s2 = "xy" -> -1
    //
    public int minimumSwap(String s1, String s2) {
        List<Character> list1 = new ArrayList<>();
        List<Character> list2 = new ArrayList<>();
        for (int index = 0; index < s1.length(); index++) {
            if (s1.charAt(index) != s2.charAt(index)) {
                list1.add(s1.charAt(index));
                list2.add(s2.charAt(index));
            }
        }

        int result = 0;
        for (int index = 0; index < list1.size() - 1; index++) {
            if (list1.get(index) == 'X' && list1.get(index + 1) == 'X') {
                if (list2.get(index) == 'Y' && list2.get(index + 1) == 'Y') {
                    result++;
                }
            } else if (list1.get(index) == 'X' && list1.get(index + 1) == 'Y') {
                if (list2.get(index) == 'Y' && list2.get(index + 1) == 'Y') {
                    result++;
                }
            }
        }

        return -1;
    }
}
