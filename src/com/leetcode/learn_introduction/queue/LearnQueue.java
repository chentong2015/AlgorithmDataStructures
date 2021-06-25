package com.leetcode.learn_introduction.queue;

import java.util.*;

/**
 * Queue队列：严格按照指定的访问顺序来process data
 * 1. Queue: First-in-first-out
 * 2. Queue: BFS 广度优先遍历
 */
public class LearnQueue {

    // 使用LinkedList(implements Deque<E>)作为队列使用
    public void testQueue() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(10);                     // 适用于队列的容量不受限制
        queue.offer(5);                 // 通常使用在容量受限的队列，该方法比add()更加可取 !!
        int size = queue.size();
        Integer checkValue = queue.peek(); // 查看应该出队列的元素，但是不会移除，可能返回null，不能赋值给值类型 !!
        if (!queue.isEmpty()) {
            int value = queue.poll();      // 取出第一个出队列的元素
        }
    }

    // ArrayDeque在默认初始情况下存储16个元素
    // Constructs an empty array deque with an initial capacity sufficient to hold 16 elements.
    public void testDeque() {
        Queue<Integer> queue = new ArrayDeque<>();
    }

    // 1. 基于优先级堆的无界优先级队列
    // 2. 优先级队列的元素根据它们的自然顺序进行排序，或者由队列构建时提供的Comparator进行排序
    private void testPriorityQueue() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    // Open the Lock
    // 4 circular wheels. Each wheel has 10 slots (0-9)(9-0) 数字循环，在相邻的两个数字可以自由移动
    // The lock initially starts at '0000', can not reach "deadends"
    // Given a target representing the value of the wheels that will unlock the lock,
    // return the minimum total number of turns, -1 if it's impossible
    //      0101   0102
    //     1212      2002
    //    0201        '0202'
    // "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202" 在数字变化的过程中，需要使用到char和int的转换

    // 0001  0010  0100  1000
    //          0000           -> 每一个结点可以延展开8个位置，每一个位置又能恢复成原来的位置，造成循环 !!
    // 0009  0090  0900  9000  -> 每变化一次，作为是一层的展开，记作一步统计 !!
    public int openLock(String[] deadends, String target) {
        // 测试理解：1. 逆向推导，从目标的值恢复成0000，避开死锁的情况下最快需要几步
        //            使用BFS算法，将该字符移动一步可以达到的相关位置都入队列，然后从第二层移动到第三层，最后判断结果是否能够到0000
        if (target.equals("0000")) return 1;
        HashSet<String> setDeadEnds = new HashSet<>();
        for (String end : deadends) setDeadEnds.add(end);

        HashSet<String> set = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.add(target);
        while (!queue.isEmpty()) {
            int value1 = target.charAt(0) - '0';
            int value2 = target.charAt(1) - '0';
            int value3 = target.charAt(2) - '0';
            int value4 = target.charAt(3) - '0';
            // 可以使用StringBuilder来优化字符的处理
            // 依次变化每一个字符，添加到队列和Set中，直到队列为空，或者到达了"0000"
            if (value1 == 0) {
                String newValue1 = "9" + value2 + value3 + value4;
                if (!setDeadEnds.contains(newValue1) && !set.contains(newValue1)) {
                    queue.add(newValue1);
                    set.add(newValue1);
                }
            } else {
                String newValue2 = String.valueOf(value1 - 1) + value2 + value3 + value4;
            }
            String newValue3 = String.valueOf(value1 + 1) + value2 + value3 + value4;
        }
        return -1; // It is impossible
    }
}