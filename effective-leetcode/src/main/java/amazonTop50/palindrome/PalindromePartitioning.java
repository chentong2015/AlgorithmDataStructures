package amazonTop50.palindrome;

import java.util.ArrayList;
import java.util.List;

// Palindrome Partitioning
// Given a string s, partition s such that every substring of the partition is a palindrome.
// Return all possible palindrome partitioning of s.
//
// 1 <= s.length <= 16
// s contains only lowercase English letters.
public class PalindromePartitioning {

    // TODO. 递归推导全部的切分位置
    // s = "aab" -> [["a","a","b"],["aa","b"]]
    // s = "a" -> [["a"]]
    //
    // 复杂度取决于字符串有多少种结果
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        recursion(result, new ArrayList<>(), s, 0);
        return result;
    }

    private void recursion(List<List<String>> result, List<String> currentPartitions, String str, int index) {
        if (index == str.length()) {
            result.add(currentPartitions);
            return;
        }
        // 单个字符是否符合要求
        List<String> copyList = new ArrayList<>(currentPartitions);
        copyList.add(String.valueOf(str.charAt(index)));
        recursion(result, copyList, str, index+1);

        // 找到后面所有符合的位置
        int right = index + 1;
        while (right < str.length()) {
            if (isPalindrome(str, index, right)) {
                List<String> tempList = new ArrayList<>(currentPartitions);
                tempList.add(str.substring(index, right+1));
                recursion(result, tempList, str, right+1);
            }
            right++;
        }
        currentPartitions.clear();
    }

     boolean isPalindrome(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
