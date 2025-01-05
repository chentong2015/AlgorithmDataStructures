package murex;

import java.util.HashMap;
import java.util.Map;

public class MurexQuestion2 {

    class Word {
        public String leftMost;
        public String rightMost;
    }

    // 查找第一个出现指定次数的单词，和另一个出现在最右边的最多次数的单词(如果存在的话)
    // 排序统计(map中插入键值时会涉及到字符排序)，忽略单词的大小写比较
    // 返回知道的两个可能的结果
    // O(N) O(N)
    public Word findTheRepeatedWord(String sentence, int n) {
        boolean hasFoundLeftMostWord = false; // 标记是否已经知道第一个指定数目的单词
        int rightMostCount = 0; // 标记最大的单词统计数目
        Word result = new Word();
        Map<String, Integer> wordCounts = countWords(sentence);
        for (Map.Entry<String, Integer> item : wordCounts.entrySet()) {
            if (!hasFoundLeftMostWord && item.getValue() == n) {
                hasFoundLeftMostWord = true;
                result.leftMost = item.getKey();
            }
            // 如果相等，仍然需要纪录最右边的单词
            if (item.getValue() >= rightMostCount) {
                rightMostCount = item.getValue();
                result.rightMost = item.getKey();
            }
        }
        if (result.leftMost.equals(result.rightMost)) {
            result.rightMost = null;
        }
        return result;
    }

    private Map<String, Integer> countWords(String sentence) {
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : sentence.split(" ")) {
            String lowerCaseWord = word.toLowerCase();
            int wordCount = 1;
            if (wordCounts.containsKey(lowerCaseWord)) {
                wordCount = wordCounts.get(lowerCaseWord) + 1;
            }
            wordCounts.put(lowerCaseWord, wordCount);
        }
        return wordCounts;
    }
}
