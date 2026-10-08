package tree.prefix_tree;

// 在prefix结尾处标记prefix完整字符串，字符串非空则记为有效单词
public class TrieNodeWithWord {

    // 直接存储该位置所形成的单词，方便直接提取
    public String word;

    public TrieNodeWithWord[] children;

    public TrieNodeWithWord() {
        children = new TrieNodeWithWord[26];
    }

}
