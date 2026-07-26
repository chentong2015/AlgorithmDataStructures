package googleTop50.graph;

// Detonate the Maximum Bombs
public class DetonateMaxBombsDFS {

    public int maximumDetonation(int[][] bombs) {
        int maxCount = 0;
        for(int index = 0; index < bombs.length; index++) {
            boolean[] hasChecked = new boolean[bombs.length];
            int count = dfs(bombs, bombs[index], index, 0, hasChecked);
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }

    // DFS Solution
    // 递归时必须把过程的数据带着走，传递给dfs方法参数
    private int dfs(int[][] bombs, int[] currentBomb, int index, int count, boolean[] hasChecked) {
        if (hasChecked[index]) {
            return count;
        }
        hasChecked[index] = true;
        count++;

        for (int i = 0; i < bombs.length; i++) {
            if (!hasChecked[i] && isConnected(currentBomb, bombs[i])) {
               count = dfs(bombs, bombs[i], i, count, hasChecked);
            }
        }
        return count;
    }

    private boolean isConnected(int[] bomb, int[] nextBomb) {
        long dx = bomb[0] - nextBomb[0];
        long dy = bomb[1] - nextBomb[1];
        return dx * dx + dy * dy <= (long) bomb[2] * bomb[2];
    }
}
