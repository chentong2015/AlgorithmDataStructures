package dp_programming.word_break;

import java.util.*;

// Word Break II
// Given a string s and a dictionary of strings wordDict,
// add spaces in s to construct a sentence where each word is a valid dictionary word.
//
// Return all such possible sentences in any order.
// The same word in the dictionary may be reused multiple times in the segmentation.
public class WordBreakII {

    // TODO. 使用二维DP数组记录遍历过程中的任意组合结果
    // "catsanddog", ["cat","cats","and","sand","dog"]
    //  0011001001
    // ["cat sand dog", "cats and dog"]
    //
    // "pineapplepenapple", ["apple","pen","applepen","pine","pineapple"]
    // ["pine apple pen apple", "pineapple pen apple", "pine applepen apple"]
    //
    // "aaaaaaaaaaaaaaaaab", ["a","aa","aaa","aaaa","aaaaa","aaaaaa"]
    // []
    public List<String> wordBreak(String str, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        HashMap<Integer, List<String>> map = new HashMap<>();
        // 初始化开头的空字符
        map.put(0, List.of(""));

        for (int index = 1; index <= str.length(); index++) {
            map.put(index, new ArrayList<>());
            for (int left = 0; left < index; left++) {
                String suffix = str.substring(left, index);
                if (set.contains(suffix)) {
                    // 用Left位置数据构建Index位置的组合数据
                    for (String s: map.get(left)) {
                        String subStr = s.isEmpty() ? suffix: s + " " + suffix;
                        map.get(index).add(subStr);
                    }
                }
            }
        }
        return map.get(str.length());
    }
}
