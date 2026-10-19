package live_coding.tencent;

// 判断一个字符串的子字符串是否包含另一个字符串"字符的所有排列组合"
// Permutation in String
// Given two strings s1 and s2,
// return true if s2 contains a permutation of s1, or false otherwise.
// In other words, return true if one of s1's permutations is the substring of s2.
//
// 1 <= s1.length, s2.length <= 10^4
// s1 and s2 consist of lowercase English letters.
public class PermutationInString {

    // TODO. 问题本质是字符的异位构词
    // s1 = "ab", s2 = "eidbaooo" -> true
    // s1 = "ab", s2 = "eidboaoo" -> false
    //
    // O(M + 26*N) 读取s2字符便会循环有限数组
    // O(1)        有限的额外存储空间
    public boolean checkInclusion(String s1, String s2) {
        char[] charArray1 = new char[26];
        for (char c: s1.toCharArray()) {
            charArray1[c - 'a']++;
        }
        char[] charArray2 = new char[26];
        for (int index = 0; index < Math.min(s1.length(), s2.length()); index++) {
            charArray2[s2.charAt(index) - 'a']++;
        }

        // Check the beginning
        if (isValidPermutation(charArray1, charArray2)) {
            return true;
        }

        // Sliding Windows for checking each substring
        int index = s1.length();
        while (index < s2.length()) {
            charArray2[s2.charAt(index - s1.length()) - 'a']--;
            charArray2[s2.charAt(index) - 'a']++;
            if (isValidPermutation(charArray1, charArray2)) {
                return true;
            }
            index++;
        }
        return false;
    }

    // 比较的时候charArray1的字符排列组合固定
    private boolean isValidPermutation( char[] charArray1,  char[] charArray2) {
        for (int index = 0; index < 26; index++) {
            if (charArray1[index] != charArray2[index]) {
                return false;
            }
        }
        return true;
    }
}
