package basic.math;

import java.util.HashMap;

// Max Points on a Line
// Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane,
// return the maximum number of points that lie on the same straight line.
//
// 1 <= points.length <= 300
// All the points are unique.
public class MaxPointsOnLine {

    // TODO. (y2-y1)/(x2-x1)=Slope 斜率存储成Double高精读值
    //
    //           [3,3]
    //      [2,2]
    // [1,1]
    //
    //
    // [1,4]
    //      [2,3]          [5,3]
    //           [3,2]
    // [1,1]          [4,1]
    //
    //
    public int maxPoints(int[][] points) {
        if(points.length <=2) {
            return points.length;
        }

        int max = 0;
        for(int i = 0; i< points.length; i++){
            HashMap<Double, Integer> slopeCountMap = new HashMap<>();
            for(int j = i+1; j<points.length; j++){
                double slope = calculateSlope(points, i, j);
                int baseCount = slopeCountMap.getOrDefault(slope, 0);
                slopeCountMap.put(slope, baseCount + 1);
            }
            for(int value: slopeCountMap.values()) {
                max = Math.max(max, value+1);
            }
        }
        return max;
    }

    // 以Double值作为斜率Key键值，处理成字符串很难约分 !!
    // - 考虑除数为0的情况
    // - 考虑-0和+0的一致性
    private double calculateSlope(int[][] points, int i, int j) {
        int x1 = points[i][0];
        int y1 = points[i][1];
        int x2 = points[j][0];
        int y2 = points[j][1];
        if(x1 == x2) {
            return Double.POSITIVE_INFINITY;
        }

        double slope = (double)(y1-y2)/(x2-x1);
        if(slope == -0.0) {
            slope = 0.0;
        }
        return slope;
    }
}