package subarrays;

// Sum of Subarray Minimums
// Given an array of integers arr, find the sum of min(b),
// where b ranges over every (contiguous) subarray of arr.
// Since the answer may be large, return the answer modulo 10^9 + 7.
//
// arr = [3,1,2,4] -> Output: 17
// Subarrays
// [3], [1], [2], [4],
// [3,1], [1,2], [2,4],
// [3,1,2], [1,2,4],
// [3,1,2,4].
// 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
//
// 1 <= arr.length <= 3 * 10^4
// 1 <= arr[i] <= 3 * 10^4
public class SumSubarrayMin {

    // TODO. 利用Monotonic Stack单调栈来存储遍历过程中有用的坐标
    // Input: 3  1  2  4
    // Stack:
    //          3  存储坐标: 用于计算Gap间隔
    //       2  2
    // 0  1  1  1
    //
    // O(n ~ n*n) 不应该在For循环的过程中反复循环Stack，没有利用栈中计算的结果
    // O(n)       栈中可能存储所有的坐标
    public static int sumSubarrayMins(int[] nums) {
        long sum = 0;
        int count = 0;
        int[] stack = new int[nums.length];

        for (int index=0; index < nums.length; index++) {
            // 先将Stack栈中更大值的index坐标移除
            while (count > 0 && nums[stack[count -1]] > nums[index]) {
                stack[count - 1] = 0;
                count--;
            }

            // 将新坐标押入栈中(最后栈顶的位置)
            count++;
            stack[count - 1] = index;

            // TODO. 根据栈中两个值之间“Gap间隔”来计算结果
            for (int right = count - 1; right > 0; right--) {
                int value = nums[stack[right]];
                int indexGap = stack[right] - stack[right -1];
                sum += (long) value * indexGap;
            }

            // TODO. 最后统计Stack栈底元素的统计
            int value = nums[stack[0]];
            sum += (long) value * (stack[0] + 1);
        }
        int mod = (int) 1e9 + 7;
        return (int) (sum % mod);
    }
}
