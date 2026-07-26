package googleTop50.graph;

import java.util.ArrayDeque;
import java.util.Queue;

// Detonate the Maximum Bombs
public class DetonateMaxBombsBFS {

    // TODO. 计算每一个起点能够触发的最大范围
    //
    // O(N*N*N)
    // O(N + N)
    public int maximumDetonation(int[][] bombs) {
        int maxCount = 0;
        for(int index = 0; index < bombs.length; index++) {
            maxCount = Math.max(maxCount, bfs(bombs, bombs[index], index));
        }
        return maxCount;
    }

    // BFS solution
    // O(N*N)
    // O(N + N)
    private int bfs(int[][] bombs, int[] startBomb, int index) {
        Queue<int[]> nextBombQueue = new ArrayDeque<>();
        boolean[] checkedBombs = new boolean[bombs.length]; // N

        // at least invoke one bomb
        int count = 1;
        checkedBombs[index] = true;

        // init the next following bombs
        for(int i = 0; i < bombs.length; i++) {
            if(!checkedBombs[i] && isConnected(startBomb, bombs[i])) {
                nextBombQueue.add(bombs[i]);
                checkedBombs[i] = true;
                count++;
            }
        }

        // continue the BFS loop
        while(!nextBombQueue.isEmpty()) {
            int[] nextBomb = nextBombQueue.poll(); // pop from queue
            for(int i = 0; i < bombs.length; i++) {
                if(!checkedBombs[i] && isConnected(nextBomb, bombs[i])) {
                    nextBombQueue.add(bombs[i]);
                    checkedBombs[i] = true;
                    count++;
                }
            }
        }
        return count;
    }

    // 计算距离的平方不需要使用Math.abs(), 考虑Long数值溢出
    private boolean isConnected(int[] bomb, int[] nextBomb) {
        long dx = bomb[0] - nextBomb[0];
        long dy = bomb[1] - nextBomb[1];
        return dx * dx + dy * dy <= (long) bomb[2] * bomb[2];
    }
}
