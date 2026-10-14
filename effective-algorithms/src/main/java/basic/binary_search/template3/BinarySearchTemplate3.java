package basic.binary_search.template3;

// Search for an element or condition which requires
// accessing the current index and its immediate left and right neighbor's index in the array
public class BinarySearchTemplate3 {

    // 需要访问index以及它的left和right相邻的位置，同时要考虑3个位置处的数据
    // 使用(left + 1 < right)循环条件
    int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) {
                left = mid; // 不能+1移动
            } else {
                right = mid; // 不能-1移动
            }
        }

        // End Condition: left + 1 == right
        // 注意出循环的条件，left和right有间隔，需要做左右两边判断
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }
}
