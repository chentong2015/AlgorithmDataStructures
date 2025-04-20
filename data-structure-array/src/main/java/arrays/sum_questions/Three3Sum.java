package arrays.sum_questions;

import java.util.*;

// 3Sum Question
// return true is there exists [nums[i], nums[j], nums[k]]
// Such that i!=j, i!=k, and j!=k, and nums[i]+nums[j]+nums[k] == 0
public class Three3Sum {

    // TODO. 3Sum = 2Sum + 1层循环, 只需要判断存在即可
    // [1,3,5,7], 8 -> true
    //
    // O(n*n)
    // O(n)
    public boolean canFindThreeValues(int[] array, int target) {
        if (array == null || array.length < 3) {
            return false;
        }
        int length = array.length;
        for (int index = 0; index < length; index++) {
            int remainingSum = target - array[index];
            if (findRemainingSum(array, remainingSum, index)) {
                return true;
            }
        }
        return false;
    }

    // TODO. 转换成2Sum问题，O(N)时间复杂度
    private boolean findRemainingSum(int[] array, int remainingSum, int indexExcluded) {
        Set<Integer> set = new HashSet<>();
        for (int index = 0; index < array.length; index++) {
            if (index == indexExcluded) {
                continue;
            }
            if (set.contains(remainingSum - array[index])) {
                return true;
            }
            set.add(array[index]);
        }
        return false;
    }
}
