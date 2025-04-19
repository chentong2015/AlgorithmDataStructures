package searching_binary.left_right;

// Find First and Last Position of Element in Sorted Array
// Given an array of integers nums sorted in non-decreasing order
// find the starting and ending position of a given target value.
// If target is not found in the array, return [-1, -1].
//
// You must write an algorithm with O(log n) runtime complexity.
// 0 <= nums.length <= 10^5
// -10^9 <= nums[i] <= 10^9
// nums is a non-decreasing array.
// -10^9 <= target <= 10^9
public class FirstLastElementSortedArray {

    // TODO. 使用两次二分查找，找到某个值的位置区间，二分循环到底
    // nums = [5,7,7,8,8,10], target = 8
    // [3,4]
    //
    // nums = [5,7,7,8,8,10], target = 6
    // [-1,-1]
    //
    // nums = [], target = 0
    // [-1,-1]
    public int[] searchRange(int[] nums, int target) {
        int left = binarySearch(nums, target, true);
        int right = binarySearch(nums, target, false);
        return new int[] {left, right};
    }

    // TODO. 金典二分查询到相同值的左右边缘
    private int binarySearch(int[] nums, int target, boolean isSearchingLeft) {
        int left = 0;
        int right = nums.length - 1;
        int idx = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // 继续二分法查找
                // 如果找起始位置则往左侧移动，如果找结束位置则往右侧移动
                idx = mid;
                if (isSearchingLeft) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return idx;
    }
}
