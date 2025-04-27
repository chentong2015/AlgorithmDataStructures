package sorting.questions;

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

    // TODO. 自定义Comparator比较器进行排序
    // words = ["i","love","leetcode","i","love","coding"], k = 2
    // -> ["i","love"]
    //
    // words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
    // -> ["the","is","sunny","day"]
    //
    // O(N*logN)    Comparator排序造成的复杂度
    // O(N + N + K) 需要三个存储空间
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            int frequency = map.getOrDefault(word, 0);
            map.put(word, frequency + 1);
        }

        List<Node> nodes = new ArrayList<>();
        for (String key : map.keySet()) {
            nodes.add(new Node(key, map.get(key)));
        }

        // TODO. 先根据频率，然后根据单词排序
        Collections.sort(nodes, (node1, node2) -> {
            if (node1.frequency > node2.frequency) {
                return -1;
            } else if (node1.frequency < node2.frequency) {
                return 1;
            } else {
                return node1.word.compareTo(node2.word);
            }
        });

        List<String> result = new ArrayList<>();
        for (int index = 0; index < k; index++) {
            result.add(nodes.get(index).word);
        }
        return result;
    }

    class Node {
        String word;
        int frequency;

        public Node(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }
    }
}
