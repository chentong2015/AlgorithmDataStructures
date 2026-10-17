package basic.searching.template2;

// Find Peak Element
// A peak element is an element that is strictly greater than its neighbors
// nums[i] != nums[i + 1], nums[-1] = nums[n] = -∞
// left = 0; right = nums.length - 1;
public class FindPeakElement {

    // nums = [1,2,1,3,5,6,4] -> index = 1, 5
    //
    // O(log(n))
    // O(1)
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] < nums[middle + 1]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        // 出循环条件: left=right当两个位置坐标相遇，
        // 说明left-1 < (left = right) > right-1，确定中间位置是Peak
        return left;
    }
}
