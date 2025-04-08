package strings.anagram;

// Valid Anagram
// Given two strings s and t,
// return true if t is an anagram of s, and false otherwise.
//
// 1 <= s.length, t.length <= 5 * 10^4
// s and t consist of lowercase English letters.
public class AnagramValid {

    // TODO. 直接使用字符统计相互消除
    // s = "anagram", t = "nagaram" -> true
    // s = "rat", t = "car" -> false
    //
    // O(N)
    // O(1)
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int i=0; i < 26; i++) {
            if (counter[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
