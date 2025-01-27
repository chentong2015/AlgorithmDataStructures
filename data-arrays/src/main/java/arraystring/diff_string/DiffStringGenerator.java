package arraystring.diff_string;

public class DiffStringGenerator {

    // TODO. 判断长字符串是否包含短字符串的所有字符(字符不重复使用)
    // Ransom Note
    // Given two strings ransomNote and magazine,
    // return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
    // Each letter in magazine can only be used once in ransomNote.
    //
    // 1 <= ransomNote.length, magazine.length <= 105
    // ransomNote and magazine consist of lowercase English letters.
    //
    // O(n + m?) 时间复杂度主要取决于magazine的字符长度
    // O(1)      只开辟了常量的内存空间
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        // TODO. 优先统计长字符串的所有字符，这里O(n)的遍历比不可少
        int[] counts = new int[26];
        for (char c : magazine.toCharArray()) {
            counts[c - 'a']++;
        }

        // 在遍历短字符串的过程中可以判断是否返回，无需遍历到最后
        for (char c : ransomNote.toCharArray()) {
            if (counts[c - 'a'] == 0) {
                return false;
            }
            counts[c - 'a']--;
        }
        return true;
    }
}
