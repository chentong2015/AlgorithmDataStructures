package array;

// Largest Number At Least Twice of Others
// An integer array nums where the largest integer is unique
// Whether the largest element in the array is at least twice as much
// as every other number in the array
// If it is, return the index of the largest element, or return -1 otherwise
public class LargestNumTwiceOthers {

    // TODO. 比较最大和第二大值
    // nums = [3,6,1,0] -> index = 1 判断最大的值，是否是其他任意值的两倍以上
    // nums = [1]       -> index = 0 1看做比其他的值(没有值)都大2倍以上
    // O(n) O(1)
    public int dominantIndex(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int foundIndex = 0;
        int max = nums[0];
        int secondMax = nums[0];
        for (int index = 1; index < nums.length; index++) {
            if (nums[index] > max) {
                if (max > secondMax) secondMax = max;        // 注意更新第二大的值
                max = nums[index];
                foundIndex = index;
            } else {
                if (index == 1 || nums[index] > secondMax) { // 将第二个值赋值给secondIndex, 分开两个最大值
                    secondMax = nums[index];
                }
            }
        }
        return max >= 2 * secondMax ? foundIndex : -1;
    }
}
