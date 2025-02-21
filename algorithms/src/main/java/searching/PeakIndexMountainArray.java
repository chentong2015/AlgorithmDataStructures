package searching;

public class PeakIndexMountainArray {

    // TODO. 对于非纯排序的数据也可以使用二分查找，在每个index位置判断前后移动
    // Peak Index in a Mountain Array
    // arr.length >= 3
    // An array arr is a mountain if the following properties hold
    // arr[0] < arr[1] < ... < arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
    // return the index i in O(log(arr.length)) time complexity.
    //
    // Input: arr = [0,10,5,2] -> 1
    //            left=1  2=right
    public int peakIndexInMountainArray(int[] arr) {
        int left = 1;
        int right = arr.length - 2;
        while (left < right) {
            int middle = left + (right - left) / 2;
            if (arr[middle-1] < arr[middle] && arr[middle] > arr[middle+1]) {
                return middle;
            } else if (arr[middle-1] < arr[middle]) {
                // Peak位置在右侧，left必须后移一位，避免无限循环
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        // while退出条件left==right,聚焦到唯一的index位置，也就是山峰点的位置
        return left;
    }
}
