package leetcode4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Find Unique Binary String
// Given an array of strings nums containing n unique binary strings each of length n
// return a binary string of length n that does not appear in nums.
// If there are multiple answers, you may return any of them.
//
// 由于给出的二进制字符串比可能的组合情况少，因此一定有结果
// 1 <= n <= 16
// nums.length == n
// nums[i].length == n
// nums[i] is either '0' or '1'.
// All the strings of nums are unique.
public class FindUniqueBinaryString {

    // TODO. n长度的二进制字符串可以组成2^n种结果
    // nums = ["01","10"] -> "00" or "11"
    // nums = ["00","01"] -> "10" or "11"
    // nums = ["111","011","001"] -> "101" or "000", "010", "100", and "110"
    //
    // O(1)       常数级别的时间和空间复杂度
    // O(16 * 17) 最多需要构建17次目标字符串
    public String findDifferentBinaryString(String[] nums) {
        Set<String> binaryStringSet = new HashSet<>(Arrays.asList(nums));

        char[] chars = new char[nums.length];
        for (int index = 0; index < nums.length; index++) {
            chars[index] = '0';
        }

        // 直接使用char字符数组来构建字符串
        int index = 0;
        String uniqueString = new String(chars);
        while (binaryStringSet.contains(uniqueString)) {
            chars[index] = '1';
            uniqueString = new String(chars);
            index++;
        }
        return uniqueString;
    }
}
