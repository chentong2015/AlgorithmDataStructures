package nonlinear.heap.min_max_heap;

import java.util.Collections;
import java.util.PriorityQueue;

// TODO. 最大和最小堆和对调使用，取中间值的时候需要在特定堆取
public class MedianDataStream2 {

    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    private boolean even = true;

    public void addNum(int num) {
        if (even) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
        even = !even;
    }

    public double findMedian() {
        if (even) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return minHeap.peek();
    }
}
