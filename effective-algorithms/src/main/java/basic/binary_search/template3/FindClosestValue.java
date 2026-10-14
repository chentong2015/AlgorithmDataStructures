package basic.binary_search.template3;

public class FindClosestValue {

    // 找到数组中一个最接近(有可能相等)某值的值(或位置)
    private static int findClosestValue(int[] nums, int x) {
        int low = 0;
        int high = nums.length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == x) {
                return nums[mid];
            }
            if (nums[mid] < x) {
                low = mid;
            } else {
                high = mid;
            }
        }

        // End condition: left + 1 = high 只有两种可能，left或者right更接近x
        if (Math.abs(nums[low] - x) <= Math.abs(nums[high] - x)) {
            return nums[low];
        }
        return nums[high];
    }
}
