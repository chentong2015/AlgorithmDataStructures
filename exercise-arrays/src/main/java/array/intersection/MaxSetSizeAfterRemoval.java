package array.intersection;

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
public class MaxSetSizeAfterRemoval {

    // TODO. 只考虑最终结果的来源，甚至不需要存储最终的结果
    public int maximumSetSize(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int index = 0; index < nums1.length; index++) {
            set1.add(nums1[index]);
            set2.add(nums2[index]);
        }

        // TODO. 计算数据Left Join和Inner Join的结果
        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        set1.removeAll(intersection);
        set2.removeAll(intersection);

        int length = nums1.length;
        int count1 = Math.min(set1.size(), length / 2);
        int count2 = Math.min(set2.size(), length / 2);
        if (count1 + count2 == length) {
            return length;
        }
        return Math.min(count1 + count2 + intersection.size(), nums1.length);
    }

    // TODO. Set1超过一半的数即使和Set2重合也可以被添加, 因为那些数注定被删除
    public int maximumSetSizePlus(int[] num1, int[] num2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        int length = num1.length;
        for (int index=0; index < length; index++) {
            set1.add(num1[index]);
            set2.add(num2[index]);
        }

        int count1 = Math.min(length / 2, set1.size());
        int remaining = set1.size() - count1;

        int count2 = 0;
        for (int num : set2) {
            if (!set1.contains(num)) {
                count2++;
            } else if (remaining > 0) {
                // 贪心(最优)算法: 替换Set1中超一半的数据,
                count2++;
                remaining--;
            }
            if (count2 >= length / 2) {
                break;
            }
        }
        return count1 + count2;
    }
}
