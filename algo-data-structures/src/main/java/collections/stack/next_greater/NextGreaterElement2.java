package collections.stack.next_greater;

import java.util.Stack;

public class NextGreaterElement2 {

    // TODO. Circular Array 对于循环数组，循环统计N+(N-1)次并定位index位置
    // Next Greater Element II
    // Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
    // return the next greater number for every element in nums
    // The next greater number of a number x is the first greater number to its traversing-order next in the array
    // 1 <= nums.length <= 10^4
    // -10^9 <= nums[i] <= 10^9
    //
    // nums = [1,2,1]     -> [2,-1,2]
    // nums = [1,2,3,4,3] -> [2,3,4,-1,4]
    //
    // O(2n)=O(n) O(2n)=O(n)
    public int[] nextGreaterElementsStack(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int size = nums.length;
        int[] res = new int[size];

        for (int i = size + (size-1); i >= 0; --i) {
            int numIndex = i % size; // 原始数组的index坐标，从后往前遍历

            // 从遍历过程中存储的之后的值中找到比当前位置大的值
            while (!stack.empty() && nums[stack.peek()] <= nums[numIndex]) {
                stack.pop();
            }

            // Stack中的数据要么被找完，要么找到更大的值
            // stack.peek()不能将数据出栈，否则会影响之后的判断
            res[numIndex] = stack.empty() ? -1 : nums[stack.peek()];

            // 最后存储原始数组的坐标，用于判断它是否比它的前面值大
            stack.push(numIndex);
        }
        return res;
    }
}
