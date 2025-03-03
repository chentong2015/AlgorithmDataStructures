package leetcode2;

import java.util.Comparator;
import java.util.PriorityQueue;

// Two City Scheduling
// A company is planning to interview 2n people.
// Given the array costs where costs[i] = [aCosti, bCosti],
// - the cost of flying the ith person to city a is aCosti,
// - the cost of flying the ith person to city b is bCosti.
// Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.
//
// 2 * n == costs.length
// 2 <= costs.length <= 100
// costs.length is even.
// 1 <= aCosti, bCosti <= 1000
public class TwoCityScheduling {

    // TODO. 在所有的2N人去一个城市中，选择N个省钱最大的去另一个城市
    // [[10,20],[30,200],[400,50],[30,20]]
    // 10 + 30 + 50 + 20 = 110
    // The total minimum cost is 110 to have half the people interviewing in each city.
    //
    // [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
    // 1859
    //
    //
    // [[10,20],[30,200],[400,50],[30,20]]
    // 10 + 30 + 400 + 30 = 4 A
    // -10  -170 +350 +10   Minus the Top N diff to save max cost
    // 10 + 30 + 50  + 20 = 2 A + 2B
    //
    // O(2N * log2N)
    // O(2N)
    public int twoCitySchedCost(int[][] costs) {
        // Create max-heap to save top diff values
        PriorityQueue<Integer> diffHeap = new PriorityQueue<>(Comparator.reverseOrder());

        // Make 2N persons go to city A
        int totalCost = 0;
        int length = costs.length;
        for (int[] cost : costs) {
            totalCost += cost[0];
            int diff = cost[0] - cost[1];
            // Take O(log2N) to insert item to Heap
            diffHeap.offer(diff);
        }

        // Make N persons go to city B, save the diff costs
        for (int index = 0; index < length / 2; index++) {
            if (!diffHeap.isEmpty()) {
                totalCost -= diffHeap.poll();
            }
        }
        return totalCost;
    }
}
