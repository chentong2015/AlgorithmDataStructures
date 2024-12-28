package collections.tree.prefix_tree.model;

public class TrieNodeWithWord {

    // 直接存储该位置所形成的单词，方便直接提取
    public String word;
    public TrieNodeWithWord[] children;

    public TrieNodeWithWord() {
        children = new TrieNodeWithWord[26];
    }
}
