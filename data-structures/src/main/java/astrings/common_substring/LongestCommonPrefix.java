package astrings.common_substring;

// Longest Common Prefix
// Write a function to find the longest common prefix string amongst an array of strings.
// If there is no common prefix, return an empty string "".
//
// 1 <= strs.length <= 200
// 0 <= strs[i].length <= 200
// strs[i] consists of only lowercase English letters if it is non-empty.
public class LongestCommonPrefix {

    // strs = ["flower","flow","flight"] -> "fl"
    // strs = ["dog","racecar","car"] -> ""
    public String longestCommonPrefix(String[] strs) {

        return "";
    }







    // TODO: Longest Common Prefix
    // Write a function to find the longest common prefix string amongst an array of strings
    // If there is no common prefix, return an empty string ""
    // strs = ["flower","flow","flight"] -> "fl"
    public String longestCommonPrefix11(String[] strs) {
        // 测试理解：1. 依次提取单词的每一个前缀字符串，判断后面的所有字符串是否具有相同的前缀  ==> 实际比较的次数过多，遍历一遍就必须出结果 !!
        //            O(m*n) m为公共前缀的长度 O(1)
        return null;
    }

    // 正确理解：
    // 1. 水平扫描：横向遍历，依次比较两个字符串的公共前缀，然后再和第三个进行比较，依次到最后    O(S) S为所有字符的长度和 O(1)
    // 2. 垂直扫描：纵向遍历字符串的每个位置上的字符(same character index of the strings) O(S) O(1)
    // 3. 高阶解法：使用Prefix Tree数据结构
    public String longestCommonPrefixHorizontalScanning(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0]; // 假设第一个字符串为所有字符串的公共前缀
        for (int i = 1; i < strs.length; i++) {
            // String.indexOf() 找字符串中的前缀字符串的index位置，第一次出现的位置必须从头开始
            // While循环结束之后再拿公共前缀去和下一个字符串比较，提取公共前缀
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    public String longestCommonPrefixVerticalScanning(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 遍历第一个位置的字符串的所有字符 ==> 避免重复检查前面已经比较过的index位置
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            // 判断后面的字符串在指定的index位置是否是指定的字符c
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) // .charAt(i) 每个指定的位置上只会读取到一次
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }
}
