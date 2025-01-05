package collections.heap.two_heap_storage;

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
public class MedianDataStream {

    // TODO. 问题的本质是排序数据 + 取Top元素特征的数据
    private PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> large = new PriorityQueue<>();
    private boolean even = true;

    // O(log(n/2)) 插入堆的复杂度取决于高度，左右两边各存储一半数据
    public void addNum(int num) {
        if (even) {
            large.offer(num);
            small.offer(large.poll());
        } else {
            small.offer(num);
            large.offer(small.poll());
        }
        even = !even;
    }

    // O(1) 直接Peek堆顶位置的数据即为中间值
    public double findMedian() {
        if (even) {
            return (small.peek() + large.peek()) / 2.0;
        } else {
            return small.peek();
        }
    }
}
