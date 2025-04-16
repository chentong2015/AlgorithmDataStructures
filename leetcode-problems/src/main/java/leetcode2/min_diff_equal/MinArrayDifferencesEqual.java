package leetcode2.min_diff_equal;

// Minimum Array Changes to Make Differences Equal
//
// Replace any element in the array with any integer in the range from 0 to k.
// X such that abs(a[i] - a[n - i - 1]) = X for all (0 <= i < n).
// Return the minimum number of changes required to satisfy the above condition.
//
// n is even.
// 2 <= n == nums.length <= 10^5
// 0 <= nums[i] <= k <= 10^5
public class MinArrayDifferencesEqual {

    public static void main(String[] args) {
        int[] nums = {2,9,7,6,5,12,12,4, 4,6,9,0,1,8,7,3};
        System.out.println(minChanges(nums, 12));
    }

    // TODO. 算法本质: 最终的K值只能有k+1种可能，差值K不能高于k值
    // 使用[0,k]范围内值来替换任意位置的值，最后一定有解
    // 当两者的Diff=0时需要判断原始值和k的大小，确定改变的次数
    //
    // 1,0,1,2,4,3, k=4
    // 2 3 1
    //
    // 0,1,2,3,3,6,5,4, k=6
    // 4 4 4 0
    //
    // 5 5 1 2 5 5, k=4
    // 0 0 1
    //
    // 2,9,7,6,5,12,12,4, 4,6,9,0,1,8,7,3, k=12
    // 1 2 1 5 5 3  6  0
    public static int minChanges(int[] nums, int k) {
        int[] frequencyArray = new int[k + 1];
        int[] maxRange = new int[k + 1];

        for (int i = 0; i < nums.length / 2; i++) {
            int min = Math.min(nums[i], nums[nums.length - i - 1]);
            int max = Math.max(nums[i], nums[nums.length - i - 1]);

            int diff = max - min;
            frequencyArray[diff]++;
            maxRange[Math.max(k - max, min) + diff]++;
        }

        for (int i = 1; i < k + 1; i++) {
            maxRange[i] += maxRange[i - 1];
        }

        int result = Integer.MAX_VALUE;
        for (int i = k; i >= 0; i--) {
            if(frequencyArray[i] == 0) {
                continue;
            }
            if (i != 0) {
                result = Math.min(result, (2 * maxRange[i - 1]) + maxRange[k] - maxRange[i - 1] - frequencyArray[i]);
            } else {
                result = Math.min(result, maxRange[k] - frequencyArray[i]);
            }
        }
        return result;
    }
}
