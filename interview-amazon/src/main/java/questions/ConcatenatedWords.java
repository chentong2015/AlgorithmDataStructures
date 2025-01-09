package questions;

import java.util.*;

// Concatenated Words
// Given an array of strings words (without duplicates),
// return all the concatenated words in the given list of words.
//
// A concatenated word is defined as a string
// that is composed entirely of at least two shorter words (not necessarily distinct)
// in the given array.
//
// 重复利用题目的限制条件: Unique唯一性对应HashSet<E>集合
// words[i] consists of only lowercase English letters.
// All the strings of words are unique.
//
// 返回的结果完全可以由给定字符串数组中的字符串"完整地"组成
// ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
// output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
public class ConcatenatedWords {

    private Set<String> stringSet;
    private List<String> results;

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        stringSet = new HashSet<>(Arrays.asList(words));
        results = new ArrayList<>();
        for (String word: words) {
            // 排除自身字符串的Key抵消判断
            stringSet.remove(word);
            recursion(word, word.toCharArray(), 0);
            stringSet.add(word);
        }
        return results;
    }

    // TODO. Recursion: 递归匹配的Key, 截取子字符串后再匹配
    // "catsdogcats" 第一种递归
    //     sdogcats -> KO
    //
    // "catsdogcats" 第二种递归
    //      dogcats
    //         cats -> OK
    private void recursion(String word, char[] chars, int start) {
        if (start == chars.length) {
            // start移动到字符串结尾，说明匹配完成
            // 1. 不能重复添加
            if (!results.contains(word)) {
                results.add(word);
            }
            // 2. 一旦单词判断正确，则停止后续的递归
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int index = start; index < chars.length; index++) {
            stringBuilder.append(chars[index]);
            if (stringSet.contains(stringBuilder.toString())) {
                recursion(word, chars, index + 1);
            }
        }
    }
}

