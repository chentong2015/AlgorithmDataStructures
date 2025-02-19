package templates.dp_programming.subsequence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ContinuousRightIntervals {

    // TODO: 本质上是找到最近的连续上升区间(梯度)，排序之后如何体现出来 !! O(nlog(n)) O(n)
    // Find Right Interval
    // Array of intervals, where intervals[i] = [starti, endi] and each starti is unique 这里这左位置是唯一的，可以作为key值 !!
    // The right interval for an interval i is an interval j such that endi <= startj and startj is minimized
    // Return an array of right interval indices for each interval i
    // If no right interval exists for interval i, then put -1 at index i
    // intervals = [[3,4],[2,3],[1,2]] -> [-1,0,1]
    // 找到每个index位置最小右区间的坐标
    public int[] findRightInterval(int[][] intervals) {
        int length = intervals.length;
        int[] starts = new int[length];
        Map<Integer, Integer> mapStartToIndex = new HashMap<>();
        for (int i = 0; i < length; i++) {
            starts[i] = intervals[i][0];
            mapStartToIndex.put(intervals[i][0], i);
        }
        Arrays.sort(starts);                 // 先将区间左的所有左位置进行排序, 然后在排序好的数组中找每个区间右位置的(index)位置
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            int index = Arrays.binarySearch(starts, intervals[i][1]);
            if (index >= 0) {               // 在准确的找到右坐标的情况下，用HashMap来查找左位置所对应的原始index坐标
                result[i] = mapStartToIndex.get(starts[index]);
            } else {
                index = -index - 1;
                if (index < length) {
                    result[i] = mapStartToIndex.get(starts[index]);
                } else {
                    result[i] = -1;
                }
            }
        }
        return result;
    }
}
