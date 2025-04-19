package arrays.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

// Merge Intervals
// Given an array of intervals where intervals[i] = [start, end], start <= end
public class MergeIntervals {

    // [[1,3],[2,6],[8,10],[15,18]]
    // [[1,6],[8,10],[15,18]]
    //
    // O(n*log(n)) n是区间的数量
    // O(n)        最差情况是没有任何合并，输出原始区间数目
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
                continue;
            }
            // TODO. 需要更新最后区间的上边界，等效于合并
            merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
