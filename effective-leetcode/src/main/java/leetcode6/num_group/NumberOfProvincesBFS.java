package leetcode6.num_group;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfProvincesBFS {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visit = new boolean[n];

        int numberOfComponents = 0;
        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                numberOfComponents++;
                bfs(isConnected, visit, i);
            }
        }
        return numberOfComponents;
    }

    public void bfs(int[][] isConnected, boolean[] visit, int iNode) {
        visit[iNode] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(iNode);
        while (!queue.isEmpty()) {
            iNode = queue.poll();
            for (int j = 0; j < isConnected.length; j++) {
                if (isConnected[iNode][j] == 1 && !visit[j]) {
                    queue.offer(j);
                    visit[j] = true;
                }
            }
        }
    }
}
