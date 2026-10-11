package leetcode5.rectangle_histogram;

import java.util.Stack;

// Largest Rectangle in Histogram
// Given an array of integers heights representing the histogram's bar height
// where the width of each bar is 1,
// return the area of the largest rectangle in the histogram.
//
// 1 <= heights.length <= 10^5
// 0 <= heights[i] <= 10^4
public class LargestRectangleHistogram {

    // Brute Force
    // 循环每个位置点往两侧扩散，把大于等于它的柱状体包含其中
    // 可提前找到每个位置左右扩散的坐标，避免每个坐标的内部循环 !!
    // O(N*N)
    // O(1)
    public int largestRectangleAreaTLE(int[] heights) {
        int result = 0;
        for (int index = 0; index < heights.length; index++) {
            int sum = heights[index];
            int left = index - 1;
            while (left >= 0 && heights[left] >= heights[index]) {
                sum += heights[index];
                left--;
            }
            int right = index + 1;
            while (right < heights.length && heights[right] >= heights[index]) {
                sum += heights[index];
                right++;
            }
            result = Math.max(result, sum);
        }
        return result;
    }

    // TODO. 先将Index位置左右两侧的最近低坐标找出来, 在遍历时直接计算宽度
    // 2,1,5,6,2,3 -> 5*2= 10
    // 2,2,2,2,2,2
    // 1,2,3,4,5,6
    //
    // O(N)
    // O(N)
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        // 使用栈存储单调递增的index位置
        Stack<Integer> stack = new Stack<>();

        // 左侧最近的较小值, 以index位置能延伸最左位置
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // 右侧最近的较小值, 以index位置能延伸的最右位置
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = right[i] - left[i] - 1;
            maxArea = Math.max(maxArea, heights[i] * width);
        }
        return maxArea;
    }
}