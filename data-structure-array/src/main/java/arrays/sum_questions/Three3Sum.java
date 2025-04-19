package arrays.sum_questions;

import java.util.*;

// 3Sum Question
// return true is there exists [nums[i], nums[j], nums[k]]
// Such that i!=j, i!=k, and j!=k, and nums[i]+nums[j]+nums[k] == 0
//
// 数组中任意三个数的和能够形成的组合数复杂度为N^3种 = Cn3
public class Three3Sum {

    // TODO. 必须将三维问题降低成二维复杂度
    // [1,3,5,7], 8 -> true
    //
    // O(n*n)
    // O(n)
    private static boolean canFindThreeValues(int[] array, int target) {
        if (array == null || array.length < 3) {
            return false;
        }
        int length = array.length;
        for (int index = 0; index < length - 1; index++) {
            swapValueByIndex(array, index, length - 1);
            int remainingValue = target - array[index];
            if (findTargetValue(array, remainingValue, length - 1)) {
                return true;
            }
            swapValueByIndex(array, length - 1, index);
        }
        return false;
    }

    // 需要将遍历的index的位置和array末尾的值进行交换
    private static void swapValueByIndex(int[] array, int index1, int index2) {
        int tempValue = array[index1];
        array[index1] = array[index2];
        array[index2] = tempValue;
    }

    // TODO. 判断int类型数组中是否有两个数的和等于要找的目标值
    //  从数组中取任意的两个值求和，形成的组合数复杂度为N^2 = Cn2
    // Check if there exist sum of two values in an array is equal to the target value
    // Return true if found, otherwise return false.
    // [1, 6, 2, 6, 0, 7, 0], 12 -> true
    // [1, 6, 2, 6, 0, 7, 0], 15 -> false
    //
    // O(n) O(n) 空间换时间，一维的时间复杂度
    private static boolean findTargetValue(int[] array, int target, int length) {
        if (array == null || array.length < 2) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int index = 0; index < length; index++) {
            // 在遍历时，同时完成对于结果的判断
            if (set.contains(target - array[index])) {
                return true;
            }
            set.add(array[index]);
        }
        return false;
    }
}
