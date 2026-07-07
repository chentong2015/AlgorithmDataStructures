package base_template.iteration.impl;

import java.util.Iterator;

// Peeking Iterator
// Design an iterator that supports the peek operation on an existing iterator
// in addition to the hasNext and the next operations.
//
// PeekingIterator(Iterator<int> nums) Initializes the object with the given integer iterator iterator.
// int next()        Returns the next element in the array and moves the pointer to the next element.
// boolean hasNext() Returns true if there are still elements in the array.
// int peek()        Returns the next element in the array without moving the pointer.
//
// 1 <= nums.length <= 1000
// 1 <= nums[i] <= 1000
// All the calls to next and peek are valid.
// At most 1000 calls will be made to next, hasNext, and peek.
public class PeekingIterator implements Iterator<Integer>  {

    // TODO. 不能再存储一遍数据(直接使用迭代器), 判断和取值都必须O(1)

    private boolean hasNext;
    private int nextElement;
    private Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator) {
         this.iterator = iterator;
         if (this.iterator.hasNext()) {
             this.hasNext = true;
             this.nextElement = this.iterator.next();
         }
    }

    // TODO. 查看迭代器中下一个元素(该元素被提前取出并保存)
    public Integer peek() {
        return this.nextElement;
    }

    @Override
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override
    public Integer next() {
        int next = nextElement;
        if (this.iterator.hasNext()) {
            this.hasNext = true;
            this.nextElement = this.iterator.next();
        } else {
            this.hasNext = false;
        }
        return next;
    }
}
