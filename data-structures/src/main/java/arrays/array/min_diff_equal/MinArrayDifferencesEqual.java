package arrays.array.min_diff_equal;

// Minimum Array Changes to Make Differences Equal
//
// replace any element in the array with any integer in the range from 0 to k.
// X such that abs(a[i] - a[n - i - 1]) = X for all (0 <= i < n).
// Return the minimum number of changes required to satisfy the above condition.
//
// n is even.
// 2 <= n == nums.length <= 10^5
// 0 <= nums[i] <= k <= 10^5
public class MinArrayDifferencesEqual {

    // TODO. 算法的本质是找出“差异值”的最大频率，其余的差异值都往这个值靠拢
    //
    // 使用[0,k]范围内的值来替换任意位置值，k值和统计的Min次数有什么关系
    //
    // [1,0,1,2,4,3], k = 4
    //  2 3 1
    // [1,2,1,2,4,3]
    // [1,2,1,3,4,3]  X=2
    //
    // [0,1,2,3,3,6,5,4], k = 6
    //  4 4 4 0
    // [0,1,2,0,3,6,5,4]
    // [0,1,2,0,4,6,5,4]  X=4
    //
    // 0 0 1 2 5 5, k = 4
    // 5 5 1
    // 0 4 1 2 5 5
    // 4 4 1 2 5 5
    //
    // 0 0 5 0 5 5, k = 4
    // 5 5 5
    public int minChanges(int[] nums, int k) {

        return 0;
    }
}
