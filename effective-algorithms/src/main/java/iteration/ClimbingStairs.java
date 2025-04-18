package iteration;

// Climbing Stairs
// It takes n steps to reach the top, each time you can either climb 1 or 2 steps.
// how many distinct ways can you climb to the top ?
public class ClimbingStairs {

    // TODO. 递归计算每个位置的来源可能性
    // n = 2
    // 1. 1 step + 1 step
    // 2. 2 steps
    //
    // n = 3
    // 1. 1 step + 1 step + 1 step
    // 2. 1 step + 2 steps
    // 3. 2 steps + 1 step
    //
    // O(2^n) Size of recursion tree
    // O(n)   The depth of the recursion tree
    public int climbStairs(int n) {
        if (n == 1) return 1;
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
}
