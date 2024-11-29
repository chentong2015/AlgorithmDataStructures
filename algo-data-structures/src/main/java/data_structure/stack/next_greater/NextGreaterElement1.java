package data_structure.stack.next_greater;

import java.util.Stack;

// TODO. 使用Stack数据结构来快速判断Next Greater Element
public class NextGreaterElement1 {

    // Next Greater Element I
    // Two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
    // For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j]
    // and determine the next greater element of nums2[j] in nums2.
    //
    // Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
    // Output: [-1,3,-1]
    //
    // Explanation: The next greater element for each value of nums1 is as follows:
    // - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
    // - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
    // - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
    //
    // 前提条件，一定是离散的数据，且一定是子集的关系
    //
    // O(N)  O(1) 直接在原始数组上操作，没有空间复杂度
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

    public static void main(String[] args) {
        int[] nums = {3, 4, 2, 5, 3, 7, 1};
        getNextGreaterElement1(nums);

        // 4 5 5 7 7 -1 -1
        for (int num: nums) {
            System.out.print(num + " ");
        }
    }
}
