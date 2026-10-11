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

    // TODO. Monotonic 单调栈问题: 使用栈存储前面计算过的数据
    // 2,1,5,6,2,3 -> 5*2= 10
    // 2,2,2,2,2,2 相同的数据如何处理
    // 1,2,3,4,5,6 单调递增的数据如何处理
    //
    // O(N)
    // O(N)
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>(); // 存index坐标

        for (int i = 0; i <= heights.length; i++) {
            int current = (i == heights.length) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > current) {
                int height = heights[stack.pop()];
                int width;
                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }

                int area = height * width;
                maxArea = Math.max(maxArea, area);
            }
            stack.push(i);
        }
        return maxArea;
    }

    // Brute Force 暴力破解
    // 循环每个位置的值，以一个点往它的两侧扩散，计算它能组成的最大面积
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
}