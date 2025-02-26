package collections.stack.next_greater;

import java.util.Stack;

// Next Greater Element I
// Two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
// For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j]
// and determine the next greater element of nums2[j] in nums2.
//
// 1 <= nums1.length <= nums2.length <= 1000
// 0 <= nums1[i], nums2[i] <= 104
// All integers in nums1 and nums2 are unique.
// All the integers of nums1 also appear in nums2.
public class NextGreaterElement1 {

    // Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
    // Output: [-1,3,-1]
    //
    // nums1 = [2,4], nums2 = [1,2,3,4]
    // Output: [3,-1]
    //
    // O(N)
    // O(1)
    public static void getNextGreaterElement1(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int index = nums.length - 1; index >= 0; index--) {
            int currentValue = nums[index];
            while (!stack.empty() && stack.peek() <= currentValue) {
                stack.pop();
            }

            // Pop掉Stack中比当前位置值小的元素后，如果有，那么一定比currentValue大
            if (stack.empty()) {
                nums[index] = -1;
            } else {
                nums[index] = stack.peek();
            }

            // 再将currentValue入栈用于计算它前面位置的结果
            stack.push(currentValue);
        }
    }
}
