package tree.prefix_tree;

import tree.prefix_tree.base.TrieNodeWithEnd;

// 如果在当前root的指定位置有找到对应的字符, 则取下一个node child, 最后表明key的结束
// O(m) O(m) 最差情况下需要添加m个node(造成的空间)
//      root
//    c      l
//   o         e -> end key "le" 共享的字符串只会存储一次(空间)
//  d            e
// e ->"code"      t -> end key "leet" 前面具有共享的字符段
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
        // 标记一个单词记录的结尾标志
        node.setEnd();
    }

    public boolean startsWith(String prefix) {
        TrieNodeWithEnd node = searchPrefix(prefix);
        return node != null;
    }

    // 如果没有依次按照字符串的顺序往后找到end位置，则该word不在Prefix tree中
    private TrieNodeWithEnd searchPrefix(String word) {
        TrieNodeWithEnd node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                return null;
            }
            node = node.get(currentChar);
        }
        return node;
    }
}
