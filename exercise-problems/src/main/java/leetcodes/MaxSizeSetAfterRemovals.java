package leetcodes;

import java.util.HashSet;
import java.util.Set;

// Maximum Size of a Set After Removals
// You are given two 0-indexed integer arrays nums1 and nums2 of even length n.
// You must remove n / 2 elements from nums1 and n / 2 elements from nums2.
// After the removals, you insert the remaining elements of nums1 and nums2 into a set s.
// Return the maximum possible size of the set s.
//
// nums1 = [1,2,1,2], nums2 = [1,1,1,1]
// nums1 = [2,2] and nums2 = [1,1]
// s = {1,2}. 2 is the maximum possible size of the set
//
// nums1 = [1,2,3,4,5,6], nums2 = [2,3,2,3,2,3]
// nums1 = [1,4,5] and nums2 = [2,3,2].
// s = {1,2,3,4,5}. 5 is the maximum possible size of the set
//
// nums1 = [1,1,2,2,3,3], nums2 = [4,4,5,5,6,6]
// nums1 = [1,2,3] and nums2 = [4,5,6]
// s = {1,2,3,4,5,6}
public class MaxSizeSetAfterRemovals {

    public static void main(String[] args) {
        int[] nums1 = {2,4,1,4};
        int[] nums2 = {10,2,4,10};
        MaxSizeSetAfterRemovals removals = new MaxSizeSetAfterRemovals();
        System.out.println(removals.maximumSetSize(nums1, nums2));
    }

    // TODO. 问题的本质是什么? 输入和输出数据有什么特征?
    // [1,2,1,2], [1,1,1,1]
    // 1 2, 1 1
    //
    // [1,2,3,4,5,6], [2,3,2,3,2,3]
    // 1 2 3 4 5 6, 2 3 3
    //
    // [1,1,1,1], [12,23,41,9]
    // 1 1, 9 12 23 41
    //
    // [10,8,7,9], [7,9,9,5]
    // 7 8 9 10, 5 7
    // 8 10, 5 7
    //
    // [2,4,1,4], [10,2,4,10]
    // 1 2 4, 2 4 10
    public int maximumSetSize(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int index = 0; index < nums1.length; index++) {
            set1.add(nums1[index]);
            set2.add(nums2[index]);
        }

        Set<Integer> result = new HashSet<>();
        int middle = nums1.length / 2;
        if (set1.size() > middle) {
            for (int num: set1) {
                if (!set2.contains(num)) {
                    result.add(num);
                    if (result.size() == middle) {
                        break;
                    }
                }
            }
        } else {
            result.addAll(set1);
        }

        result.addAll(set2);
        return Math.min(result.size(), nums1.length);
    }
}
