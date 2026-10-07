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
public class WordLadderBfs {

    // TODO. 构建从开始单词到结果单词的通路
    // Queue + for + for
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int steps = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return steps;
                }

                for (int i = 0; i < word.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char[] chars = word.toCharArray();
                        chars[i] = c;
                        String newWord = new String(chars);
                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            wordSet.remove(newWord);
                        }
                    }
                }
            }
            steps++;
        }
        return 0;
    }
}
