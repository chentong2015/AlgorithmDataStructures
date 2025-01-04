package collections.queue_heap.topK_questions;

import java.util.*;

// TODO. PriorityQueue金典运用: 对元素出现的频率进行优先级排序，然后取TOP k频率
public class TopKFrequentElements {

    // Top K Frequent Elements
    // Given an integer array nums and an integer k, return the k most frequent elements
    //
    // O(Nlogk) if k < N and O(N) in the particular case of N = k
    // O(N+k) to store the hash map with not featured N elements and a heap with k elements
    //
    // O(nlog(k)) 生成优先队列时需要逐个排序
    // O(n+k+k)   开辟三个数据的存储空间
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == k) {
            return nums;
        }
        HashMap<Integer, Integer> results = new HashMap<>();
        for (int num : nums) {
            int baseCount = results.getOrDefault(num, 0);
            results.put(num, baseCount + 1);
        }

        // TODO. 根据Hashmap统计的频率进行排序
        //  只需要在队列中存储k个结果，多的数据会降低队列复杂度
        Queue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(results::get));
        for (int n : results.keySet()) {
            heap.offer(n);
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        // 从队列中取出最后的结果数据: 将Integer拆箱成int数据
        int[] top = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            top[i] = heap.poll();
        }
        return top;
    }
}





