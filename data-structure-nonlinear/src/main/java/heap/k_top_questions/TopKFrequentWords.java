package heap.k_top_questions;

import java.util.*;

// Top K Frequent Words
// Given an array of strings words and an integer k, return the k most frequent strings.
// Return the answer
// Sorted by the frequency from highest to lowest.
// Sort the words with the same frequency by their lexicographical order.
//
// 1 <= words.length <= 500
// 1 <= words[i].length <= 10
// words[i] consists of lowercase English letters.
// k is in the range [1, The number of unique words[i]]
//
// Could you solve it in O(n log(k)) time and O(n) extra space ?
public class TopKFrequentWords {

    public static void main(String[] args) {
        String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is"};
        TopKFrequentWords instance = new TopKFrequentWords();
        System.out.println(instance.topKFrequent(words, 4));
    }

    // TODO. 根据统计的频率进行排序
    // words = ["i","love","leetcode","i","love","coding"], k = 2
    // -> ["i","love"]
    //
    // words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
    // -> ["the","is","sunny","day"]
    //
    // word1: 10
    // word2: 5
    // word3: 2
    // word4: 1
    //
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            int frequency = map.getOrDefault(word, 0);
            map.put(word, frequency + 1);
        }

        // [Word, frequency] 排序单词和统计频率: 注意反转排序
        PriorityQueue<HeapNode> heap = new PriorityQueue<>(
                Comparator.comparingInt((HeapNode o) -> o.frequency).reversed());

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            heap.offer(new HeapNode(entry.getKey(), entry.getValue()));
            if (heap.size() > k) {
                heap.poll();
            }
        }

        // 针对相同frequency频率的单词排序
        List<String> result = new ArrayList<>();
        while (!heap.isEmpty()) {
            List<String> topWords = new ArrayList<>();
            HeapNode node = heap.poll();
            int frequency = node.frequency;
            topWords.add(node.word);

            while (!heap.isEmpty() && frequency == heap.peek().frequency) {
                topWords.add(heap.poll().word);
            }
            Collections.sort(topWords);
            result.addAll(topWords);
        }
        return result;
    }

    class HeapNode {
        String word;
        int frequency;

        public HeapNode(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }
    }
}
