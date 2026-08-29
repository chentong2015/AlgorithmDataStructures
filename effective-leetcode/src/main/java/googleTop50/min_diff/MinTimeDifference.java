package googleTop50.min_diff;

import java.util.List;
import java.util.TreeSet;

// Minimum Time Difference
// Given a list of 24-hour clock time points in "HH:MM" format,
// return the minimum minutes difference between any two time-points in the list.
public class MinTimeDifference {

    // TODO. 遍历的同时插入和二分查找 + 环形结构(多添加一个存储周期)
    //
    // timePoints = ["23:59","00:00"] -> 1
    // timePoints = ["00:00","23:59","00:00"] -> 0
    //
    // O(N*logN*2)
    // O(N)
    public int findMinDifference(List<String> timePoints) {
        int minDiff = Integer.MAX_VALUE;
        TreeSet<Integer> sortedTree = new TreeSet<>();
        for(String timePoint: timePoints) {
            int hour = Integer.parseInt(timePoint.split(":")[0]);
            int minutes = Integer.parseInt(timePoint.split(":")[1]);
            int time = hour * 60 + minutes;

            if(!sortedTree.isEmpty()) {
                int diff = findTimeDiff(sortedTree, time);
                minDiff = Math.min(minDiff, diff);

                diff = findTimeDiff(sortedTree, time + 24 * 60);
                minDiff = Math.min(minDiff, diff);
                if (minDiff == 0) {
                    return 0;
                }
            }
            // TODO. 环形数据结构查找, 再添加一个时间周期
            sortedTree.add(time);
            sortedTree.add(time + 24 * 60);
        }
        return minDiff;
    }

    private int findTimeDiff(TreeSet<Integer> sortedTree, int time) {
        Integer timeBefore = sortedTree.floor(time);  // find the nearest value <= time
        Integer timeAfter = sortedTree.ceiling(time); // find the nearest value >= Time
        int diff = Integer.MAX_VALUE;
        if (timeBefore != null && timeAfter != null) {
            diff = Math.min(time - timeBefore, timeAfter - time);
        } else if (timeBefore == null && timeAfter != null) {
            diff = timeAfter - time;
        } else if (timeBefore != null) {
            diff = time - timeBefore;
        }
        return diff;
    }
}
