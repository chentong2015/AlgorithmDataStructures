package collections.stack.next_permutation;

import java.util.Arrays;

// Next Permutation
// The next permutation of an array of integers is
// the next lexicographically greater permutation of its integer.
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 100
public class NextPermutation {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        NextPermutation instance = new NextPermutation();
        instance.nextPermutation(nums);

        for (int num: nums) {
            System.out.println(num);
        }
    }

    // TODO. 没有更大的值则从头开始, 等效于排序, 但首数字不能为0
    // nums = [1,2,3] -> [1,3,2].
    // nums = [1,1,5] -> [1,5,1].
    // nums = [3,2,1] -> [1,2,3]
    //
    // 4 2 5 3 -> 4 3 2 5
    //
    // 3
    // 3 5
    // 2 5 使用二分查询找到后续第一比它大的数据，反之往后扩容
    public void nextPermutation(int[] nums) {
        int middle = -1;
        int[] temp = new int[nums.length];
        int length = 0;
        for (int index = nums.length - 1; index >= 0; index--) {
            if (length == 0) {
                temp[length] = nums[index];
                length++;
                continue;
            }

            // TODO. 这里需要使用List<E>来做二分法查询
            // 二分法查询后往后扩容
            int findIndex = Arrays.binarySearch(temp, 0, length, nums[index]);
            if (findIndex < 0) {
                findIndex = -(findIndex + 1);
            }
            if (findIndex == length) {
                temp[length] = nums[index];
                length++;
                continue;
            }

            // 找到后面最小的更大值值，交换位置
            middle = index - 1;
            int nextGreaterValue = temp[findIndex];
            temp[findIndex] = nums[index];
            nums[index] = nextGreaterValue;
            break;
        }

        // 将剩余的后续数据进行排序，保证最小
        Arrays.sort(temp);

        // 将两部分的数据进行组合
        for (int index = 0; index < length; index++) {
            nums[middle + 1 + index] = temp[index];
        }
    }
}
