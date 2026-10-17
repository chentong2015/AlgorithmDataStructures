package basic.searching.template2;

// Find Minimum in Rotated Sorted Array
// Given the sorted rotated array nums of unique elements,
// return the minimum element of this array
public class FindMinRotatedSortedArray {

    // nums = [0,1,2,4,5,6,7] -> [4,5,6,7,0,1,2]
    // rotated 4 times  -> minValue = 0
    public static int findMinIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] > nums[right]) {
                left = middle + 1; // 如果中间的位置的值大于右端位置的值，则最小值在右边
            } else {
                right = middle;
            }
        }
        // 出循环条件: left=right当两个位置坐标相遇, 说明前面一个位置是大值，后面一个位置也是大值 !!
        return nums[left];
    }
}
