package array.rotate_array;

// Rotate Array
// Rotate the array to the right by k steps, where k is non-negative.
// Could you do it in-place with O(1) extra space ?
//
// 1 <= nums.length <= 10^5
// -2^31 <= nums[i] <= 2^31 - 1
// 0 <= k <= 10^5
public class RotateArray {

    // TODO. 直接在原数组操作，分段Reverse反转三次
    // nums = [1,2,3,4,5,6,7], k = 3
    // -> [5,6,7,1,2,3,4]
    //
    // nums = [-1,-100,3,99], k = 2
    // -> [3,99,-1,-100]
    //
    // TODO. 探索最终结果数据的生成，将算法图形化
    // [1, 2, 3, 4, 5, 6, 7]
    // [7, 6, 5, 4, 3, 2, 1]  整体反转
    // [5, 6, 7,  4, 3, 2, 1] 反转第一部分
    // [5, 6, 7,  1, 2, 3, 4] 反转第二部分
    //
    // O(n) O(1)
    public void rotateArray(int[] nums, int k) {
        if (nums.length > 1) {
            k = k % nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
