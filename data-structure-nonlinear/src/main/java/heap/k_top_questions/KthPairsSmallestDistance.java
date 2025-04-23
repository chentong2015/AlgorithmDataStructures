package heap.k_top_questions;

import java.util.Arrays;

// Find K-th Smallest Pair Distance
// The distance of a pair of integers a and b is defined
// as the absolute difference between a and b.
//
// Given an integer array nums and an integer k, return the kth smallest distance
// among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.
public class KthPairsSmallestDistance {

    // TODO. 根据距离来二分查询
    // nums = [1,3,1], k = 1  -> the 1st smallest distance pair is (1,1), and its distance is 0.
    // (1,3) -> 2
    // (1,1) -> 0
    // (3,1) -> 2
    //
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        int low = 0;
        int high = nums[nums.length - 1] - nums[0];
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = countDistanceLessThanMid(nums, mid);
            if (count >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        // low值刚好是要找的距离值，它前面有(k-1)个更小距离
        return low;
    }

    private int countDistanceLessThanMid(int[] nums, int mid) {
        int count = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            while (nums[right] - nums[left] > mid) {
                left++;  // 滑动窗口从左侧出数据
            }
            // index的距离有多少，就有多少个更小的距离数
            count += right - left;
        }
        return count;
    }
}
