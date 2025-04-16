package master.skipline_problem;

import java.util.*;

// The Skyline Problem
// Given in the array buildings where buildings[i] = [lefti, righti, heighti]:
// - lefti is the x coordinate of the left edge of the ith building.
// - righti is the x coordinate of the right edge of the ith building.
// - heighti is the height of the ith building.
// all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
//
// 1 <= buildings.length <= 10^4
// 0 <= lefti < righti <= 2^31 - 1
// 1 <= heighti <= 2^31 - 1
// buildings is sorted by lefti in non-decreasing order.
public class TheSkylineProblem {

    // TODO. 将每栋建筑的Border边缘扣出来，根据树立的Border来判断高度变化
    // buildings = [[0,2,3],[2,5,3]]
    // Output: [[0,3],[5,0]]
    //
    // buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
    // Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
    //
    //
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // This array as bars representing borders of buildings
        int[][] heights = new int[2 * buildings.length][2];
        for (int i = 0; i < buildings.length; i++) {
            heights[i * 2] = new int[]{ buildings[i][0], -buildings[i][2] };    // left, -height
            heights[i * 2 + 1] = new int[]{ buildings[i][1], buildings[i][2] }; // right, height
        }

        // Sort the bars based on position, use height as tie-breaker
        Arrays.sort(heights, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        // TODO. priority queue is removal of given item other than root can be costly O(n)
        // height -> count
        var map = new TreeMap<Integer, Integer>(Comparator.reverseOrder());
        map.put(0, 1);

        List<List<Integer>> res = new ArrayList<>();
        int prev = 0;
        for (int[] height : heights) {
            // -ve height for left bar meaning validity of this building is starting, add it
            if (height[1] < 0) {
                map.put(-height[1], map.getOrDefault(-height[1], 0) + 1);
            } else {
                // +ve height for right bar meaning end of validity, remove it
                map.put(height[1], map.get(height[1]) - 1);
                // could have been costly for priority queue
                if (map.get(height[1]) == 0) {
                    map.remove(height[1]);
                }
            }

            // max height in curr position that is diff from prev, add to skyline
            if (map.firstKey() != prev) {
                prev = map.firstKey();
                res.add(List.of(height[0], prev));
            }
        }
        return res;
    }
}
