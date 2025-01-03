package data_stream;

import java.util.LinkedList;

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

// TODO. 数据结构分析
// - 插入值大小是无序的，因此不能使用两个变量来记录
// - 不能使用LinkedList，因为链表会存储重复数据，导致查询变慢
public class MedianDataStream {



    private double median;
    private final LinkedList<Integer> linkedList;

    public MedianDataStream() {
        this.linkedList = new LinkedList<>();
    }

    // TODO: ，并每次插入都应对数据排序
    // O(N)
    public void addNum(int num) {
        int index = 0;
        while (index < linkedList.size()) {
            if (linkedList.get(index) <= num) {
                index++;
            } else {
                break;
            }
        }
        linkedList.add(index, num);
    }

    // O(N)
    public double findMedian() {
        int size = linkedList.size();
        if (size % 2 == 0) {
            int sum = linkedList.get(size/2 - 1) + linkedList.get(size/2 + 1 - 1);
            return (double) sum / 2;
        } else {
            return linkedList.get((size + 1) / 2 - 1);
        }
    }
}
