package leetcode4;

import java.util.*;

// Avoid Flood in The City
// Your country has an infinite number of lakes.
// Initially, all the lakes are empty, but
// when it rains over the nth lake, the nth lake becomes full of water.
// If it rains over a lake that is full of water, there will be a flood.
//
// Your goal is to avoid floods in any lake.
// - rains[i] > 0 means there will be rains over the rains[i] lake.
// - rains[i] == 0 means there are no rains this day, you can choose one lake this day and dry it.
//
// Return an array ans where:
// - ans.length == rains.length
// - rains[i] > 0, ans[i] == -1
// - rains[i] == 0, ans[i] is the lake you choose to dry
//
// If there are multiple valid answers return any of them.
// If it is impossible to avoid flood return an empty array.
//
// Notice that if you chose to dry a full lake, it becomes empty,
// but if you chose to dry an empty lake, nothing changes.
// 1 <= rains.length <= 10^5
// 0 <= rains[i] <= 10^9
public class AvoidFloodInTheCity {

    // TODO. 只当出现Flood洪水时才Dry处理，否则无法根据需要选择Dry的rain
    // [1,2,3,4] -> [-1,-1,-1,-1]
    // After the first day full lakes are [1]
    // After the second day full lakes are [1,2]
    // After the third day full lakes are [1,2,3]
    // After the fourth day full lakes are [1,2,3,4]
    // There's no day to dry any lake and there is no flood in any lake.
    //
    // [1,2,0,0,2,1] -> [-1,-1,2,1,-1,-1]
    //
    // [1,2,0,1,2] -> []
    // After the second day, full lakes are [1,2].
    // We have to dry one lake in the third day.
    // After that, it will rain over lakes [1,2]
    //
    // 1 2 0 1 3
    // 1 2 0 2 3
    // 1 2 0 0 0 -> [-1,-1,1,1,1]
    // 0 0 0 0 0 -> [1,1,1,1,1]
    // 0 1 1     -> [] 前面的dry失效
    //
    // O(N * N)
    // O(N + N)
    public int[] avoidFlood(int[] rains) {
        // TODO. 使用LinkedList可以高效删除使用过的"Dry坐标'
        List<Integer> dryIndexList = new ArrayList<>();

        // rain -> index 存储降雨时的位置坐标
        Map<Integer, Integer> rainIndexMap = new HashMap<>();
        int[] result = new int[rains.length];
        for (int index = 0; index < rains.length; index++) {
            int rain = rains[index];
            if (rain == 0) {
                dryIndexList.add(index);
            } else {
                if (rainIndexMap.containsKey(rain)) {
                    // TODO. 找到前面最小的满足条件的"Dry坐标"进行处理
                    int indexBefore = rainIndexMap.get(rain);
                    boolean hasDry = false;
                    for (int i = 0; i < dryIndexList.size(); i++) {
                        if (dryIndexList.get(i) > indexBefore) {
                            result[dryIndexList.get(i)] = rain;
                            hasDry = true;
                            dryIndexList.set(i, -1);
                            break;
                        }
                    }
                    // 如果前面没有找到dry坐标来阻止洪水，则失败
                    if (!hasDry) {
                        return new int[]{};
                    }
                }
                rainIndexMap.put(rain, index);
                result[index] = -1;
            }
        }

        // 如果还有dry位置没有填满，补全要dry的rain的序号
        for (Integer integer : dryIndexList) {
            if (integer >= 0) {
                result[integer] = 1;
            }
        }
        return result;
    }
}
