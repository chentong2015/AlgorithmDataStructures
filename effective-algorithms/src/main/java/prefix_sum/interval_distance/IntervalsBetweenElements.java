package prefix_sum.interval_distance;

// Intervals Between Identical Elements
// You are given a 0-indexed array of n integers arr
// The interval between two elements in arr is defined as the absolute difference between their indices.
// More formally, the interval between arr[i] and arr[j] is |i - j|.
//
// Return an array intervals of length n where intervals[i] is
// - the sum of intervals between arr[i]
// - and each element in arr with the same value as arr[i].
//
// n == arr.length
// 1 <= n <= 10^5
// 1 <= arr[i] <= 10^5
public class IntervalsBetweenElements {

    // TODO. 如何避免计算距离的总和
    // [2,1,3,1,2,3,3]
    //  0 1 2 3 4 5 6
    // [4,2,7,2,4,4,5]
    //
    // 2 -> 0,4
    // 1 -> 1,3
    // 3 -> 2,5,6
    //      0 3 5
    //
    public long[] getDistances(int[] arr) {
        long[] distances = new long[arr.length];

        // Maps the values to their list of indices
        // HashMap<>

        return distances;
    }
}
