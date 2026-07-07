package arrays.intervals;

import java.util.ArrayList;
import java.util.List;

// Insert Interval
// ou are given an array of non-overlapping intervals
// where intervals[i] = [starti, endi] represent the start and the end of the ith interval
// and intervals is sorted in ascending order by starti.
//
// You are also given an interval newInterval = [start, end]
// that represents the start and end of another interval.
//
// Insert newInterval into intervals such that intervals is still sorted in ascending order
// by starti and intervals still does not have any overlapping intervals
// (merge overlapping intervals if necessary).
public class InsertInterval {

    // TODO. 1. 先整合成新的数组再交给merge()方法合并
    // intervals = [[1,3],[6,9]], newInterval = [2,5]
    // result -> [[1,5],[6,9]]
    //
    // intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
    // result -> [[1,2],[3,10],[12,16]]
    //
    // O(N) 构建有序的中间数组
    // O(N) 中间临时数组
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int length = intervals.length;
        int[][] tempIntervals = new int[length + 1][2];
        int tempIndex = 0;
        boolean hasUsedNewInterval = false;

        for (int[] interval : intervals) {
            if (interval[0] >= newInterval[0] && !hasUsedNewInterval) {
                tempIntervals[tempIndex][0] = newInterval[0];
                tempIntervals[tempIndex][1] = newInterval[1];
                tempIndex++;
                hasUsedNewInterval = true;
            }
            tempIntervals[tempIndex][0] = interval[0];
            tempIntervals[tempIndex][1] = interval[1];
            tempIndex++;
        }

        if (!hasUsedNewInterval) {
            tempIntervals[tempIndex][0] = newInterval[0];
            tempIntervals[tempIndex][1] = newInterval[1];
        }
        // TODO. 传递的区间已经排好序, 无需再Sort
        return MergeIntervals.merge(tempIntervals);
    }

    // TODO. 2. 直接遍历每一个区间, 动态扩展区间范围
    // O(N)
    // O(N)
    public int[][] insertPlus(int[][] intervals, int[] newInterval) {
        boolean inserted = false;
        List<int[]> newIntervals = new ArrayList<>();
        for(int[] interval: intervals){
            if (interval[1] < newInterval[0]) { // 没有交集(插在后面)则直接添加
                newIntervals.add(interval);
            } else if (newInterval[1] < interval[0]) { // 没有交集(插在前面)直接添加
                if (!inserted) {
                    newIntervals.add(newInterval);
                    inserted = true;
                }
                newIntervals.add(interval);
            } else {
                // TODO. 有区间重叠则往两侧扩展，则扩充新区间范围
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }
        if(!inserted) {
            newIntervals.add(newInterval);
        }
        return newIntervals.toArray(new int[newIntervals.size()][]);
    }
}
