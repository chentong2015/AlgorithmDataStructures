package live_coding.byte_dance;

// Trapping Rain Water
// Given n non-negative integers representing an elevation map
// where the width of each bar is 1,
// compute how much water it can trap after raining.
//
// n == height.length
// 1 <= n <= 2 * 10^4
// 0 <= height[i] <= 10^5
public class ByteQuestion {

    // TODO. 储水的本质: 它前后最大高度的柱子将它围起来所储水的大小
    // 储水的大小取决于较底一侧的高度
    // 哪一侧的高度更低则移动哪一侧坐标，移动后更新最大高度
    //
    // 0,1,0,2,1,0,1,3,2,1,2,1 -> 6
    // 2,5,3,7,6 -> 2
    //
    // O(N) O(1)
    public static int testSaveWater(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int sum = 0;
        int left = 0;
        int right = arr.length - 1;
        int highestLeft = arr[left];
        int highestRight = arr[right];
        while (left < right) {
            if (highestLeft > highestRight) {
                sum += highestRight - arr[right]; // 计算低位差值
                right--;
                highestRight = Math.max(arr[right], highestRight);
            } else {
                sum += highestLeft - arr[left]; // 仅在移动时计算
                left++;
                highestLeft = Math.max(arr[left], highestLeft);
            }
        }
        return sum;
    }
}
