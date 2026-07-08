package array.mountain_array;

// Valid Mountain Array
// Given an array of integers arr,
// return true if and only if it is a valid mountain array
public class BaseValidMountainArray {

    // TODO. 先While上升移动, 再While下降移动
    // arr = [0,3,2,1]     -> true
    // arr = [0,1,2,3,4,5] -> false
    // O(n) O(1)
    public static boolean isValidMountainArray(int[] arr) {
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
