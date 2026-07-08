package array.mountain_array;

// Find Peak Element
// A peak element is an element that is strictly greater than its neighbors.
// If the array contains multiple peaks, return the index to any of the peaks.
public class FoundPeakElement {

    // TODO. 二分搜索山峰, 控制middle左右移动
    // nums = [1] -> 0
    // nums = [1,2] -> 1
    // nums = [2,1] -> 1
    // nums = [1,2,3] -> 2
    // nums = [3,2,1] -> 0
    // nums = [1,2,3,1] -> 2
    //
    // O(logN)
    // O(1)
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length -1;
        while(left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]){
                left = mid + 1; // 上升情况往右侧移动
            } else{
                right = mid;
            }
        }
        return left;
    }
}
