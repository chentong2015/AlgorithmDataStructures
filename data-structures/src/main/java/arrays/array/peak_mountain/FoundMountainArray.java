package arrays.array.peak_mountain;

// Valid Mountain Array
// Given an array of integers arr, return true if and only if it is a valid mountain array
//
// 山峰数组特点: 整个数组只构成一个山峰，只有一个位置最高峰
// 数组两端的数小, 中间的数字大, 必须是严格的上升或者下降, 必须有升有降
// 从两边往中间读取，直到左边到达山峰点，右边到达山峰点，最后这两个山峰点必须是同一个位置
public class FoundMountainArray {

    // TODO. 从左往后遍历时必须随时判断index是否越界
    // arr = [0,3,2,1]     -> true
    // arr = [0,1,2,3,4,5] -> false
    // O(n) O(1)
    public static boolean validMountainArray(int[] arr) {
        if (arr.length < 3) {
            return false;
        }

        // 必须至少上升一步且不能上升到最后
        int index = 0;
        while (index < arr.length - 1 && arr[index] < arr[index + 1]) {
            index++;
        }
        if (index == 0 || index == arr.length - 1) {
            return false;
        }

        // 必须下降直到结尾
        while (index < arr.length - 1 && arr[index] > arr[index + 1]) {
            index++;
        }
        return index == arr.length - 1;
    }
}
