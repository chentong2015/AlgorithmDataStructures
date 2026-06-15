package leetcode5;

// Ways to Make a Fair Array
// You are given an integer array nums.
// You can choose exactly one index (0-indexed) and remove the element.
// Notice that the index of the elements may change after the removal.
//
// An array is fair if the sum of the odd-indexed values equals the sum of the even-indexed values.
// Return the number of indices that you could choose such that after the removal, nums is fair.
//
// 1 <= nums.length <= 10^5
// 1 <= nums[i] <= 10^4
public class WaysFairArray {

    // TODO. 必须对数据预处理:
    //  存储index位置前后的偶数和/奇数和, 反转后面奇偶和
    //
    // 2 1 6 4 -> 1
    //
    // 0 2 2 8  EvenBefore 偶数和
    // 0 0 1 1  OldBefore  奇数和
    // -
    // 6 6 0 0  EvenAfter
    // 5 4 4 0  OldAfter
    //
    // O(N+N+N) 总共循环三次
    // O(2N+2N) 额外开辟的空间
    public int waysToMakeFair(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }

        int[] sumEvenBefore = new int[nums.length];
        int[] sumOddBefore = new int[nums.length];
        int sumEven = 0;
        int sumOdd = 0;
        for (int index = 0; index < nums.length; index++) {
            sumEvenBefore[index] = sumEven;
            sumOddBefore[index] = sumOdd;
            if (index % 2 == 0) {
                sumEven += nums[index];
            } else {
                sumOdd += nums[index];
            }
        }

        int[] sumEvenAfter = new int[nums.length];
        int[] sumOddAfter = new int[nums.length];
        sumEven = 0;
        sumOdd = 0;
        for (int index = nums.length - 1; index >= 0; index--) {
            sumEvenAfter[index] = sumEven;
            sumOddAfter[index] = sumOdd;
            if (index % 2 == 0) {
                sumEven += nums[index];
            } else {
                sumOdd += nums[index];
            }
        }

        // 统计的时候后续的奇偶会发生变化
        int count = 0;
        for (int index = 0; index < nums.length; index++) {
            if (sumEvenBefore[index] + sumOddAfter[index] == sumOddBefore[index] + sumEvenAfter[index]) {
                count++;
            }
        }
        return count;
    }
}
