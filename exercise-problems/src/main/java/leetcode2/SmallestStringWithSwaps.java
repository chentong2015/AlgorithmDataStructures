package leetcode2;

import java.util.List;

// Smallest String With Swaps
// You are given a string s and an array of pairs of indices in the string pairs
// where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
// You can swap the characters at any pair of indices in the given pairs any number of times.
// Return the lexicographically smallest string that s can be changed to after using the swaps
//
// 1 <= s.length <= 10^5
// 0 <= pairs.length <= 10^5
// 0 <= pairs[i][0], pairs[i][1] < s.length
// s only contains lower case English letters.
public class SmallestStringWithSwaps {

    // TODO. 不要考虑中间交换过程的数据，直接判断最终的结果
    // s = "dcab", pairs = [[0,3],[1,2]]
    // swap s[0] and s[3], s = "bcad"
    // swap s[1] and s[2], s = "bacd"
    //
    // s = "dcab", pairs = [[0,3],[1,2],[0,2]]
    // swap s[0] and s[3], s = "bcad"
    // swap s[0] and s[2], s = "acbd"
    // swap s[1] and s[2], s = "abcd"
    //
    // s = "cba", pairs = [[0,1],[1,2]]
    // swap s[0] and s[1], s = "bca"
    // swap s[1] and s[2], s = "bac"
    // swap s[0] and s[1], s = "abc"
    //
    // 如果交换的位置能够联通，则返回最小的重组字符串
    // 如果无法联通所有位置，则拼接所有最小字符串片段
    // 0 1 2 3
    // 0 1 2 3
    // 0 1 2
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {


    }
}
