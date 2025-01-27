package hard_questions.subarrays.ranges;

// TODO. 问题本质: 最大值子数组统计值 - 最小值子数组统计值 = Ranges子数组统计
//  Sum SubArray Max - Sum SubArray Min = Sum SubArray Range
// Sum of Subarray Ranges
// You are given an integer array nums.
// The range of a subarray of nums is the difference between largest and smallest element in subarray.
// Return the sum of all subarray ranges of nums.
//
// 1 <= nums.length <= 1000
// -10^9 <= nums[i] <= 10^9
//
// nums = [1,2,3] -> sum = 4
// [1,2], range = 2 - 1 = 1
// [2,3], range = 3 - 2 = 1
// [1,2,3], range = 3 - 1 = 2
//
// nums = [1,3,3] -> sum = 4
// nums = [4,-2,-3,4,1] -> sum = 59
public class SumSubarrayRanges {

    // TODO. 根据问题的约束条件，考虑暴力破解(执行常数次的循环)
    public static long subArrayRanges(int[] nums) {
        long sum = 0;
        int startIndex = 0;
        while (startIndex < nums.length - 1) {
            int min = nums[startIndex];
            int max = nums[startIndex];

            // 往后面迭代的同时，更新截取的SubArray区间的最值
            for (int right = startIndex + 1; right < nums.length; right++) {
                if (nums[right] > max) {
                    max = nums[right];
                } else if (nums[right] < min) {
                    min = nums[right];
                }
                sum+= max - min;
            }
            startIndex++;
        }
        return sum;
    }
}
