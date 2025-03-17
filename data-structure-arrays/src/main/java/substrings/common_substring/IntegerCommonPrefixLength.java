package substrings.common_substring;

import java.util.HashSet;
import java.util.Set;

// Find the Length of the Longest Common Prefix
// You are given two arrays with positive integers arr1 and arr2.
// A prefix of a positive integer is an integer formed by one or more of its digits
// starting from its leftmost digit.
// For example, 123 is a prefix of the integer 12345, while 234 is not.
//
// A common prefix of two integers a and b is an integer c,
// such that c is a prefix of both a and b.
// For example, 5655359 and 56554 have common prefixes 565
//
// You need to find the length of the longest common prefix between all pairs of integers (x, y)
// Return the length of the longest common prefix among all pairs.
// If no common prefix exists among them, return 0.
//
// 1 <= arr1.length, arr2.length <= 5 * 10^4
// 1 <= arr1[i], arr2[i] <= 10^8
public class IntegerCommonPrefixLength {

    // TODO. 避免多次比较相同的整数，从大数值往小数组开始比较
    // arr1 = [1,10,100], arr2 = [1000] -> 3
    // - The longest common prefix of (1, 1000) is 1.
    // - The longest common prefix of (10, 1000) is 10.
    // - The longest common prefix of (100, 1000) is 100.
    //
    // arr1 = [1,2,3], arr2 = [4,4,4] -> 0
    // arr1 = [10], arr2 = [17,11] -> 1
    //
    // O(N+M)
    // O(N+M)
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        // HashSet存储所有可能的前缀情况
        Set<Integer> set1 = new HashSet<>();
        for (int int1: arr1) {
            while (int1 >= 10) {
                set1.add(int1);
                int1 /= 10;
            }
            set1.add(int1);
        }

        // 遍历arr2数组中数据，判断所需要的前缀是否存在
        int commonPrefix = 0;
        for (int int2: arr2) {
            if (commonPrefix > int2) {
                continue;
            }
            while (int2 >= 10) {
                if (set1.contains(int2)) {
                    commonPrefix = Math.max(commonPrefix, int2);
                    break;
                }
                int2 /= 10;
            }
            if (set1.contains(int2)) {
                commonPrefix = Math.max(commonPrefix, int2);
            }
        }

        // 通过最大公共值的位置/长度
        if (commonPrefix == 0) {
            return 0;
        }
        int length = 0;
        while (commonPrefix >= 10) {
            commonPrefix /= 10;
            length++;
        }
        return length + 1;
    }
}
