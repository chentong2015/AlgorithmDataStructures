package strings;

// Given a string s
// find the first non-repeating character in it and return its index.
// If it does not exist, return -1
//
// 1 <= s.length <= 105
// s consists of only lowercase English letters.
public class FirstUniqueCharacter {

    // TODO. 充分利用26个英文字符来创建数组, 使用HashSet<>和HashMap<>会造成空间浪费
    // s = "leetcode" -> 0
    // s = "loveleetcode" -> 2
    // s = "aabb" -> -1
    //
    // O(N + N)
    // O(N)
    public int firstUniqChar(String s) {
        int[] counts = new int[26];
        for (char c: s.toCharArray()) {
            counts[c - 'a'] += 1;
        }
        for (int index = 0; index < s.length(); index++) {
            if ( counts[s.charAt(index) - 'a'] == 1) {
                return index;
            }
        }
        return -1;
    }
}
