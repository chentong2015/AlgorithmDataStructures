package nonlinear.tree.prefix_tree.word_autocomplete;

import java.util.ArrayList;
import java.util.List;

// Search Suggestions System
// You are given an array of strings products and a string searchWord.
//
// Design a system that suggests at most three product names
// from products after each character of searchWord is typed.
// Suggested products should have common prefix with searchWord.
//
// If there are more than three products with a common prefix
// return the three lexicographically minimums products.
//
// Return a list of lists of the suggested products after each character of searchWord is typed.
public class PrefixTreeSearchComplete {

    // TODO. 使用前缀树实现搜索自动补全的系统, 实时推荐可能的结果
    // products = ["mobile","mouse","moneypot","monitor","mousepad"],
    // m -> ["mobile","moneypot","monitor"]
    // mo -> ["mobile","moneypot","monitor"]
    // mou -> ["mouse","mousepad"]
    // mous -> ["mouse","mousepad"]
    // mouse -> ["mouse","mousepad"]
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        TrieNode root = new TrieNode();
        for (String product : products) {
            buildTrieTree(root, product);
        }

        List<List<String>> listSuggestions = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c: searchWord.toCharArray()) {
            stringBuilder.append(c);
            listSuggestions.add(searchSuggestions(root, stringBuilder.toString()));
        }
        return listSuggestions;
    }

    public void buildTrieTree(TrieNode root, String product) {
        TrieNode pointer = root;
        for (char c : product.toCharArray()) {
            if (!pointer.containsChild(c)) {
                TrieNode newChild = new TrieNode();
                pointer.put(c, newChild);
            }
            pointer = pointer.getChild(c);
        }
        pointer.setWord(product);
    }

    public List<String> searchSuggestions(TrieNode root, String prefix) {
        TrieNode pointer = root;
        for (char c: prefix.toCharArray()) {
            if (pointer.containsChild(c)) {
                pointer = pointer.getChild(c);
            } else {
                return new ArrayList<>();
            }
        }

        List<String> suggestions = new ArrayList<>();
        collectChildren(pointer, suggestions);
        if (suggestions.size() < 4) {
            return suggestions;
        }
        return List.of(suggestions.get(0), suggestions.get(1), suggestions.get(2));
    }

    // TODO. 递归搜索节点下所有可能的单词: 收集所有的单词，单词自动从小到大排序
    private void collectChildren(TrieNode pointer, List<String> suggestions) {
        if (pointer.getWord() != null) {
            suggestions.add(pointer.getWord());
        }
        for (int index=0; index < 26; index++) {
            char ch = (char)('a' + index);
            if (pointer.containsChild(ch)) {
                collectChildren(pointer.getChild(ch), suggestions);
            }
        }
    }

    class TrieNode {
        String word = null;
        TrieNode[] children = new TrieNode[26]; // For lowercase English letters.

        public void put(char ch, TrieNode node) {
            children[ch - 'a'] = node;
        }

        public boolean containsChild(char ch) {
            return children[ch - 'a'] != null;
        }

        public TrieNode getChild(char ch) {
            return children[ch - 'a'];
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getWord() {
            return word;
        }
    }
}
