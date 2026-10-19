package leetcode6;

// Container With Most Water
// You are given an integer array height of length n.
// There are n vertical lines drawn such that
// the two endpoints of the ith line are (i, 0) and (i, height[i]).
//
// Find two lines that together with the x-axis form a container,
// such that the container contains the most water.
// Return the maximum amount of water a container can store.
//
// n == height.length
// 2 <= n <= 10^5
// 0 <= height[i] <= 10^4
public class ContainerMostWater {

    // TODO. Two Pointers 双指针移动, 储水问题以更大高度为准
    //
    // [1,8,6,2,5,4,8,3,7] -> 7*7=49
    //    |             |
    //  0 1 2 3 4 5 6 7 8
    //
    // [1,1] -> 1
    //  | |
    //
    // O(N)
    // O(1)
    public int maxArea(int[] height) {
        if (height.length == 2) {
            return Math.min(height[0], height[1]);
        }

        int maxArea = 0;
        int left = 0;
        int right = height.length-1;
        while (left != right) {
            if (height[left] <= height[right]) {
                maxArea = Math.max(maxArea, height[left] * (right - left));
                left++;
            } else {
                maxArea = Math.max(maxArea, height[right] * (right - left));
                right--;
            }
        }
        return maxArea;
    }
}
