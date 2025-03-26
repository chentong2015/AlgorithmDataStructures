package arrays.increments.continuously_numbers;

import java.util.HashSet;

// Longest Continuous Increasing Numbers
//
// 无法根据数据的范围使用常量的数组进行存储
// 0 < nums.length < 10^5
// -10^9 < nums[i] < 10^9
public class ContinuousIncreasingNumbers {

    public static void main(String[] args) {
        int[] nums = {4,2,7,8,1,5,6,0};
        ContinuousIncreasingNumbers instance = new ContinuousIncreasingNumbers();
        System.out.println(instance.findContinuousIncreasingLength(nums));
    }

    // TODO. HashSet<>集合 + 数据扩散, 以O(1)的复杂度判断数据是否存在
    // [100,4,200,1,3,2］-> 4
    // -> [1,2,3,4]
    //
    // [4,2,7,8,1,5,6,0] -> 5
    // -> [4,5,6,7,8]
    //
    // O(N) 必须保证时间内复杂度
    // O(N) 以空间换取时间
    public int findContinuousIncreasingLength(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int result = 0;
        while (!set.isEmpty()) {
            for (int num : set) {
                int length = 1;

                int left = num - 1;
                while (set.contains(left)) {
                    length++;
                    set.remove(left);
                    left--;
                }

                int right = num + 1;
                while (set.contains(right)) {
                    length++;
                    set.remove(right);
                    right++;
                }

                set.remove(num);
                result = Math.max(result, length);

                // 每当处理完一个数字的连续区间后，必须跳出
                // 否则for无法继续循环已经被删除的item数据
                break;
            }
        }
        return result;
    }
}
