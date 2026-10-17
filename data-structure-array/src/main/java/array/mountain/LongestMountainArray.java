package array.mountain;

// Longest Mountain in Array
// An array arr is a mountain array if and only if:
//   arr.length >= 3
//   There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
//   arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
//   arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
//
// Given an integer array arr, return the length of the longest subarray,
// which is a mountain. Return 0 if there is no mountain subarray.
//
// 1 <= arr.length <= 10^4
// 0 <= arr[i] <= 10^4
public class LongestMountainArray {

    // TODO. 双指针: 判断山峰的上升和下降, 标记长度
    // [2,1,4,7,3,2,5] -> [1,4,7,3,2] -> 5
    // [2,2,2] -> 0
    //
    public int longestMountain(int[] arr) {
        if (arr.length < 3) {
            return 0;
        }
        int result = 0;
        int left = 0;
        int index = 1;
        while (index < arr.length - 1) {
            if (arr[left] >= arr[index] || arr[index] == arr[index+1]) {
                left = index; // 不构成山峰条件, 改坐标
                index++;
                continue;
            }
            if (arr[index] < arr[index+1]) {
                index++;      // 上峰继续上升
                continue;
            }

            // 构成山峰条件, 找到右侧最远下降位置
            int right = index+1;
            while (right < arr.length && arr[right-1] > arr[right]) {
                right++;
            }
            result = Math.max(result, right-left);
            left = right - 1; // 重置上峰的左侧
            index = right;
        }
        return result;
    }
}