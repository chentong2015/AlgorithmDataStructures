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

    // nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2] -> 2
    // (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
    // (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
    //
    // nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0] -> 1
    // (0, 0, 0, 0) -> nums1[0] + nums2[0] + nums3[0] + nums4[0] = 0
    //
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {


        return 0;
    }













    // TODO: 数组过多也需要使用Hash Table来减低时间复杂度 !!
    // 4Sum II
    // Given four integer arrays nums1, nums2, nums3, and nums4 all of length n
    // return the number of tuples (i, j, k, l) such that:
    // 0 <= i, j, k, l < n
    // nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
    // nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2] -> 2 一共有两种组合的可能
    // Test: 所有的数字展开后的排列组成有n^4种可能，基础解法的时间复杂度
    //       O(n^2) 一共出现了3次该时间复杂度  O(n^2) 需要2个这样的空间复杂度
    public int fourSumCountCC(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // Check array null or length = 0
        int sum = 0;
        Map<Integer, Integer> map1 = parseSums(nums1, nums2);
        Map<Integer, Integer> map2 = parseSums(nums3, nums4);
        for (int key : map1.keySet()) {     // O(n^2)
            if (map2.containsKey(-key)) {   // O(1)
                sum += map1.get(key) * map2.get(-key);
            }
        }
        return sum;
    }

    private Map<Integer, Integer> parseSums(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                int value = num1 + num2;
                int count = map.getOrDefault(value, 0);
                map.put(value, count + 1);
            }
        }
        return map;
    }
}
