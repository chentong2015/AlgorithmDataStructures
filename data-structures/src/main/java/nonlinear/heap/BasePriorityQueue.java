package nonlinear.heap;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class BasePriorityQueue {

    // 输出顺序: A B C D 基于自然排序
    private void testPriorityQueue() {
        Queue<String> queue = new PriorityQueue<>(); // Min-Heap 默认最小堆
        queue.offer("D");
        queue.offer("B");
        queue.offer("A");
        queue.offer("C");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    public static void main(String[] args) {
        Queue<Double> queue = new PriorityQueue<>(); // Min-Heap 默认自然排序
        Queue<Double> queue1 = new PriorityQueue<>(Collections.reverseOrder()); // Max-Heap
        Queue<Double> queue2 = new PriorityQueue<>(Comparator.reverseOrder()); // Max-Heap
        queue.offer(3.0);
        queue.offer(1.5);
        queue.offer(2.0);
        queue.offer(4.0);
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
