package nonlinear.heap;

import java.util.*;

public class BasePriorityQueueOrdered {

    // TODO. 根据堆存储的int[]数组来排序
    private static void testPriorityQueueArray() {
        // 默认情况无法根据int[]来进行元素比较，必须自定义比较的方式 ！
        Queue<int[]> priorityQueue = new PriorityQueue<>();

        // 根据int[]的第二个位置值来排序，形成Min-Heap
        Queue<int[]> priorityQueue1 = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // 根据int[]的第一个位置值来排序，形成Min-Heap
        Queue<int[]> priorityQueue2 = new PriorityQueue<>((a1, a2) -> a1[0] - a2[0]);

        // 根据int[]的第二个位置值来排序，形成Max-Heap
        Queue<int[]> priorityQueue3 = new PriorityQueue<>((a1, a2) -> a2[1] - a1[1]);
        priorityQueue3.offer(new int[]{4, 5});
        priorityQueue3.offer(new int[]{7, 3});
        priorityQueue3.offer(new int[]{2, 10});
        while (!priorityQueue3.isEmpty()) {
            int[] item = priorityQueue3.poll();
            System.out.println(item[0] + "=" + item[1]);
        }
    }

    // TODO. 根据Hash表的统计结果来排序
    private static void testPriorityQueueHash() {
        HashMap<Integer, Integer> maps = new HashMap<>();
        maps.put(5, 3);
        maps.put(1, 7);
        maps.put(6, 4);

        // 根据maps.get(key)获取value统计值来排序，形成Min-Heap
        Queue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(maps::get));

        // 根据maps.get(key)获取value统计值来排序，形成Max-Heap
        Queue<Integer> heapMax = new PriorityQueue<>(Comparator.comparingInt(maps::get).reversed());

        for (int n : maps.keySet()) {
            heapMax.offer(n);
        }
        while (!heapMax.isEmpty()) {
            System.out.println(heapMax.poll());
        }
    }

    // TODO. 根据Node节点类型, 自定义堆中元素的比较方式
    public static void testPriorityQueueOrdered() {
        PriorityQueue<QueueNode> queue = new PriorityQueue<>(new Comparator<QueueNode>() {
            @Override
            public int compare(QueueNode o1, QueueNode o2) {
                if (o1.units < o2.units) {
                    return 1;
                } else if (o1.units > o2.units) {
                    return -1;
                }
                return o1.name.compareTo(o2.name);
            }
        });
    }

    static class QueueNode {
        int units;
        String name;
    }
}