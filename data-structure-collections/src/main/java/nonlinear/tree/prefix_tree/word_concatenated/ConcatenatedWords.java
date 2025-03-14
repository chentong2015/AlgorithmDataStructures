package nonlinear.tree.prefix_tree.word_concatenated;

import java.util.*;

// Concatenated Words
// Given an array of strings words (without duplicates),
// return all the concatenated words in the given list of words.
//
// A concatenated word is defined as a string that is composed entirely
// of at least two shorter words (not necessarily distinct) in the given array.
//
// words[i] consists of only lowercase English letters.
// All the strings of words are unique.
//
// ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
// ["catsdogcats","dogcatsdog","ratcatdogcat"]
//
// 将所有的字符串进行排序，有利于从短字符向长字符构建Trie树结构
// - 时间复杂度最佳
// - 空间复杂度最差
public class ConcatenatedWords {

    private Trie trie = new Trie();

    public List<String> findAllConcatenatedWords(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        List<String> ans = new ArrayList<>();
        for (String w : words) {
            if (dfs(w)) {
                ans.add(w);
            } else {
                trie.insert(w);
            }
        }
        return ans;
    }

    // TODO. 递归直到Trie树的End结点，截取后续的子字符串再递归
    // 直到递归的substring子字符串为空，则组合成功
    // 如果递归过程中有返回false，则整个组合失败(无解)
    private boolean dfs(String w) {
        if (w.isEmpty()) {
            return true;
        }
        Trie node = trie;
        for (int i = 0; i < w.length(); ++i) {
            int idx = w.charAt(i) - 'a';
            if (node.children[idx] == null) {
                return false;
            }
            node = node.children[idx];
            if (node.isEnd && dfs(w.substring(i + 1))) {
                return true;
            }
        }
        return false;
    }

    static class Trie {
        boolean isEnd;
        Trie[] children = new Trie[26];

        void insert(String w) {
            Trie node = this;
            for (char c : w.toCharArray()) {
                c -= 'a';
                if (node.children[c] == null) {
                    node.children[c] = new Trie();
                }
                node = node.children[c];
            }
            node.isEnd = true;
        }
    }
}

