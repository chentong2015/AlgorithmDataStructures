package search_DFS.word_concatenated;

import java.util.*;

// Concatenated Words
//
// "a", "aaa", "aaaa", "aaaaa"
// -> "aaa", "aaaa", "aaaaa"
// 时间复杂度一般
// 空间复杂度较差
public class WordsConcatenated {

    // TODO. DP 动态编程: 记录历史操作的数据/结果
    // - set 记录离散的数据元素
    // - memoryWords 记录遍历过程中每个单词的"判断结果"，以便后续重复利用
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        Map<String, Boolean> memoryWords = new HashMap<>();
        List<String> result = new ArrayList<>();
        for(String word : words){
            if(concatable(word, set, memoryWords))
                result.add(word);
        }
        return result;
    }

    // TODO. String = Prefix + Suffix 拆解字符串前缀和后缀
    // prefix 前缀必须能够找到对应的Word
    // suffix 后缀也必须找到对应的Word
    // suffix 或者后面又能继续查分成(prefix + suffix)进行判断
    public boolean concatable(String word, Set<String> set, Map<String, Boolean> memoryWords){
        if(memoryWords.containsKey(word)) {
            return memoryWords.get(word);
        }

        for(int i = 1; i < word.length(); i++){
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);
            // DFS 深度递归算法
            if(set.contains(prefix) && (set.contains(suffix) || concatable(suffix, set, memoryWords))){
                memoryWords.put(word, true);
                return true;
            }
        }
        memoryWords.put(word, false);
        return false;
    }
}
