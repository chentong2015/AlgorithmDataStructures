package stack_monotonic;

import java.util.Stack;

// 132 Pattern
// 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k]
// i < j < k and nums[i] < nums[k] < nums[j]
// Return true if there is a 132 pattern in nums, otherwise return false
//
// n == nums.length
// 1 <= n <= 2 * 105
// -10^9 <= nums[i] <= 10^9
public class Subsequence132Pattern {

    // TODO. 利用Stack存储右侧单调递增的数据
    // nums = [1,2,3,4]  -> false
    // nums = [3,1,4,2]  -> true
    // nums = [-1,3,2,0] -> true
    //
    // 8 12 9 10 8 7 6
    //
    // O(N) O(N)
    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int third = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            int first = nums[i];
            if (first < third) {
                return true;
            }

            while (!stack.isEmpty() && first > stack.peek()) {
                third = stack.pop(); // 使用保存second数据后面的最大值
            }
            stack.push(first); // 新存储second的值, 比third值大
        }
        return false;
    }

    // TODO. 直接利用原始数组当作Stack栈存储, 降低空间复杂度
    // 8 12 9 10 8 7   6
    //                 6j   kValue=min
    //                 7j   kValue=6   当设置kValue的值的时候，说明最终条件成立了一半nums[k] < nums[j]
    //                 8j   kValue=7
    //                 10j  kValue=8   对于10j而言，它后面小于它的且最大的值是8
    //             9j  10   kValue=8   9正好位于8~10之间，所以直接添加在前面
    //                                 同时说明9比10后面的值都要大，并不构成132Pattern
    //             9   12j  kValue=10  对于12j而言，它后面小于它的且最大的值是10
    //                                 需要找到比12小的后面的最大值，使得132出现的概率最大
    //             8<10<12  条件成立
    //
    // O(n+n) 极限情况下内层的while循环会执行(n-1)次
    // O(1)
    public boolean find132PatternNNNN(int[] nums) {
        int secondIndex = nums.length;    // 3最大值的位置
        int thirdMax = Integer.MIN_VALUE; // 3后面的最大的2值

        for (int index = nums.length - 1; index >= 0; index--) {
            int iValue = nums[index]; // 读取新的1值
            if (iValue < thirdMax) {
                return true;
            }

            // 找3后面的小于它的最大2
            while (secondIndex < nums.length && nums[secondIndex] < iValue) {
                thirdMax = nums[secondIndex];
                secondIndex++;
            }

            // 将读取的1转换成3, 当成最大值存储
            secondIndex--;
            nums[secondIndex] = iValue;
        }
        return false;
    }
}
