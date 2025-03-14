package collections.stack.next_greater;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// Next Greater Element I
// Two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
// For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j]
// and determine the next greater element of nums2[j] in nums2.
//
// 1 <= nums1.length <= nums2.length <= 1000
// 0 <= nums1[i], nums2[i] <= 10^4
//
// All integers in nums1 and nums2 are unique.
// All the integers of nums1 also appear in nums2.
public class NextGreaterElementI {

    // TODO. 由于值的唯一性，可以将其作为Key映射到Index坐标
    // Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
    // Output: [-1,3,-1]
    //
    // nums1 = [2,4], nums2 = [1,2,3,4]
    // Output: [3,-1]
    //
    // O(N + N - 1)
    // O(N + N) 最差情况是栈中会存储所以数据
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();

        Stack<Integer> stack = new Stack<>();
        for (int index = nums2.length - 1; index >= 0; index--) {
            int num = nums2[index];
            map.put(num, index);

            // 出栈stack中比当前值小的元素
            while (!stack.empty() && stack.peek() <= num) {
                stack.pop();
            }
            nums2[index] = stack.isEmpty() ? -1: stack.peek();
            stack.push(num);
        }

        for (int index = 0; index < nums1.length; index++) {
            int foundIndex = map.get(nums1[index]);
            nums1[index] = nums2[foundIndex];
        }
        return nums1;
    }
}
