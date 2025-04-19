package arrays.sorting_questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Minimum Absolute Difference
// Given an array of distinct integers' arr, 提供的数组中没有相同的值, 数据是乱序排列的
// find all pairs of elements with the minimum absolute difference of any two elements.
// 2 <= arr.length <= 105 注意检查特殊条件
public class MinAbsoluteDifference {

    // TODO. 排序后的数据相邻位置的间隔值一定小于间隔位置的差值 !!
    //  最小的绝对值差值一定出现在排序后的两个相邻值之间
    // Input: arr = [4,2,1,3]     --> Output: [[1,2],[2,3],[3,4]]
    // Input: arr = [1,3,6,10,15] --> Output: [[1,3]]
    //
    // O(n*logn+n+n)
    // O(n)
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(arr);

        int minDiff = Integer.MAX_VALUE;
        for (int index = 1; index < arr.length; index++) {
            int tempValue = arr[index] - arr[index - 1];
            if (tempValue < minDiff) {
                minDiff = tempValue;
                // 在找到最小间隔值时，清除掉之前存储的数据再重新累计
                resultList.clear();
                resultList.add(List.of(arr[index - 1], arr[index]));
            } else if (tempValue == minDiff) {
                // 将相同的最小间隔值在一次遍历时顺便插入 !!
                resultList.add(List.of(arr[index - 1], arr[index]));
            }
        }
        return resultList;
    }
}
