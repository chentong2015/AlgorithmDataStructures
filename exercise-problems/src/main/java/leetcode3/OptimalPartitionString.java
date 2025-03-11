package leetcode3;

import java.util.Arrays;

// Optimal Partition of String
// Given a string s, partition the string into one or more substrings
// such that the characters in each substring are unique
// That is, no letter appears in a single substring more than once.
//
// Return the minimum number of substrings in such a partition.
// Note that each character should belong to exactly one substring in a partition.
//
// 1 <= s.length <= 105
// s consists of only English lowercase letters.
//
// 至少需要划分多少个子字符串才能满足条件
// abacaba -> 4 is the minimum number of substrings needed
// ("a","ba","cab","a") and ("ab","a","ca","ba")
//
// ssssss -> 6 min number
// ("s","s","s","s","s","s").
public class OptimalPartitionString {

    // TODO. 贪心算法: 每次都选择可能的最大子字符串，直到无法选择
    //
    // O(26N) 快速判断某个字符是否之前存在过
    // O(26)
    public int partitionString(String s) {
        int min = 0;
        int[] counts = new int[26];
        for (char c: s.toCharArray()) {
            if (counts[c - 'a'] != 0) {
                min++;
                Arrays.fill(counts, 0);
            }
            // 重新开始统计唯一的字符
            counts[c - 'a']++;
        }

        // 累计最后一个没有重复的子字符串
        return min + 1;
     }
}
