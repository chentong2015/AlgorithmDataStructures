package asubstrings.common_substring;

// Longest Common Prefix
// Write a function to find the longest common prefix string amongst an array of strings.
// If there is no common prefix, return an empty string "".
//
// 1 <= strs.length <= 200
// 0 <= strs[i].length <= 200
// strs[i] consists of only lowercase English letters if it is non-empty.
public class LongestCommonPrefix {

    // TODO. 不要直接调用String类型的API，这会破坏算法的复杂度
    // strs = ["flower","flow","flight"] -> "fl"
    // strs = ["dog","racecar","car"] -> ""
    //
    // O(N) N is the length of all characters
    // O(1) 最差情况下只需要存储对一个字符串的长度
    public String longestCommonPrefix(String[] strs) {
        char[] commons = strs[0].toCharArray();
        int commonLength = commons.length;
        for (int i = 1; i < strs.length; i++) {
            commonLength = Math.min(commonLength, strs[i].length());
            for (int j = 0; j < commonLength; j++) {
                if (commons[j] != strs[i].charAt(j)) {
                    commonLength = j;
                }
            }
            if (commonLength == 0) {
                return "";
            }
        }

        StringBuilder result = new StringBuilder();
        for (int index = 0; index < commonLength; index++) {
            result.append(commons[index]);
        }
        return result.toString();
    }
}
