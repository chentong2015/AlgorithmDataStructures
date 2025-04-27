package heap;

import java.util.PriorityQueue;

// Given an array of integers nums,
// sort the array in ascending order and return it.
//
// You must solve the problem without using any built-in functions in O(nlog(n)) time complexity
// and with the smallest space complexity possible.
public class HeapSorting {

    // TODO. 使用PriorityQueue优先堆来排序数据
    // nums = [5,2,3,1] -> [1,2,3,5]
    // nums = [5,1,1,2,0,0] -> [0,0,1,1,2,5]
    //
    // O(N*logN) 降低时间复杂度
    // O(K)      空间复杂度是入堆的数据大小
    public int[] sortArray(int[] nums) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num: nums) {
            heap.offer(num);
        }
        int index = 0;
        while (!heap.isEmpty()) {
            nums[index++] = heap.poll();
        }
        return nums;
    }
}


