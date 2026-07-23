package live_coding.google.top50;

// Maximum Number of Points with Cost
public class MaxNumPointsWithCost {

    public long maxPoints(int[][] points) {
        long maxScore = 0; // init by min value
        int middleCol = (points[0].length - 1) / 2 ;
        for (int row = 0; row < points.length; row++) {
            int maxValueCol = points[row][middleCol];
            for(int col = 0; col < points[0].length; col++) {
                // move each value in col to middle col
                maxValueCol = Math.max(points[row][col] - Math.abs(col - middleCol), maxValueCol);
            }
            maxScore += maxValueCol;
        }
        return maxScore;
    }

}
