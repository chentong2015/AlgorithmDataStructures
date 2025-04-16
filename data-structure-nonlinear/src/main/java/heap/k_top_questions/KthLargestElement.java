package heap.k_top_questions;

import java.util.PriorityQueue;

public class KthLargestElement {

    // TODO. 直接对数据的大小值进行优先级排序，最后返回结果
    // Kth Largest Element in an Array
    // Given an integer array nums and an integer k, return the kth largest element in the array.
    // t is the kth largest element in the sorted order, not the kth distinct element.
    // Can you solve it without sorting?
    //
    // nums = [3,2,1,5,6,4], k = 2 -> 5
    // nums = [3,2,3,1,2,4,5,5,6], k = 4 -> 4
    //
    // 1 <= k <= nums.length <= 10^5
    // -10^4 <= nums[i] <= 10^4
    //
    // O(k + (n-k)*log(k)) 最差情况下min-deap最小堆每次插入新数据时都需要循环到堆的底部
    // O(k)                额外存储了k个结果数据
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int index=0; index < k; index++) {
            priorityQueue.add(nums[index]);
        }

        for (int index=k; index< nums.length; index++) {
            if (nums[index] > priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.add(nums[index]);
            }
        }

        // 优先级队列的Root节点就是结果(TopK中的最小值)
        return priorityQueue.poll();
    }
}
