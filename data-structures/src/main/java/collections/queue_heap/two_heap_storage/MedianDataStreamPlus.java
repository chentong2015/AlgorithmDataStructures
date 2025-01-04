package collections.queue_heap.two_heap_storage;

// Follow up: 根据特定的输入数据，进步优化算法
//
// If all integer numbers from the stream are in the range [0, 100],
// how would you optimize your solution?
//
// If 99% of all integer numbers from the stream are in the range [0, 100],
// how would you optimize your solution?
public class MedianDataStreamPlus {

    // 存储的数据值直接对应到Index坐标
    // - 保证插入数据 O(1)
    // - 保证查询数据 O(1) 常量的遍历时间
    private int[] counts = new int[101];

    public MedianDataStreamPlus() {

    }

    public void addNum(int num) {

    }

    public double findMedian() {
       return 0;
    }
}
