package hash_map;

import java.util.HashMap;

// Isomorphic Strings
// All occurrences of a character must be replaced with another character
// while preserving the order of characters
// No two characters may map to the same character, but a character may map to itself
//
// 1 <= s.length <= 5 * 104
// t.length == s.length
// s and t consist of any valid ascii character.
public class IsomorphicStrings {

    // TODO. Map<Key,Value>存储字符之间映射关系
    // s = "egg", t = "add" -> true       e->a, g->d
    // s = "paper", t = "title" -> true
    // s = "paper", t = "titte" -> false  p->t, e->t
    //
    // O(n)
    // O(n)
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        for (int index = 0; index < s.length(); index++) {
            char c = s.charAt(index);
            char mapChar = t.charAt(index);
            if (map.containsKey(c)) {
                // 保证相同字符映射结果一致
                if (map.get(c) != mapChar) {
                    return false;
                }
            } else {
                // 新key键不能映射到前面已经映射过的字符
                if (map.containsValue(mapChar)) {
                    return false;
                }
                map.put(c, mapChar);
            }
        }
        return true;
    }
}
