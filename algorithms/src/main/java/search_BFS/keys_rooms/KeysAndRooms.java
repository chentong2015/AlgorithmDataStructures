package search_BFS.keys_rooms;

import java.util.*;

// Keys and Rooms
// Each room has a distinct number in 0, 1, 2, ..., N-1,
// each room may have some keys to access the next room
//
// Each room i has a list of keys rooms[i], each key rooms[i][j] is an integer in [0, 1, ..., N-1]
// A key rooms[i][j] = v opens the room with number v
// Initially, all the rooms start locked (except for room 0)
// You can walk back and forth between rooms freely
//
// Return true if and only if you can enter every room
public class KeysAndRooms {

    // TODO. 从每一个点往下展开，一层一层进行查找
    // 依次根据拿到的钥匙，开启后面的房间，然后获得钥匙...
    // [[1],[2],[3],[]]        -> true
    // [[1,3],[3,0,1],[2],[0]] -> false 始终没有办法开启index=2的房间
    //
    // O(N) N是总的key的数量
    // O(n) n是房间的数目
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> openRooms = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        openRooms.add(0);
        for (int key : rooms.get(0)) {
            queue.add(key);
            openRooms.add(key);
        }

        // TODO. 通过queue队列的非空来实现层级遍历
        while (!queue.isEmpty()) {
            int index = queue.poll();
            for (int key : rooms.get(index)) {
                // 只有是新的房间才需要添加并遍历
                if (!openRooms.contains(key)) {
                    queue.add(key);
                    openRooms.add(key);
                }
            }
        }
        // 所有的钥匙都拿到，所有的门都打开
        return openRooms.size() == rooms.size();
    }
}
