package collections.tree.prefix_tree.questions;

import collections.tree.prefix_tree.node.TrieNodeWithWord;

import java.util.ArrayList;
import java.util.List;

// Word Search II
// Given an m x n board of characters and a list of strings words, return all words on the board
// Each word must be constructed from letters of sequentially adjacent cells
// The same letter cell may not be used more than once in a word
//
// board = [["o","a","b","n"],  words = ["oa","oaa"] words中要查找的单词都是唯一，可以构建HashKey
//          ["o","t","a","e"],  Output: ["oa","oaa"] 这里必须要判断到单词的完整长度，不能重复添加
//          ["a","h","k","r"],
//          ["a","f","l","v"]]
//
// board = [["o","a","a","n"],  words = ["oath","pea","eat","rain","hklf", "hf"]
//          ["e","t","a","e"],  注意取的单词构成了一个闭合的线路
//          ["i","h","k","r"],
//          ["i","f","l","v"]]
public class WordSearchII {

    // TODO. 利用单词数组构建Trie Tree搜索树
    // 以二维字符数组中某个字符为起点DFS展开遍历 + Backtracking回溯
    public List<String> findWords(char[][] board, String[] words) {
        List<String> results = new ArrayList<>();
        TrieNodeWithWord root = buildTrie(words);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, results);
            }
        }
        return results;
    }

    public TrieNodeWithWord buildTrie(String[] words) {
        TrieNodeWithWord root = new TrieNodeWithWord();
        for (String word : words) {

            // 从root节点位置将word单词构建到Trie树中
            TrieNodeWithWord p = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (p.children[index] == null) {
                    p.children[index] = new TrieNodeWithWord();
                }
                p = p.children[index];
            }

            // 在构建完成的叶子节点位置存储这个单词的完成形式
            p.word = word;
        }
        return root;
    }

    // 每一层递归中，Trie Tree树的结点会进入不同的层次，以此来对应(查找)指定的单词
    public void dfs(char[][] board, int i, int j, TrieNodeWithWord p, List<String> res) {
        char c = board[i][j];

        if (c == '#' || p.children[c - 'a'] == null) {
            // 已经读过的字符位置或者不在构建的Trie Tree结构中，则返回
            return;
        }

        p = p.children[c - 'a'];
        if (p.word != null) {
            // 只要Trie Tree的节点位置上的word属性值非空，则说明是words中的单词
            res.add(p.word);
            p.word = null;
        }

        board[i][j] = '#';
        if (i > 0) {
            dfs(board, i - 1, j, p, res);
        }
        if (j > 0) {
            dfs(board, i, j - 1, p, res);
        }
        if (i < board.length - 1) {
            dfs(board, i + 1, j, p, res);
        }
        if (j < board[0].length - 1) {
            dfs(board, i, j + 1, p, res);
        }
        board[i][j] = c;
    }
}
