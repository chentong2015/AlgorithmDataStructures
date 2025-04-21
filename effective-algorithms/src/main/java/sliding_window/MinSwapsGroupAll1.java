package sliding_window;

// Minimum Swaps to Group All 1's Together II
// A swap is defined as taking two distinct positions in an array and swapping the values in them.
// A circular array is defined as an array where we consider
// the first element and the last element to be adjacent.
//
// Given a binary circular array nums, return the minimum number of swaps required
// to group all 1's present in the array together at any location.
//
// 1 <= nums.length <= 10^5
// nums[i] is either 0 or 1.
public class MinSwapsGroupAll1 {

    // TODO. 无需考虑移动过程，1的个数就是Sliding Window区间大小
    // nums = [0,1,0,1,1,0,0]
    // [0,0,1,1,1,0,0] using 1 swap.
    // [0,1,1,1,0,0,0] using 1 swap.
    //
    // nums = [0,1,1,1,0,0,1,1,0]
    // [1,1,1,0,0,0,0,1,1] using 2 swaps (using circular array).
    // [1,1,1,1,1,0,0,0,0] using 2 swaps.
    //
    // nums = [1,1,0,0,1] -> 0
    //
    // 滑动窗口去框出最优的位置区间
    // 1 0 1 0 0 1 0 0 0 '1 1 1 0 1 0 1' 0 0 1
    //
    // O(n)
    // O(1)
    public int minSwaps(int[] nums) {
        int window = 0;
        for (int num: nums) {
            if (num == 1) {
                window++;
            }
        }

        // 初始化第一个窗口的统计(移动的数目)
        int countOne = 0;
        for(int i = 0; i < window; i++){
            if(nums[i] == 1) {
                countOne++;
            }
        }
        int result = window - countOne;

        // TODO. 坐标只需要往后移动length的长度即可
        for(int i = window; i < window + nums.length; i++){
            if(nums[i - window] == 1) {
                countOne--;
            }
            if(nums[i % nums.length] == 1) {
                countOne++;
            }
            result = Math.min(result, window - countOne);
        }
        return result;
    }
}
