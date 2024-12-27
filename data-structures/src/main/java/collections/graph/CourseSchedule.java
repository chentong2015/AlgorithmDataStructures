package collections.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Course Schedule
// There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
// You are given an array prerequisites where prerequisites[i] = [ai, bi]
// indicates that you must take course bi first if you want to take course ai.
//
// For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
// Return true if you can finish all courses. Otherwise, return false.
//
// numCourses = 2, prerequisites = [[1,0]] -> true
// numCourses = 2, prerequisites = [[1,0],[0,1]] -> false
//
// 题目数据的约束公式非常重要，保证算法的逻辑性
// 1 <= numCourses <= 2000
// 0 <= prerequisites.length <= 5000
// prerequisites[i].length == 2
// 0 <= ai, bi < numCourses
// All the pairs prerequisites[i] are unique. 保证Mapping关系不会对此添加
public class CourseSchedule {

    // TODO. 最后答案只需要判断是否能够形成通路
    //  - 不考虑最后形成的通路的路线是什么，不需要构建任何图形，不重要 ！
    //  - 只考虑不能形成通路的反面条件，什么前提条件会造成错误 ！
    // This problem is equivalent to finding if a cycle exists in a directed graph.
    // If a cycle exists, no topological ordering exists
    // therefore it will be impossible to take all courses.
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> courseMap = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i++) {
            int before = prerequisites[i][1];
            int after = prerequisites[i][0];
            if (before == after) {
                return false; // Not possible
            }

            if (courseMap.containsKey(after)) {
                if (dfs(courseMap, after, before)) {
                    return false;
                }
            }

            if (courseMap.containsKey(before)) {
                courseMap.get(before).add(after);
            } else {
                List<Integer> followings = new ArrayList<>();
                followings.add(after);
                courseMap.put(before, followings);
            }
        }
        return true;
    }

    // TODO. 使用DFS暴力破解会造成非常大的时间复杂度 => 不推荐
    // Check if there is circle condition
    private boolean dfs(Map<Integer, List<Integer>> courseMap, int startKey, int target) {
        boolean isFoundTarget = false;
        if (courseMap.containsKey(startKey)) {
            for (int item: courseMap.get(startKey)) {
                if (item == target) {
                    isFoundTarget = true;
                    break;
                } else {
                    isFoundTarget = dfs(courseMap, item, target);
                }
            }
        }
        return isFoundTarget;
    }
}
