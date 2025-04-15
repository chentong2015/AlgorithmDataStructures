package array.array2.rotate_image;

// Rotate Image
// You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees
// You have to rotate the image in-place
//
// n == matrix.length == matrix[i].length
// 1 <= n <= 20
// -1000 <= matrix[i][j] <= 1000
public class RotateImage {

    // TODO. 旋转时确定坐标，将行坐标和列坐标互换
    // 只有4个点(东南西北)位置的坐标值需要依次交换
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                // i横坐标确定列数，j列坐标确定行数
                int temp = matrix[n-1-j][i];

                // 使用i，j计算4个坐标，从后往前替换
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }
}
