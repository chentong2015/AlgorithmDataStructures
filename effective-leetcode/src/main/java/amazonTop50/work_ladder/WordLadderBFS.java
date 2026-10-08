package amazonTop50.work_ladder;

import java.util.*;

// Word Ladder
// A transformation sequence from word beginWord to word endWord
// using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
//  - Every adjacent pair of words differs by a single letter.
//  - Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
//  - sk == endWord
// All the words in wordList are unique.
//
// 1 <= beginWord.length <= 10
// 1 <= wordList.length <= 5000
// beginWord != endWord
// endWord.length == beginWord.length
// beginWord, endWord, and wordList[i] consist of lowercase English letters.
public class WordLadderBFS {

    // TODO. BFS从开始单词逐步构建(组合a-z字符)到目标单词的通路
    // Queue + while/for 层级遍历 + for 组合所有字符
    //
    // "hit", "cog", ["hot","dot","dog","lot","log","cog"] -> 5
    // "hit" -> "hot" -> "dot" -> "dog" -> cog"
    //
    // O(N+N*L*26) 单词的数量,单词的字符数,常量字符
    // O(N)        存储全部单词
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 使用HashSet快速判断是否存在
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int steps = 1;
        while (!queue.isEmpty()) { // 两层while的循环中次数O(N)级别
            int size = queue.size();
            while (size > 0) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return steps;
                }

                // 组合成所有单词，如果在提供列表中则添加到下一层Queue中
                char[] chars = word.toCharArray();
                for (int i = 0; i < word.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String newWord = new String(chars);
                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            wordSet.remove(newWord);
                        }
                    }
                }
                size--;
            }

            // 每一个层级增加延伸一步
            steps++;
        }
        return 0;
    }
}