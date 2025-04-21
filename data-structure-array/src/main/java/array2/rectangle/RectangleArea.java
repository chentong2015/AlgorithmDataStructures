package array2.rectangle;

// Rectangle Area
// Given the coordinates of two rectilinear rectangles in a 2D plane,
// return the total area covered by the two rectangles.
//
// The first rectangle is defined by its bottom-left corner (ax1, ay1)
// and its top-right corner (ax2, ay2).
// The second rectangle is defined by its bottom-left corner (bx1, by1)
// and its top-right corner (bx2, by2).
//
// -10^4 <= ax1 <= ax2 <= 10^4
// -10^4 <= ay1 <= ay2 <= 10^4
// -10^4 <= bx1 <= bx2 <= 10^4
// -10^4 <= by1 <= by2 <= 10^4
public class RectangleArea {

    // TODO. (X,Y)二维坐标的交集计算
    // (-3,0) - (3,4)
    //    (0,-1) - (9,2)
    //
    // O(1)
    // O(1)
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);

        // TODO. 通用交集计算公式: (MinX2 - MaxX1) * (MinY2 - MaxY1)
        int cx1 = Math.max(ax1, bx1);
        int cx2 = Math.min(ax2, bx2);
        int cy1 = Math.max(ay1, by1);
        int cy2 = Math.min(ay2, by2);
        int intersection = Math.max(0, cx2 - cx1) * Math.max(0, cy2 - cy1);
        return area1 + area2 - intersection;
    }
}
