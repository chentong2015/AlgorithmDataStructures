package leetcode5;

import java.util.PriorityQueue;

// Largest Number
// Given a list of non-negative integers nums,
// arrange them such that they form the largest number and return it.
//
// Since the result may be very large, so you need to return a string instead of an integer.
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 10^9
public class LargestNumber {

    // TODO. Num两两比较 == Str字符串两两比较
    // [10,2] -> "210"
    // [3,30,34,5,9] -> "9534330"
    // [34323,3432]  -> "343234323"
    //
    // O(N*logN) 在遍历的过程中动态排序
    // O(N+N)    所有0-9字符的存储
    public String largestNumber(int[] nums) {
        PriorityQueue<String> maxHeap = new PriorityQueue<>((str1, str2) -> {
            String result1 = str1 + str2;
            String result2 = str2 + str1;
            // 注意对比较的结果取反，组成最大堆
            if (result1.compareTo(result2) > 0) {
                return -1;
            } else if (result1.compareTo(result2) < 0) {
                return 1;
            }
            return 0;
        });

        boolean allZero = true; // 特殊场景全0的处理
        for (int num : nums) {
            if (num > 0) {
                allZero = false;
            }
            maxHeap.add(String.valueOf(num));
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            stringBuilder.append(maxHeap.poll());
        }
        return allZero ? "0" : stringBuilder.toString();
    }
}
