package com.leetcode.basic_theory_introduction.tree.prefix_tree_trie.model;

public class TrieNodeWithWord {

    public String word;
    public TrieNodeWithWord[] children;

    public TrieNodeWithWord() {
        children = new TrieNodeWithWord[26];
    }
}