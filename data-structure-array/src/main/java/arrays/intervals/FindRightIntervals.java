package arrays.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// Find Right Interval
// You are given an array of intervals,
// where intervals[i] = [starti, endi] and each starti is unique.  -> 作为健值
// The right interval for an interval i is an interval j such that -> 符合二分查找
// - startj >= endi
// - startj is minimized
// - i may equal j
//
// Return an array of right interval indices for each interval i.
// If no right interval exists for interval i, then put -1 at index i.
//
// 1 <= intervals.length <= 2 * 10^4
// intervals[i].length == 2
// -10^6 <= starti <= endi <= 10^6
// The start point of each interval is unique.
public class FindRightIntervals {

    // TODO: 利用HashMap<>存储排序之前的映射Index坐标, 确定结果
    // [3,4],[2,3],[1,2] intervals
    //  0     1     2
    //
    // [1,2],[2,3],[3,4] sorted intervals
    // [1,    2,    3]   startIndexArray
    //   1     0    -1   result array
    //
    // [[1,4],[2,3],[3,4]] -> [-1,2,-1]
    //
    // O(N + N*logN + N*logN) 先初始化数据再遍历结果
    // O(2N + N)              存储Mapping和StartIndex数组
    public int[] findRightInterval(int[][] intervals) {
        // startIndex唯一数原始坐标 -> 原来index坐标
        int length = intervals.length;
        Map<Integer, Integer> mapStartiIndex = new HashMap<>();
        for (int index = 0; index < length; index++) {
            mapStartiIndex.put(intervals[index][0], index);
        }

        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        // 整合排序后startIndex数组(唯一坐标), 用于二分法查找
        int[] startIndexArray = new int[length];
        for (int index = 0; index < length; index++) {
            startIndexArray[index] = intervals[index][0];
        }

        int[] result = new int[length];
        for (int index = 0; index < length; index++) {
            // Log(N): If not found, return -(insertion point) - 1
            int startJ = intervals[index][1];
            int findPosition = Arrays.binarySearch(startIndexArray, index, length, startJ);
            if (findPosition < 0) {
                findPosition = -(findPosition + 1);
            }

            // 通过mapStartiIndex找回原始数组坐标并记录答案
            int startI = intervals[index][0];
            int originalIndex = mapStartiIndex.get(startI);
            if (findPosition < length) {
                int findIndex = startIndexArray[findPosition];
                result[originalIndex] = mapStartiIndex.get(findIndex);
            } else {
                // 如果插入坐标位置在最后, 说明没有找到startj
                result[originalIndex] = -1;
            }
        }
        return result;
    }
}
