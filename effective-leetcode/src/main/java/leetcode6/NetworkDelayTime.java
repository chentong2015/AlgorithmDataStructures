package leetcode6;

import java.util.*;

// Network Delay Time
// You are given a network of n nodes, labeled from 1 to n.
// You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi),
//   ui is the source node,
//   vi is the target node,
//   and wi is the time it takes for a signal to travel from source to target.
//
// We will send a signal from a given node k.
// Return the minimum time it takes for all the n nodes to receive the signal.
// If it is impossible for all the n nodes to receive the signal, return -1.
//
// 1 <= k <= n <= 100
//
public class NetworkDelayTime {

    // TODO. 本质是延迟时间的最小化，无所谓每个Node节点的访问路径 !!
    //
    // [2,1,1], n=4, k=2 -> 2
    // [2,3,1],
    // [3,4,1],
    // 1 <-- 2 --> 3 ---> 4
    //
    // [[1,2,1],[2,1,3]], n=2, k=2
    // 1 <--> 2      注意相互联通的图形
    //
    // [[1,2,1],[2,3,2],[1,3,4]], n=3, k=1
    // 1 --> 2 --> 3
    //   -------->   注意多条路径选择的图形

    public int networkDelayTime(int[][] times, int n, int k) {
        int[] relativeDelay = new int[n + 1];
        Arrays.fill(relativeDelay, Integer.MAX_VALUE);
        relativeDelay[k] = 0; // 标记第k个起点位置，节点延迟为0

        // TODO. distDelay记录的是相对于K节点的延迟 !!
        // 循环N-1次的剩余节点数，至少每次联通一个节点Target
        for (int i = 1; i < n; i++) {
            for (int[] edge : times) {
                int sourceId = edge[0];
                int targetId = edge[1];
                int time = edge[2];

                // 刷新targetId位置更小的相对延迟
                if (relativeDelay[sourceId] != Integer.MAX_VALUE
                        && relativeDelay[sourceId] + time < relativeDelay[targetId]) {
                    relativeDelay[targetId] = relativeDelay[sourceId] + time;
                }
            }
        }

        // 找出所有Node到达的最大延迟(记录的延迟已经最小化)，MAX_VALUE说明无法到达
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (relativeDelay[i] == Integer.MAX_VALUE) {
                return -1;
            }
            result = Math.max(result, relativeDelay[i]);
        }
        return result;
    }
}