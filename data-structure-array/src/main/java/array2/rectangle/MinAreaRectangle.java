package array2.rectangle;

import java.util.HashSet;
import java.util.Set;

// Minimum Area Rectangle
// You are given an array of points in the X-Y plane points where points[i] = [xi, yi].
// Return the minimum area of a rectangle formed from these points,
// with sides parallel to the X and Y axes.
// If there is not any such rectangle, return 0.
//
// 1 <= points.length <= 500
// points[i].length == 2
// 0 <= xi, yi <= 4 * 10^4
// All the given points are unique.
public class MinAreaRectangle {

    // TODO. Geometry几何学问题: 长方形的四个坐标由两个坐标确定
    // [x1,y2]     [x2,y2]
    //       [x3,y3]
    // [x1,y1]     [x2,y1]
    //
    // [[1,1],[1,3],[3,1],[3,3],[2,2]] -> 4
    // [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]] -> 2
    //
    // O(N*N) 降维度的时间复杂度
    // O(N)
    public int minAreaRect(int[][] points) {
        Set<String> pointSet = new HashSet<>();
        for (int[] point : points) {
            pointSet.add(point[0] + "," + point[1]);
        }

        int minArea = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                // 遍历所有bottom-left和top-right坐标的组合
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];

                // TODO. 根据两个坐标来验证Rectangle四个坐标
                if (x1 != x2 && y1 != y2) {
                    if (pointSet.contains(x1 + "," + y2) && pointSet.contains(x2 + "," + y1)) {
                        // 由于不确定大小，计算必须取绝对值
                        int area = Math.abs(x2 - x1) * Math.abs(y2 - y1);
                        minArea = Math.min(minArea, area);
                    }
                }
            }
        }
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
}
