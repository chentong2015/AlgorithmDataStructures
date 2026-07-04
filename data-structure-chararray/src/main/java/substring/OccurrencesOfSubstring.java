package substring;

public class OccurrencesOfSubstring {

    // TODO: 使用StringBuilder类型API快速替换子字符串
    // Remove All Occurrences of a Substring
    // Given two strings s and part, perform the following operation on s
    // until all occurrences of the substring part are removed:
    // Find the leftmost occurrence of the substring part and remove it from s
    //
    // Input: s = "daabcbaabcbc", part = "abc"
    // Output: "dab"
    // Explanation: The following operations are done:
    // - s = "daabcbaabcbc", remove "abc" starting at index 2, so s = "dabaabcbc".
    // - s = "dabaabcbc", remove "abc" starting at index 4, so s = "dababc".
    // - s = "dababc", remove "abc" starting at index 3, so s = "dab".
    // Now s has no occurrences of "abc".
    //
    // O(N*N) 构建SB和每个字符逐一替换
    // O(N)   构建SB结果
    public String removeOccurrences(String s, String part) {
        StringBuilder sb = new StringBuilder(s);
        do {
            int i = sb.indexOf(part);
            if (i == -1) {
                break;
            }
            sb.replace(i, i + part.length(), "");
        } while (true);
        return sb.toString();
    }

    // Occurrences of substring in a string
    // 全部替换子字符串之后，通过字符长度的变化来判断替换次数
    public static int count(String str, String target) {
        int lenStr = str.length();
        int lenReplacedStr = str.replace(target, "").length();
        return (lenStr - lenReplacedStr) / target.length();
    }
}
