package astrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LearnString2 {

    // First Unique Character in a String
    // Return the first non-repeating character in it and return its index
    // Input: "loveleetcode" 都是小写字符 ->  2 返回其中第一个非重复字符的位置
    public static int firstUniqueChar(String s) {
        // 正确理解: 1. 利用HashMap的put方法来统计指定的Key出现的次数               O(1)
        //          2. 使用定长字符数组int[26], 计算s.charAt(i)-'a', 统计字符次数 O(1)
        Map<Character, Integer> maps = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char keyChar = s.charAt(i);
            int oldCount = maps.getOrDefault(keyChar, 0);
            maps.put(keyChar, oldCount + 1);
        }
        for (int j = 0; j < s.length(); j++) {
            // 找到第一个非重复字符，即统计数为1的第一个字符，直接返回
            if (maps.get(s.charAt(j)) == 1) {
                return j;
            }
        }
        return -1;
    }

    // TODO: 利用字符串中的字符来做运算 s.charAt(i) - 'a'，将字符转换成int有效数值 !!
    // Valid Anagram 字符中包含同样多的字符
    // Sorting both strings will result in two identical strings
    public static boolean isAnagram(String s, String t) {
        // 正确理解: 1. 使用定长字符数组int[26], 统计每个出现字符的数量
        //             O(n)  O(1) 定长的数组，额外开辟出来的空间是一定的
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }

        // 正确理解: 2. String -> CharArray 通过调用Array的静态方法来实现 !!
        //             Time complexity: O(nlogn)  Space complexity: O(1)
        if (s.length() != t.length()) {
            return false;
        }
        char[] sChars = s.toCharArray(); // 将string复制一份出来, 时间复制度O(n), 和语言的实现有关，可将提供的参数改成char[]取消复杂度
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);             // 排序方法的时间复杂度 O(nlog(n)) > O(n)
        Arrays.sort(tChars);
        return Arrays.equals(sChars, tChars);
    }
}
