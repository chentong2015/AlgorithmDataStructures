package arrays.array.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// Find Right Interval
// You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
// The right interval for an interval i is an interval j such that
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

    // TODO: Start Index唯一: 可以作为Key + 可以用于二分查找 ！
    // [[3,4],[2,3],[1,2]] intervals
    //    0     1     2
    // [1,2],[2,3],[3,4] sorted intervals
    // [1,    2,    3]   startIndexArray
    //   1     0    -1   result array
    //
    // [[1,4],[2,3],[3,4]] -> [-1,2,-1]
    //
    // O(N + N*logN + N*logN) 先初始化数据再遍历结果
    // O(2N + N)  存储Mapping和StartIndex数组
    public int[] findRightInterval(int[][] intervals) {
        int length = intervals.length;

        // 存储startIndex数值的原始坐标位置
        Map<Integer, Integer> mapStartIndex = new HashMap<>();
        for (int index = 0; index < length; index++) {
            mapStartIndex.put(intervals[index][0], index);
        }

        // 根据数组第一个元素进行排序
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        // TODO. 用于二分法查找，降低时间复杂度
        int[] startIndexArray = new int[length];
        for (int index = 0; index < length; index++) {
            startIndexArray[index] = intervals[index][0];
        }

        // Compute the result values
        int[] result = new int[length];
        for (int index = 0; index < length; index++) {
            // 找到当前starti的原始数组坐标
            int startValue = intervals[index][0];
            int startPosition = mapStartIndex.get(startValue);

            int endValue = intervals[index][1];
            int findPosition = Arrays.binarySearch(startIndexArray, index, length, endValue);

            // If not found, return -(insertion point) - 1
            if (findPosition < 0) {
                findPosition = -(findPosition + 1);
            }
            if (findPosition < length) {
                // 找到startj的原始数组坐标
                int findPositionValue = startIndexArray[findPosition];
                result[startPosition] = mapStartIndex.get(findPositionValue);
            } else {
                // 如果插入的坐标位置是在最后, 说明没有找到startj
                result[startPosition] = -1;
            }
        }
        return result;
    }
}
