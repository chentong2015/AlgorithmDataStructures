package interview_coding.byte_dance;

// Trapping Rain Water
// Given n non-negative integers representing an elevation map
// where the width of each bar is 1,
// compute how much water it can trap after raining.
//
// n == height.length
// 1 <= n <= 2 * 10^4
// 0 <= height[i] <= 10^5
public class ByteQuestion {

    // TODO. 储水量决定于左右"两个最大高度中的更小值"
    //  只有更小的那一侧需要计算并移动，在移动的过程中随时更新最大高度，避免往前的循环判断
    //   > 如果趋势是V字形，则计算差距储水量大小
    //   > 一旦有新高度出现，则重新以高的柱子为准
    //
    // O(N) O(1)
    public static int testSaveWater(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int sum = 0;
        int left = 0;
        int highestLeft = arr[left];
        int right = arr.length - 1;
        int highestRight = arr[right];

        while (left < right) {
            if (highestLeft > highestRight) {
                sum += highestRight - arr[right];
                right--;
                highestRight = Math.max(arr[right], highestRight);
            } else {
                sum += highestLeft - arr[left];
                left++;
                highestLeft = Math.max(arr[left], highestLeft);
            }
        }
        return sum;
    }
}
