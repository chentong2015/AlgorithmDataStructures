package leetcode2;

import java.util.HashSet;
import java.util.Set;

// Largest Time for Given Digits
// Given an array arr of 4 digits,
// find the latest 24-hour time that can be made using each digit exactly once.
//
// 24-hour times are formatted as "HH:MM",
// where HH is between 00 and 23, and MM is between 00 and 59.
//
// The earliest 24-hour time is 00:00, and the latest is 23:59.
// arr.length == 4
// 0 <= arr[i] <= 9
public class LargestTimeGivenDigits {

    // TODO. 首位置数字只有三种可能情况，只能取0 1 2
    // 当第一位置的数据为2时，第二个数字约束范围有变化
    // 当第一位置的数据为2时，可能无法形成最后的结果 !!
    // 0  9  5  9
    // 1  9  5  9
    // 2  3  5  9
    //
    // [1,2,3,4] -> "23:41"
    // [0,4,0,0] -> "04:00"
    // [2,0,6,6] -> "06:26"
    // [5,5,5,5] -> ""
    //
    // O(3*4*4) 内部循环两次
    // O(3*4)
    public String largestTimeFromDigits(int[] arr) {
        int[][] limitsArray = {{0, 9, 5, 9}, {1, 9, 5, 9}, {2, 3, 5, 9}};
        String result = "";
        for (int[] limits: limitsArray) {
            boolean hasFound = true;
            StringBuilder timeBuilder = new StringBuilder();
            Set<Integer> savedIndex = new HashSet<>();
            for (int index = 0; index < 4; index++) {
                int findIndex = findMaxIndex(arr, limits[index], savedIndex);
                if (findIndex == -1) {
                    hasFound = false;
                    break;
                }
                savedIndex.add(findIndex);
                timeBuilder.append(arr[findIndex]);
                if (index == 1) {
                    timeBuilder.append(":");
                }
            }

            // 判断每一轮的约束是否能够得出结果, 取其中最大值
            if (!hasFound) {
                continue;
            }
            String hourTime = timeBuilder.toString();
            if (hourTime.compareTo(result) > 0) {
                result = hourTime;
            }
        }
        return result;
    }

    // 在非排除的坐标里，找小于等于某个值的最大值
    // Find the index of max value which is <= limit, and the index is not included in Set<>
    private int findMaxIndex(int[] arr, int limit, Set<Integer> savedIndex) {
        int findIndex = -1;
        for (int index = 0; index < 4; index++) {
            if (savedIndex.contains(index)) {
                continue;
            }
            if (arr[index] <= limit) {
                if (findIndex == -1) {
                    findIndex = index;
                } else if (arr[index] > arr[findIndex]) {
                    findIndex = index;
                }
            }
        }
        return findIndex;
    }
}
