package leetcode6.num_province;

// Number of Provinces
// There are n cities. Some of them are connected, while some are not.
// If city a is connected directly with city b,
// and city b is connected directly with city c,
// then city a is connected indirectly with city c.
//
// A province is a group of directly or indirectly connected cities
// and no other cities outside the group.
//
// You are given an n x n matrix isConnected where
// isConnected[i][j] = 1 if the ith city and the jth city are directly connected,
// isConnected[i][j] = 0 otherwise.
//
// 1 <= n <= 200
// isConnected[i][j] is 1 or 0.
// isConnected[i][i] == 1
// isConnected[i][j] == isConnected[j][i] 联通一定是相互的
public class NumberOfProvincesDFS {

    // TODO. DFS Connected Component 深度递归联通组件(图形思想)
    // 只关心有多少种Group集合, 不记录Group中有什么
    //
    // [1,0,0], -> 0 每个城市彼此独立
    // [0,1,0],
    // [0,0,1]
    //
    // [x,1,0], -> 2 两个Group集合
    // [1,x,0],
    // [0,0,x]
    //
    // [1,0,0,1], -> 1 只有一个集合
    // [0,1,1,0],
    // [0,1,1,1],
    // [1,0,1,1]]
    //
    // O(N*N)  必须访问全部有效的标志位
    // O(N+N)  DFS最多递归全部的节点
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visit = new boolean[n];   // 标记递归遍历过的Node, 判断新组的统计
        int numberOfComponents = 0;
        for (int i = 0; i < n; i++) {       // 循环所有节点, i表示第一个Node节点
            if (!visit[i]) {
                numberOfComponents++;       // 新出现的Node作为新Group统计
                dfs(isConnected, visit, i); // 找第i个节点的全部联通节点
            }
        }
        return numberOfComponents;
    }

    // TODO. 递归找出第iNode个节点联通所有节点，并标记
    // 遍历的第i行则对应第i个Node节点, 等效的
    public void dfs(int[][] isConnected, boolean[] visit, int iNode) {
        visit[iNode] = true;
        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[iNode][i] == 1 && !visit[i]) {
                dfs(isConnected, visit, i);
            }
        }
    }
}