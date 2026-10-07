package amazonTop50;

import java.util.*;

// K Closest Points to Origin
// Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane
// and an integer k, return the k closest points to the origin (0, 0).
//
// The distance between two points on the X-Y plane is the Euclidean distance
// (i.e., √(x1 - x2)2 + (y1 - y2)2).
//
// You may return the answer in any order.
// The answer is guaranteed to be unique (except for the order that it is in).
//
// 1 <= k <= points.length <= 104
// -10^4 <= xi, yi <= 10^4
public class KClosestPointsToOrigin {

    // TODO. 创建Pair对象来存储二维坐标
    // points = [[3,3],[5,-1],[-2,4]], k = 2
    // [[3,3],[-2,4]]
    //
    // [[0,1],[1,0]], k = 2 考虑特殊数据
    // [[0,1],[1,0]]
    //
    // O(N*LogK)  边读边排序(二维坐标)
    // O(N+K)     存储距离和结果K个值

    public int[][] kClosest(int[][] points, int k) {
        // distance -> list of index
        HashMap<Integer, List<Integer>> hashmap = new HashMap<>();
        // Max Heap 最大堆保证大值先被移除
        Queue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());

        for (int index = 0; index < points.length; index++) {
            int distance = points[index][0] * points[index][0] + points[index][1] * points[index][1];
            heap.add(distance);

            List<Integer> list = hashmap.getOrDefault(distance, new ArrayList<>());
            list.add(index);
            hashmap.put(distance, list);

            // 将最大的距离移除，确定所有对应坐标都不是结果
            if (heap.size() > k) {
                hashmap.remove(heap.poll());
            }
        }

        int id = 0;
        int[][] result = new int[k][2];
        for (List<Integer> list : hashmap.values()) {
            for (int index : list) {
                result[id][0] = points[index][0];
                result[id][1] = points[index][1];
                id++;
            }
        }
        return result;
    }
}
