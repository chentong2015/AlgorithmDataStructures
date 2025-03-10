package leetcode2;

import java.util.*;

// Smallest String With Swaps
// You are given a string s and an array of pairs of indices in the string pairs
// where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
// You can swap the characters at any pair of indices in the given pairs any number of times.
// Return the lexicographically smallest string that s can be changed to after using the swaps
//
// 1 <= s.length <= 10^5
// 0 <= pairs.length <= 10^5
// 0 <= pairs[i][0], pairs[i][1] < s.length
// s only contains lower case English letters.
public class SmallestStringWithSwaps {

    // TODO. 将Pair坐标位置进行分组，同组的字符串取最小值，最后拼接片段
    //
    // s = "dcab", pairs = [[0,3],[1,2]]
    // swap s[0] and s[3], s = "bcad"
    // swap s[1] and s[2], s = "bacd"
    //
    // s = "dcab", pairs = [[0,3],[1,2],[0,2]]
    // swap s[0] and s[3], s = "bcad"
    // swap s[0] and s[2], s = "acbd"
    // swap s[1] and s[2], s = "abcd"
    //
    // s = "cba", pairs = [[0,1],[1,2]]
    // swap s[0] and s[1], s = "bca"
    // swap s[1] and s[2], s = "bac"
    // swap s[0] and s[1], s = "abc"
    //
    // TODO. 一旦构成Group之后，组内的所有的字符取最小结果
    // 不同的组之间需要链接合并
    // 0 1 2 3 4 5
    // 0 -> {0, 3, 4, 2}
    // 1 -> {1, 5}
    //
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int[] root = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            root[i] = i;
        }

        // Union pairs
        for (List<Integer> pair : pairs) {
            union( pair.get(0),  pair.get(1), root);
        }

        // Group indices by their root parent
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int rootI = find(i, root);
            groups.computeIfAbsent(rootI, k -> new ArrayList<>()).add(i);
        }

        // Sort characters within each group
        char[] result = s.toCharArray();
        for (List<Integer> indices : groups.values()) {
            List<Character> chars = new ArrayList<>();
            for (int index : indices) {
                chars.add(s.charAt(index));
            }
            Collections.sort(chars);
            Collections.sort(indices);

            // Place sorted characters back into the result array
            for (int i = 0; i < indices.size(); i++) {
                result[indices.get(i)] = chars.get(i);
            }
        }
        return new String(result);
    }

    // 将Pair坐标进行联合，构成一个Group组
    private void union(int x, int y, int[] root) {
        int rootX = find(x, root);
        int rootY = find(y, root);
        if (rootX != rootY) {
            root[rootY] = rootX;
        }
    }

    private int find(int x, int[] root) {
        if (x == root[x]) {
            return x;
        }
        return root[x] = find(root[x], root);
    }
}
