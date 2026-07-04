package string;

import java.util.*;

// Prefix Generation based on ID count
// 根据统计的字符数量来动态生成Prefix+单个字符的组合形式
// 假设每个字符的重复次数不超过26个, 分配的字符区间为[A,Z]
// 假设所有字符都是大写字符
public class GroupIdEncountered {

    // TODO. 一次遍历, 直接使用常量数组统计每个字符数量
    // prefix: OK00
    // input: AAABBCDEFABDSKSAKQ
    // output: OK00A, OKA00A, OKB00A, OK00B, OKA00B ... OK00C
    //
    // O(N) N is number of chars
    // O(N)
    public static List<String> buildFullId(String prefix, String str) {
        if (str == null || str.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        int[] countChars = new int[26];
        for (char c : str.toCharArray()) {
            if (countChars[c - 'A'] == 0) {
                result.add(prefix + "00" + c);
            } else {
                char charInserted = (char) ('A' + countChars[c - 'A'] - 1);
                result.add(prefix + charInserted + "00" + c);
            }
            countChars[c - 'A']++;
        }
        return result;
    }

    // TODO. 一个Map, 直接使用Map统计字符串的数量
    // prefix: OK00
    // input: ABC, ABC, BBC, ABC, BBC, FFF ....
    // output: OK00ABC, OKA00ABC, OK00BBC, OKB00ABC, OKA00BBC, OK00FFF ...
    public static List<String> buildFullIdWithList(String prefix, List<String> values) {
        if (values == null || values.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String value : values) {
            if (map.containsKey(value)) {
                char charInserted = (char) ('A' + map.get(value) - 1);
                result.add(prefix + charInserted + "00" + value);
                map.put(value, map.get(value) + 1);
            } else {
                result.add(prefix + "00" + value);
                map.put(value, 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();
        inputs.add("ABC");
        inputs.add("BBC");
        inputs.add("ABC");
        inputs.add("ABC");
        inputs.add("BBC");
        inputs.add("FFF");

        List<String> result = buildFullIdWithList("OK", inputs);
        for (String str : result) {
            System.out.println(str);
        }
    }
}
