package templates.dp_program.palindromic;

import templates.dp_program.common_subsequence.LongestCommonSubsequence;

public class MinInsertionStringPalindrome {

    private final LongestCommonSubsequence lcsObject = new LongestCommonSubsequence();

    // TODO: 使用LCS比较原字符串和反转字符串有多少公共的char序列
    //  排除掉公共字符序列的数量，不同的字符数量则必须通过插入char来形成"回文效果"
    //
    //  直接利用LCS计算一个字符串和它的反转字符串之间的最长公共子序列的数量
    //  剩下的长度差值就是要形成Palindrome所需要插入的字符数量
    // Minimum Insertion Steps to Make a String Palindrome
    // In one step you can insert any character at any index of the string
    // Return the minimum number of steps to make s palindrome
    // s = "zzazz" -> 0
    // s = "mbadm" -> "mbdadbm" or "mdbabdm" -> 2
    //      mdabm
    // s = "leetcode" -> "leetcodocteel" -> 5 需要插入5个字符才能构成回文
    //      edocteel
    public int minInsertions(String str) {
        // 使用StringBuilder来完成字符串的反转
        String reverseStr = new StringBuilder(str).reverse().toString();

        int lcsLength = lcsObject.longestCommonSubsequence(str, reverseStr);
        return str.length() - lcsLength;
    }
}
