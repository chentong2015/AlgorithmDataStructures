package two_pointers;

// Two Sum II - Input array is sorted
// Given an array of integers numbers that is already sorted in non-decreasing order
// Find two numbers such that they add up to a specific target number
// Return the indices of the two numbers (1-indexed) as an integer array answer of size 2
public class TwoSumSorted {

    // TODO: 经典双坐标移动算法
    // numbers = [2,7,11,15], target = 9 -> [1,2]
    //
    // O(N)
    // O(1)
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }
            // 根据Sum值的大小判断移动方向
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        // 跳出循环的条件，左右左边重合
        return new int[0];
    }
}
