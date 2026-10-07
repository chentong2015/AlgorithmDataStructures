package amazonTop50;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// Find the K-Sum of an Array
// You are given an integer array nums and a positive integer k
// You can choose any subsequence of the array and sum all of its elements together.
// K-Sum is the kth largest subsequence sum that can be obtained (not necessarily distinct).
// Return the K-Sum of the array.
//
// 1 <= n <= 10^5
// -10^9 <= nums[i] <= 10^9
// 1 <= k <= min(2000, 2n)
public class KLargestSumArray {

    // TODO. 第k大子序列和 = 最大子序列和 - 第k-1小的损失
    //  使用最小堆来存储损失的SUM的排序值
    //
    // [2,4,-2], k = 5 -> 2
    // 6, 4, 4, 2, 2, 0, 0, -2.
    //
    // [2,2,4]        排序后的数组
    // 6              起始点最大值
    // 6 - 2 = 4      第一个损失点
    // 6 - 2 = 4      第二个损失点
    // 6 - 4 = 2
    // 6 - 2 - 2 = 2
    // 6 - 2 - 4 = 0
    // 6 - 2 - 4 = 0
    // 6 - 2 - 2 - 4 = -2
    //
    // O(N + NlogN + KlogN)  避免罗列子序列全集O(2^n)
    // O(2N)                 最多维护N个级别的损失

    public long kSum(int[] nums, int k) {
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >0) {
                sum += nums[i];
            } else {
                nums[i] = Math.abs(nums[i]); // 改成绝对值, 作为损失值
            }
        }
        if (k==1) {
            return sum;
        }

        Arrays.sort(nums); // 排序全部数据, 从最小损失开始

        // 用最小堆中存储所有损失sum的排序: 从损失第一个最小值开始
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(Comparator.comparingLong(a -> a.sum));
        minHeap.add(new Pair(nums[0],1));

        while (k > 1) {
            Pair pair = minHeap.poll();       // 每一轮取一个最小损失
            long minSumReduction = pair.sum;
            k--;
            if (k==1) {
                return sum - minSumReduction; // 返回损失最小和后的结果
            }

            // Backtracking 撤销前一个最小折扣，构成更小折扣组合
            int idx = pair.idx;
            if (idx < nums.length) {
                minHeap.add(new Pair(minSumReduction + nums[idx],idx+1));
                minHeap.add(new Pair(minSumReduction - nums[idx-1] + nums[idx],idx+1));
            }
        }
        return 0;
    }

    class Pair {
        long sum;
        int idx;
        public Pair(long sum, int idx) {
            this.sum=sum;
            this.idx=idx;
        }
    }
}