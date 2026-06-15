package leetcode5.snapshot;

import java.util.TreeMap;

// Snapshot Array
// Implement a SnapshotArray that supports the following interface
//
// 1 <= length <= 5 * 10^4
// 0 <= index < length
// 0 <= val <= 10^9
// 0 <= snap_id < (the total number of times we call snap())
// At most 5 * 10^4 calls will be made to set, snap, and get.
public class SnapshotArray {

    // TODO. TreeMap存储历史结构, 优化存储空间且能够使用二分法查找
    //
    // O(N+KlogK) N是长度, K是Pair对数量
    // O(N+K)     需要空间存储[SnapID, ID]数据

    int snapId = 0;
    TreeMap<Integer, Integer>[] historyRecords;

    public SnapshotArray(int length) {
        historyRecords = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            historyRecords[i] = new TreeMap<>();
            historyRecords[i].put(0, 0);
        }
    }

    // TODO. 设置时同一个SnapID状态的值只会被记录一次
    public void set(int index, int val) {
        historyRecords[index].put(snapId, val);
    }

    public int snap() {
        return snapId++;
    }

    //
    // Returns a key-value mapping associated with the greatest key less than or equal to the given key
    public int get(int index, int snapId) {
        return historyRecords[index].floorEntry(snapId).getValue();
    }
}
