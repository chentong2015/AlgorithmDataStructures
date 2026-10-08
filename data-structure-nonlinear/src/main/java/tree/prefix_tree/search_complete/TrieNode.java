package tree.prefix_tree.search_complete;

public class TrieNode {

    String word = null; // 直接存储结尾的完整单词
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
