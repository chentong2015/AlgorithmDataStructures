package heap.double_heap;

import java.util.Collections;
import java.util.PriorityQueue;

// Find Median from Data Stream
// The median is the middle value in an ordered integer list.
// If the size of the list is even, there is no middle value,
// and the median is the mean of the two middle values.
//
// for arr = [2,3,4], the median is 3.
// for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
//
// -10^5 <= num <= 10^5
// There will be at least one element in the data structure before calling findMedian.
// At most 50000 calls will be made to addNum and findMedian
public class MedianDataStream1 {

    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    private boolean even = true;

    // O(log(n/2)) 插入堆的复杂度取决于高度，左右两边各存储一半数据
    public void addNum(int num) {
        if (even) {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        } else {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        }
        even = !even;
    }

    // O(1) 直接Peek堆顶位置的数据即为中间值
    public double findMedian() {
        if (even) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }
}
