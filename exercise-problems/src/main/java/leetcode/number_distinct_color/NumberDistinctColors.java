package leetcode.number_distinct_color;

import java.util.HashMap;

// Find the Number of Distinct Colors Among the Balls
// There are limit + 1 balls with distinct labels in the range [0, limit].
// For every query in queries that is of the form [x, y], you mark ball x with the color y.
// After each query, you need to find the number of distinct colors among the balls.
//
// Return an array result of length n,
// where result[i] denotes the number of distinct colors after ith query.
// 返回每一轮离散颜色的数目
public class NumberDistinctColors {

    // TODO. 算法思路: 双HashMap, 必须记录出现过的颜色统计(在移除时降低统计)
    // limit = 4, queries = [[1,4],[2,5],[1,3],[3,4]]
    // 0
    // 1 -> 4 -> 3
    // 2 -> 5
    // 3 -> 4
    //
    // limit = 1, queries = [0,1],[1,4],[1,1],[1,4],[1,1]
    // 0 -> 1
    // 1 -> 4 -> 1 -> 4
    //
    // limit = 1, queries = [[0,1],[0,4],[0,4],[0,1],[1,2]]
    // 0 -> 1 -> 4 -> 4 -> 1
    // 1 -> 2
    public static int[] queryResults(int limit, int[][] queries) {
        int index = 0;
        int[] result = new int[queries.length];
        int count = 0;
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        HashMap<Integer, Integer> colorCountMap = new HashMap<>();

        for (int[] query: queries) {
            int key = query[0];
            int newColor = query[1];

            // TODO. 处理旧的ID，移除旧的不同颜色
            if (indexMap.containsKey(key)) {
                int oldColor = indexMap.get(key);
                if (oldColor == newColor) {
                    result[index++] = count;
                    continue;
                }

                // 判断移除的旧颜色是否只有一个，重新统计颜色
                if (colorCountMap.get(oldColor) == 1) {
                    colorCountMap.remove(oldColor);
                    count--;
                } else {
                    colorCountMap.put(oldColor, colorCountMap.get(oldColor) - 1);
                }
            }

            // TODO. 处理新的ID + 处理新的颜色
            indexMap.put(key, newColor);
            if (colorCountMap.containsKey(newColor)) {
                // 新的颜色已经存在，则不增加统计
                colorCountMap.put(newColor, colorCountMap.get(newColor) + 1);
            } else {
                colorCountMap.put(newColor, 1);
                count++;
            }

            result[index++] = count;
        }
        return result;
    }
}
