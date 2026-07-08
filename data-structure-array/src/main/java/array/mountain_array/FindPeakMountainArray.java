package array.mountain_array;

// Peak Index in a Mountain Array
//
// An array arr is a mountain if the following properties hold
// arr[0] < arr[1] < ... < arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
// return the index i in O(log(arr.length)) time complexity.
//
// arr.length >= 3
// arr is guaranteed to be a mountain array.
public class FindPeakMountainArray {

    // TODO. 非纯有序数据也能二分搜索, 只要能判middle的移动方向
    // arr = [0,10,5,2] -> 1
    //     left=1  2=right
    public int peakIndexInMountainArray(int[] arr) {
        // 初始值往中间靠, 直接判断+1和-1位置的值
        int left = 1;
        int right = arr.length - 2;

        while (left < right) {
            int middle = left + (right - left) / 2;
            if (arr[middle - 1] < arr[middle] && arr[middle] > arr[middle+1]) {
                return middle; // 确定是山峰数组则直接判断
            } else if (arr[middle - 1] < arr[middle]) {
                left = middle + 1; // 上升趋势, Peak点在右侧
            } else {
                right = middle;
            }
        }

        // 退出条件left==right, 聚焦到唯一index位置(山峰位置)
        return left;
    }
}
