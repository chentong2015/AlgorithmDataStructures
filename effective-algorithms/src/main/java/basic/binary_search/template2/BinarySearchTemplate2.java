package basic.binary_search.template2;

// Search for an element or condition which requires accessing the current index and
// its immediate right neighbor's index in the array
public class BinarySearchTemplate2 {

    // 需要访问index和它前或后index(或相关的index/right)
    // 通过while(left < right)循环来逼近最终的值
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1; // 允许+1移动
            } else {
                right = mid; // 不能-1移动
            }
        }

        // End Condition: left == right
        if (left != nums.length && nums[left] == target) {
            return left; // 取最后一个位置的值
        }
        return -1;
    }
}
