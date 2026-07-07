package arrays.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

// Merge Intervals
// Given an array of intervals where intervals[i] = [start, end], start <= end
public class MergeIntervals {

    // TODO. 必须先排序再合并共同区间, 并循环后续期间的起始位置
    // [[1,3],[2,6],[8,10],[15,18]]
    // [[1,6],[8,10],[15,18]]
    //
    // O(n*log(n)) n是区间的数量
    // O(n)        最差情况是没有任何合并，输出原始区间数目
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int resultCount = 0;
        int i = 0;
        while (i < intervals.length) {
            int startI = intervals[i][0];
            int endI = intervals[i][1];

            // 循环时考虑后续区间的包容
            int right = i + 1;
            while (right < intervals.length && endI >= intervals[right][0]) {
                // 需要判断是否有必要更新endI的值
                if (endI < intervals[right][1]) {
                    endI = intervals[right][1];
                }
                right++;
            }

            // 直接用原始数组存储结果值
            intervals[resultCount][0] = startI;
            intervals[resultCount][1] = endI;
            resultCount++;
            i = right;
        }

        // collect all result intervals
        int[][] resultIntervals = new int[resultCount][2];
        for (int index = 0; index < resultCount; index++) {
            resultIntervals[index][0] = intervals[index][0];
            resultIntervals[index][1] = intervals[index][1];
        }
        return resultIntervals;
    }
}
