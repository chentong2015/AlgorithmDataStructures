package heap;

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

    public static void testPriorityQueueOrdered() {
        // TODO. 自定义Node比较构建Min-Heap堆
        PriorityQueue<HeapNode> minHeap = new PriorityQueue<>(new Comparator<HeapNode>() {
            @Override
            public int compare(HeapNode o1, HeapNode o2) {
                if (o1.frequency < o2.frequency) {
                    return 1;
                } else if (o1.frequency > o2.frequency) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        // TODO. 自定义Node比较构建Max-Heap堆
        PriorityQueue<HeapNode> maxHeap = new PriorityQueue<>(
                Comparator.comparingInt((HeapNode o) -> o.frequency).reversed());
    }

    class HeapNode {
        String word;
        int frequency;

        public HeapNode(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }
    }
}