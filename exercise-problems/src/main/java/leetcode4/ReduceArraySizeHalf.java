package leetcode4;

import java.util.Arrays;
import java.util.HashMap;

// Reduce Array Size to The Half
// You are given an integer array arr.
// You can choose a set of integers and remove all the occurrences of these integers in the array.
//
// Return the minimum size of the set
// so that at least half of the integers of the array are removed.
//
// 2 <= arr.length <= 10^5
// arr.length is even.
// 1 <= arr[i] <= 10^5
public class ReduceArraySizeHalf {

    // TODO. 对统计频率进行排序，从高频到低频逐个移除(保证移除的数据最少)
    // arr = [3,3,3,3,5,5,5,2,2,7] -> {3,7},{3,5},{3,2},{5,2} -> 2
    // [5,5,5,2,2]
    //
    // arr = [7,7,7,7,7,7] -> {7} -> 1
    // []
    //
    // 3,3,3,3,5,5,5,2,2,7
    // 3 -> 4
    // 5 -> 3
    // 2 -> 2
    // 7 -> 1
    //
    // O(N + N + N*log(N) + N/2)
    // O(N + N)
    public int minSetSize(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int a: arr) {
            int count = map.getOrDefault(a, 0);
            map.put(a, count+1);
        }

        // 单独对频率进行排序
        int[] counts = new int[map.size()];
        int index = 0;
        for (int count : map.values()) {
            counts[index++] = count;
        }

        Arrays.sort(counts);

        int leftCount = arr.length;
        for (int i = counts.length - 1; i >= 0; i--) {
            leftCount -= counts[i];
            if (leftCount <= arr.length / 2) {
                return counts.length - i;
            }
        }
        return arr.length;
    }
}
