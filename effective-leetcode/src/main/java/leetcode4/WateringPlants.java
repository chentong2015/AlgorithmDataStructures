package leetcode4;

// Watering Plants
// You want to water n plants in your garden with a watering can.
// The plants are arranged in a row and are labeled from 0 to n - 1 from left to right
// where the ith plant is located at x = i
// There is a river at x = -1 that you can refill your watering can at.
//
// Each plant needs a specific amount of water. You will water the plants in the following way:
// - Water the plants in order from left to right.
// - After watering the current plant,
//   if you do not have enough water to completely water the next plant,
//   return to the river to fully refill the watering can.
// - You cannot refill the watering can early.
//
// You are initially at the river (i.e., x = -1).
// It takes one step to move one unit on the x-axis.
//
// Given a 0-indexed integer array plants of n integers,
// where plants[i] is the amount of water the ith plant needs,
// and an integer capacity representing the watering can capacity,
// return the number of steps needed to water all the plants.
//
// n == plants.length
// 1 <= n <= 1000
// 1 <= plants[i] <= 10^6
// max(plants[i]) <= capacity <= 10^9
public class WateringPlants {

    // TODO. 注意回到River打水的过程需要计算来回的步数
    // plants = [2,2,3,3], capacity = 5
    // - Walk to plant 0 (1 step) and water it. Watering can has 3 units of water.
    // - Walk to plant 1 (1 step) and water it. Watering can has 1 unit of water.
    // - Since you cannot completely water plant 2,
    //   walk back to the river at -1 to refill (2 steps).
    // - Walk to plant 2 (3 steps) and water it. Watering can has 2 units of water.
    // - Since you cannot completely water plant 3,
    //   walk back to the river at -1 to refill (3 steps).
    // - Walk to plant 3 (4 steps) and water it.
    // 1 + 1 + 2 + 3 + 3 + 4 = 14.
    //
    // plants = [1,1,1,4,2,3], capacity = 4
    // - Water plants 0, 1, and 2 (3 steps). Return to river (3 steps).
    // - Water plant 3 (4 steps). Return to river (4 steps).
    // - Water plant 4 (5 steps). Return to river (5 steps).
    // - Water plant 5 (6 steps).
    // Steps needed = 3 + 3 + 4 + 4 + 5 + 5 + 6 = 30.
    //
    // O(N)
    // O(1)
    public int wateringPlants(int[] plants, int capacity) {
        int steps = 1;
        int remainingWater = capacity - plants[0];
        int nextIndex = 1;
        while (nextIndex < plants.length) {
            if (remainingWater < plants[nextIndex]) {
                // return to river and back
                steps += 2 * nextIndex;
                remainingWater = capacity;
            } else {
                // Watering plant and move on
                steps++;
                remainingWater -= plants[nextIndex];
                nextIndex++;
            }
        }
        return steps;
    }
}
