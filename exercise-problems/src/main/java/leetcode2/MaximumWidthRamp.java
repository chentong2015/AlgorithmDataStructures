package leetcode2;

// Maximum Width Ramp
// A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= nums[j]
// The width of such a ramp is j - i.
// Given an integer array nums, return the maximum width of a ramp in nums.
// If there is no ramp in nums, return 0.
//
// 2 <= nums.length <= 5 * 10^4
// 0 <= nums[i] <= 5 * 10^4
public class MaximumWidthRamp {

    // 6,0,8,2,1,5 -> (1, 5) -> 4
    // 9,8,1,0,1,9,4,0,4,1 -> (2,9) -> 7
    // 5,5,4,4,4,4,1 -> 3
    // 6,5,4,3,2,1 -> 0
    //
    // TODO. Sorting 算法 O(N*logN)
    // 按照数值sorting之后，每个数值前面数据都是符合大小要求的
    // 找到前面最小的index和当前的index比较，需要满足坐标要求
    // [6,0,8,2,1,5]
    // [0,1,2,5,6,8]
    //
    // TODO. Two Pointers 双指针算法: 双指针往相同方向移动
    // [6,0,8,2,1,5]
    // [8,8,8,5,5,5]
    // move left: nums[left] > rightMax[right] 不满足要求，移动左边
    // move right: nums[i] <= rightMax[i] 满足要求，更新right - left
    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int[] rightMax = new int[n];
        rightMax[n-1] = nums[n-1];
        for (int i = n-2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], nums[i]);
        }

        int left = 0;
        int right = 0;
        int result = 0;
        while (right < n) {
            while (left < right && nums[left] > rightMax[right]) {
                left++;
            }
            result = Math.max(result, right - left);
            right++;
        }
        return result;
    }
}
