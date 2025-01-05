package collections.heap;

import java.util.*;

public class BasePriorityQueueOrdered {

    class QueueNode {
        int units;
        String name;
    }

    // TODO. 根据自定义Node节点类型，完整定义比较的三种结果
    public void testPriorityQueueOrdered() {
        PriorityQueue<QueueNode> queue = new PriorityQueue<>(new Comparator<QueueNode>() {
            @Override
            public int compare(QueueNode o1, QueueNode o2) {
                if (o1.units < o2.units) {
                    return 1;
                } else if (o1.units > o2.units) {
                    return -1;
                }
                return 0;
            }
        });
    }

    // TODO. 根据队列存储的类型，选择特定的数据进行比较
    public void testOtherOrdered() {
        HashMap<Integer, Integer> results = new HashMap<>();
        Queue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(results::get));
        for (int n : results.keySet()) {
            heap.add(n);
        }

        int[] nums1 = new int[10];
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int x : nums1) {
            priorityQueue.offer(new int[]{x, 0});
        }
    }
}