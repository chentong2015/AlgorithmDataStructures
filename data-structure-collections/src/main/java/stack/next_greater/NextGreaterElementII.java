package stack.next_greater;

import java.util.Stack;

// Next Greater Element II
// Given a circular integer array nums (the next element of nums[nums.length - 1] is nums[0]),
// return the next greater number for every element in nums
//
// The next greater number of a number x is the first greater number
// to its traversing-order next in the array
//
// 1 <= nums.length <= 10^4
// -10^9 <= nums[i] <= 10^9
public class NextGreaterElementII {

    // TODO. Circular Array 循环统计N+(N-1)次并定位index位置
    // nums = [1,2,1]     -> [2,-1,2]
    // nums = [1,2,3,4,3] -> [2,3,4,-1,4]
    //
    // 1,2,3,4,3 + 1,2,3,4 多循环n-1的数据
    //    <- i
    //
    // O(N + N -1)
    // O(N + N)
    public int[] nextGreaterElements(int[] nums) {
        int size = nums.length;
        int[] res = new int[size];

        Stack<Integer> stack = new Stack<>();
        for (int i = size + (size-1); i >= 0; --i) {
            int numIndex = i % size;
            // 出栈stack中比当前值小的元素
            while (!stack.empty() && nums[stack.peek()] <= nums[numIndex]) {
                stack.pop();
            }

            // 根据存储的坐标找到后面更大的值
            res[numIndex] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(numIndex);
        }
        return res;
    }
}
