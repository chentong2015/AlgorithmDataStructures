package master.bfs_searching;

import java.util.*;

// Open the Lock
// 4 circular wheels. Each wheel has 10 slots (0-9)(9-0)
// The wheels can rotate freely and wrap around: turn '9' to be '0', or '0' to be '9'.
// Each move consists of turning one wheel one slot.
//
// The lock initially starts at '0000', can not reach "deadends"
// Given a target representing the value of the wheels that will unlock the lock,
// return the minimum total number of turns, -1 if it's impossible
//
// 1 <= deadends.length <= 500
// deadends[i].length == 4
// target.length == 4
// target will not be in the list deadends.
// target and deadends[i] consist of digits only.
public class OpenTheLock {

    // TODO. 金典BFS算法: 遍历Graph图的联通性问题
    // There are 10000 nodes (strings '0000' to '9999'),
    // There is an edge between two nodes
    // - if they differ in one digit, that digit differs by 1
    //   (wrapping around, so '0' and '9' differ by 1)
    // - if both nodes are not in deadends.
    //
    // deadends = ["0201","0101","0102","1212","2002"], target = "0202" -> 6
    // "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"
    //
    // deadends = ["8888"], target = "0009" -> 1
    // "0000" -> "0009"
    //
    // O(10000 + 10000*4) 最多常量次数的循环
    // O(10000)           最多常量数据存储到Queue中

    public int openLock(String[] deadends, String target) {
        // 同时存储阻塞位置和以遍历过的位置
        HashSet<String> deadSet = new HashSet<>();
        Collections.addAll(deadSet, deadends);
        if(deadSet.contains("0000")){
            return -1;
        }

        Queue<String> queue = new ArrayDeque<>();
        queue.add("0000");
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int index = 0; index < size; index++) {
                String current = queue.poll();
                if (current.equals(target)) {
                    return level;
                }
                addNextLocks(queue, deadSet, current);
            }
            level++;
        }
        return -1;
    }

    // 每个char字符都有增减两种可能: 循环表示 + 修改完再恢复/或每次重新取 !!
    private void addNextLocks(Queue<String> queue, Set<String> deadSet, String currentLock) {
        for (int index = 0; index < 4; index++) {
            char ch = currentLock.charAt(index);
            char chLeft = (ch == '0') ? '9' : (char) (ch - 1);
            char chRight = (ch == '9') ? '0' : (char) (ch + 1);

            char[] chars = currentLock.toCharArray();
            chars[index] = chLeft;
            String newLock = new String(chars);
            if (!deadSet.contains(newLock)) {
                deadSet.add(newLock);
                queue.add(newLock);
            }

            chars[index] = chRight;
            newLock = new String(chars);
            if (!deadSet.contains(newLock)) {
                deadSet.add(newLock);
                queue.add(newLock);
            }
        }
    }
}
