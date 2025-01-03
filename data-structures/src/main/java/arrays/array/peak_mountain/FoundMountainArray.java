package arrays.array.peak_mountain;

public class FoundMountainArray {

    // TODO. 山峰数组特点: 整个数组只构成一个山峰，只有一个位置最高峰
    // 数组两端的数小, 中间的数字大, 必须是严格的上升或者下降, 必须有升有降
    // 从两边往中间读取，直到左边到达山峰点，右边到达山峰点，最后这两个山峰点必须是同一个位置
    // Valid Mountain Array
    // Given an array of integers arr, return true if and only if it is a valid mountain array
    //
    // arr = [0,3,2,1]     -> true
    // arr = [0,1,2,3,4,5] -> false
    // O(n) O(1)
    public static boolean validMountainArray(int[] arr) {
        if (arr.length < 3) {
            return false;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < arr.length - 1) {
            if (arr[left] >= arr[left + 1]) {
                break;
            }
            left++;
        }
        while (right > 0) {
            if (arr[right - 1] <= arr[right]) {
                break;
            }
            right--;
        }
        // 左右两边必须出发至少一步
        return left > 0 && right < arr.length - 1 && left == right;
    }

    // TODO. 从左往后遍历时必须随时判断index是否越界
    public static boolean validMountainArray2(int[] arr) {
        if (arr.length < 3) {
            return false;
        }

        int index = 0;
        while (index < arr.length - 1 && arr[index] < arr[index + 1]) {
            index++;
        }
        // 必须至少上升一步且不能上升到最后
        if (index == 0 || index == arr.length - 1) {
            return false;
        }
        while (index < arr.length - 1 && arr[index] > arr[index + 1]) {
            index++;
        }
        // 必须下降直到结尾
        return index == arr.length - 1;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,4};
        System.out.println(validMountainArray2(arr));
    }
}
