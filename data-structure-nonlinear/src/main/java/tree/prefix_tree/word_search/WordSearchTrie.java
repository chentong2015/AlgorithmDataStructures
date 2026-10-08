package tree.prefix_tree.word_search;

import tree.prefix_tree.TrieNodeWithWord;

import java.util.ArrayList;
import java.util.List;

// Word Search II
// Given an m x n grid of characters board and a string word,
// return true if word exists in the grid.
//
// board[i][j] is a lowercase English letter.
// 1 <= words.length <= 3 * 104
// 1 <= words[i].length <= 10
// words[i] consists of lowercase English letters.
// All the strings of words are unique.
public class WordSearchTrie {

    // TODO. Trie Tree 金典案例: 将要搜索的单词构建成前缀树
    //  DFS字符数组的所有字符串, 判断是否能形成前缀数中的目标单词, 通过字符串Prefix前缀快速比较
    //
    // ["o","a","a","n"],  words = ["oath","pea","eat","rain"]
    // ["e","t","a","e"],  Output: ["eat","oath"]
    // ["i","h","k","r"],
    // ["i","f","l","v"]]
    //
    // O(W*L + N*M*3^L)  运行时可能远优化与理论的最差复杂度
    // O(W*L + L)        W单词数量, L单词最长字符

    public List<String> findWords(char[][] board, String[] words) {
        TrieNodeWithWord root = buildTrie(words);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 以每个node启动DFS所有能形成的字符
                dfs(board, i, j, root, result);
            }
        }
        return result;
    }

    // 把所有要找的单词构建成Prefix Tree
    public TrieNodeWithWord buildTrie(String[] words) {
        TrieNodeWithWord root = new TrieNodeWithWord();
        for (String word : words) {
            TrieNodeWithWord trieNode = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (trieNode.children[index] == null) {
                    trieNode.children[index] = new TrieNodeWithWord();
                }
                trieNode = trieNode.children[index];
            }
            trieNode.word = word; // 标记结尾完整单词
        }
        return root;
    }

    // TODO. Trie最核心的价值就是把大量不存在的prefix在搜索早期剪掉
    public void dfs(char[][] board, int i, int j, TrieNodeWithWord trieNode, List<String> result) {
        char c = board[i][j];
        if (c == '#' || trieNode.children[c - 'a'] == null) {
            return;
        }

        trieNode = trieNode.children[c - 'a'];
        if (trieNode.word != null) { // 判断是否找到目标单词
            result.add(trieNode.word);
            trieNode.word = null;    // 记录后避免重复查找
        }

        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j, trieNode, result);
        if (j > 0) dfs(board, i, j - 1, trieNode, result);
        if (i < board.length - 1) dfs(board, i + 1, j, trieNode, result);
        if (j < board[0].length - 1) dfs(board, i, j + 1, trieNode, result);
        board[i][j] = c; // Backtracking
    }
}
