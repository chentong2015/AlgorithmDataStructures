package heap.k_top_questions;

import java.util.*;

// TODO. PriorityQueue金典运用: 对元素出现的频率进行优先级排序，然后取TOP k频率
public class TopKFrequentElements {

    // TODO. 根据元素的输入值范围设置定长的统计数组
    // Top K Frequent Elements
    // Given an integer array nums and an integer k, return the k most frequent elements
    //
    // O(Nlogk) if k < N and O(N) in the particular case of N = k
    // O(N+k) to store the hash map with not featured N elements and a heap with k elements
    //
    // 1 <= nums.length <= 105
    // -104 <= nums[i] <= 104
    // k is in the range [1, the number of unique elements in the array].
    // It is guaranteed that the answer is unique.
    //
    // nums = [1,1,1,2,2,3], k = 2 -> [1,2]
    public int[] topKFrequent(int[] nums, int k) {
        int[] frequency = new int[20001];
        for (int num : nums) {
            frequency[num + 10000]++;
        }

        // TODO. 根据统计频率来排序，构建Max-Heap最大堆(存储TopK元素)
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < 20001; i++) {
            if (frequency[i] > 0) {
                maxHeap.offer(new int[] {i - 10000, frequency[i]});
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll()[0];
        }
        return result;
    }
}





