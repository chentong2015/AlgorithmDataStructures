package arrays.sum_questions;

import java.util.HashMap;
import java.util.Map;

// 4Sum II
// Given four integer arrays nums1, nums2, nums3, and nums4 all of length n
// return the number of tuples (i, j, k, l) such that:
//   0 <= i, j, k, l < n
//   nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
//
// n == nums1.length
// n == nums2.length
// n == nums3.length
// n == nums4.length
// 1 <= n <= 200
// -2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28
public class Four4SumII {

    // TODO. 对半划分降低维度: 求一正一负的组合情况
    // nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2] -> 2
    // (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
    // (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
    //
    // nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0] -> 1
    // (0, 0, 0, 0) -> nums1[0] + nums2[0] + nums3[0] + nums4[0] = 0
    //
    // -2 -1 1 2 选择的结果组合不能重复
    // -1 -2 1 2
    //
    // O(N*N + N*N) 以时间复杂度换取空间复杂度
    // O(N*N)
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int a : nums1) {
            for (int b : nums2) {
                int sum = a + b;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        for (int c : nums3) {
            for (int d : nums4) {
                int target = -(c + d);
                count += map.getOrDefault(target, 0);
            }
        }
        return count;
    }
}
