package arrays.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

// Merge Intervals
// Given an array of intervals where intervals[i] = [start, end], start <= end
public class MergeIntervals {

    // TODO. 一定需要排序再考虑合并共同区间
    // [[1,3],[2,6],[8,10],[15,18]]
    // [[1,6],[8,10],[15,18]]
    //
    // O(n*log(n)) n是区间的数量
    // O(n)        最差情况是没有任何合并，输出原始区间数目
    public int[][] merge(int[][] intervals) {
        // sort all intervals by start value
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int resultIntervalCount = 0;
        int i = 0;
        while (i < intervals.length) {
            int startI = intervals[i][0];
            int endI = intervals[i][1];

            // while循环时考虑后续区间的包容
            int nextI = i + 1;
            while (nextI < intervals.length && endI >= intervals[nextI][0]) {
                if (endI < intervals[nextI][1]) {
                    endI = intervals[nextI][1];
                }
                nextI++;
            }

            // use the original array to save result
            intervals[resultIntervalCount][0] = startI;
            intervals[resultIntervalCount][1] = endI;
            resultIntervalCount++;

            i = nextI;
        }

        // collect all result intervals
        int[][] resultIntervals = new int[resultIntervalCount][2];
        for (int index = 0; index < resultIntervalCount; index++) {
            resultIntervals[index][0] = intervals[index][0];
            resultIntervals[index][1] = intervals[index][1];
        }
        return resultIntervals;
    }
}
