package string;

public class ImplIndexOf {

    // Implement strStr() / Java indexOf()
    // Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack
    // haystack = "hello", needle = "ll" -> index = 2
    public int strStr(String haystack, String needle) {
        // 测试理解：1. 在没有别的解法时，直接使用常规解法 O(n*m) O(1) !!
        if (needle == null || needle.isEmpty()) {
            return 0;
        }
        if (haystack == null || haystack.length() < needle.length()) {
            return -1;
        }
        for (int index = 0; index < haystack.length(); index++) {
            for (int j = 0; j < needle.length(); j++) {
                if (index + j == haystack.length()) return -1;
                if (haystack.charAt(index + j) != needle.charAt(j)) break;  // 在内部判断3种可能的情况
                if (j == needle.length() - 1) return index;
            }
        }
        return -1;
    }
}
