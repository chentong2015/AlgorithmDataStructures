package templates.k_problems.priority_queue;

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

        // 最后优先级队列的Root节点就是结果
        return priorityQueue.poll();
    }
}
