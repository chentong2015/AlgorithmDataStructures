package amazonTop50;

// Minimum Adjacent Swaps to Make a Valid Array (Locked)
// You are given a 0-indexed integer array nums.
// Swaps of adjacent elements are able to performed on nums.
// A valid array meets the following conditions:
// - The largest element (any of the largest elements if there are multiple)
//   is at the rightmost position in the array.
// - The smallest element (any of the smallest elements if there are multiple)
//   is at the leftmost position in the array.
// Return the minimum swaps required to make nums a valid array
//
// 1 <= nums.length <= 10^5
// 1 <= nums[i] <= 10^5
public class MinAdjacentSwapsArray {

    // TODO. 直接根据移动的index位置计算，注意移动交叉
    // [3,4,5,5,3,1]
    // - Swap 1: Swap the 3rd and 4th elements, nums is then [3,4,5,3,5,1].
    // - Swap 2: Swap the 4th and 5th elements, nums is then [3,4,5,3,1,5].
    // - Swap 3: Swap the 3rd and 4th elements, nums is then [3,4,5,1,3,5].
    // - Swap 4: Swap the 2nd and 3rd elements, nums is then [3,4,1,5,3,5].
    // - Swap 5: Swap the 1st and 2nd elements, nums is then [3,1,4,5,3,5].
    // - Swap 6: Swap the 0th and 1st elements, nums is then [1,3,4,5,3,5].
    // It can be shown that 6 swaps is the minimum swaps required to make a valid array
    //
    // 0,,,,,min,,,,max,,,n-1
    // 0,,,,,max,,,,min,,,n-1
    //
    // O(N+N)
    // O(1)
    public int minSwaps(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int n = nums.length;
        int minIdLeftmost = findMinIndexLeftmost(nums);
        int maxIdRightmost = findMaxIndexRightmost(nums);

        // 特殊的边缘场景
        if (minIdLeftmost == 0) {
            return (n - 1) - maxIdRightmost;
        }
        if (maxIdRightmost == n - 1) {
            return minIdLeftmost;
        }

        // 如果移动有交叉，则总交换步数减1
        int swapToRight = (n - 1) - maxIdRightmost;
        int swapToLeft = minIdLeftmost < maxIdRightmost ? minIdLeftmost : minIdLeftmost - 1;
        return swapToLeft + swapToRight;
    }

    private int findMinIndexLeftmost(int[] nums) {
        int minIndex = 0;
        for (int index = 1; index < nums.length; index++) {
            if (nums[index] < nums[minIndex]) {
                minIndex = index;
            }
        }
        return minIndex;
    }

    private int findMaxIndexRightmost(int[] nums) {
        int n = nums.length;
        int maxIndex = n - 1;
        for (int index = n - 2; index >= 0; index--) {
            if (nums[index] > nums[maxIndex]) {
                maxIndex = index;
            }
        }
        return maxIndex;
    }
}
