package array.duplicate;

import java.util.ArrayList;
import java.util.List;

// Find All Duplicates in an Array
// All the integers of nums are in the range [1, n] and each integer appears once or twice
// Return an array of all the integers that appears twice
//
// n == nums.length
// 1 <= nums[i] <= n
// Each element in nums appears once or twice.
public class FindAllDuplicates {

    // TODO: 将数据存储的值对应到Index坐标，判断是否出现过
    //
    // [4,3,2,7,8,2,3,1] -> [2,3]
    // [4,3,2,7,8,2,3,1]
    // [4,3,2,-7,8,2,3,1]  标记负数表示能够通过某个位置上的值"定位找到"这个位置上面来
    // [4,3,-2,7,8,2,3,1]  如果之前已经"被找到过"，那个这个数字就是第二次出现
    //
    // O(N)
    // O(1)
    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int index = 0; index < nums.length; index++) {
            int findIndex = Math.abs(nums[index]) - 1; // 计算对应坐标
            if (nums[findIndex] < 0) {
                result.add(findIndex + 1);
            }
            nums[findIndex] = -nums[findIndex]; // 标记出现过
        }
        return result;
    }
}