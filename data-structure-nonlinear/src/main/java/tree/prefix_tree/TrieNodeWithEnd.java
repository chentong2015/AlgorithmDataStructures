package tree.prefix_tree;

// 通过字符构建的前缀树结构: 标记end结尾
//
//                       abcdefghijklmnopqrstuvwxyz
//                   link                       link
//  abcdefghijklmnopqrstuvwxyz ...             abcdefghijklmnopqrstuvwxyz
//             link                          link                      link
//  abcdefghijklmnopqrstuvwxyz ... abcdefghijklmnopqrstuvwxyz  ...   abcdefghijklmnopqrstuvwxyz
//        link
//  abcdefghijklmnopqrstuvwxyz ...
public class TrieNodeWithEnd {

    private TrieNodeWithEnd[] links;
    private boolean isEnd;

    public TrieNodeWithEnd() {
        links = new TrieNodeWithEnd[26];
    }

    // char-'a'转换index位置，对应children位置上添加结点
    public void put(char ch, TrieNodeWithEnd node) {
        links[ch - 'a'] = node;
    }

    // 判断指定位置上是否有node child
    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public TrieNodeWithEnd get(char ch) {
        return links[ch - 'a'];
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
