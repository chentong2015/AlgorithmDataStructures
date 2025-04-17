package heap_double;

import java.util.Collections;
import java.util.PriorityQueue;

// Destroying Asteroids
// You are given an integer mass, which represents the original mass of a planet.
// You are further given an integer array asteroids,
// where asteroids[i] is the mass of the ith asteroid.
//
// You can arrange for the planet to collide with the asteroids in any arbitrary order.
// If the mass of the planet is greater than or equal to the mass of the asteroid,
// the asteroid is destroyed and the planet gains the mass of the asteroid.
// Otherwise, the planet is destroyed.
//
// Return true if all asteroids can be destroyed. Otherwise, return false.
// 1 <= mass <= 10^5
// 1 <= asteroids.length <= 10^5
// 1 <= asteroids[i] <= 10^5
public class DestroyingAsteroids {

    // TODO. 使用双堆结构来找寻"中间最接近"某个数的值
    // 每次撞击质量最接近(<=)mass的小行星，直到不能撞击为止
    // mass = 10, asteroids = [3,9,19,5,21] -> true
    // - + mass 9.  New planet mass: 10 + 9 = 19
    // - + mass 19. New planet mass: 19 + 19 = 38
    // - + mass 5.  New planet mass: 38 + 5 = 43
    // - + mass 3.  New planet mass: 43 + 3 = 46
    // - + mass 21. New planet mass: 46 + 21 = 67
    //
    // mass = 5, asteroids = [4,9,23,4] -> 22 < 23 -> false
    // - + mass 4, 5 + 4 = 9
    // - + mass 9, 9 + 9 = 18
    // - + mass 4, 18 + 4 = 22
    //
    // O(N*logN + N) 插入堆的时间复杂度 + 循环堆中所有数据
    // O(N) 堆需要存储所有的数据值
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 根据Mass作为中间值来构建双堆的数据
        for (int asteroid: asteroids) {
            if (asteroid <= mass) {
                maxHeap.offer(asteroid);
            } else {
                minHeap.offer(asteroid);
            }
        }
        if (maxHeap.isEmpty()) {
            return false;
        }

        // 不断中最大堆中取值，将最小堆中的数据甩到最大堆中
        while (!minHeap.isEmpty()) {
            if (maxHeap.isEmpty()) {
                break;
            }
            mass += maxHeap.poll();
            while (!minHeap.isEmpty() && minHeap.peek() <= mass) {
                maxHeap.offer(minHeap.poll());
            }
        }

        // 最后判断最小堆中的数据是否被消完
        return minHeap.isEmpty();
    }
}
