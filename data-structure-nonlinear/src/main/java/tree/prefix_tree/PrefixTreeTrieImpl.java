package tree.prefix_tree;

// Implement Trie (Prefix Tree)
//
//      root
//    c      l
//   o         e -> end key "le" 共享的字符串只会存储一次(空间)
//  d            e
// e ->"code"      t -> end key "leet" 前面具有共享的字符段
//
// O(m) 最差情况下需要添加m个node(造成的空间)
// O(m)
public class PrefixTreeTrieImpl {

    // 整个Trie Tree的根节点位置
    private TrieNodeWithEnd root = new TrieNodeWithEnd();

    public void insert(String word) {
        TrieNodeWithEnd node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNodeWithEnd());
            }
            node = node.get(currentChar);
        }
        node.setEnd(); // 标记完整单词结尾标志
    }

    // 判断是否能找到之前插入的单词, 必须是完整单词(带End标志)
    // Returns true if the string word is in the trie (was inserted before)
    public boolean search(String word) {
        TrieNodeWithEnd node = findTrieNode(word);
        return node != null && node.isEnd();
    }

    // 判断之前插入的单词是否有相同的Prefix前缀
    // Returns true if there is a previously inserted string word that has the prefix
    public boolean startsWith(String prefix) {
        TrieNodeWithEnd node = findTrieNode(prefix);
        return node != null; // 非空则表示这个Prefix路径被构建过
    }

    private TrieNodeWithEnd findTrieNode(String prefix) {
        TrieNodeWithEnd node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char currentChar = prefix.charAt(i);
            if (!node.containsKey(currentChar)) {
                return null;
            }
            node = node.get(currentChar);
        }
        return node;
    }
}