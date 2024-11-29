package data_structure.arraystring.anagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// TODO. HashMap<Key,Value> 金典场景: 对数据进行分组，处理单位是一个完整字符串
public class AnagramsGroup {

    // TODO. Value值和Result结果共用同一个对象引用，避免重复开辟内层空间 !!
    // - 每个str的key值需要计算一次
    // - 每个str key的分组能够在O(1)时间内找到
    // HashMap: Key->List<String> group
    // return map.values().stream().toList();
    // return new ArrayList(map.values());
    //
    // O(N)      N is total number of letters
    // O(26 * n) n is number of strings 每个key都是由26个字符组成
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> results = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for(String str: strs) {
            String key = generateAnagramsKey(str);
            if(map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
                results.add(list);
            }
        }
        return results;
    }

    // TODO. 基于char[]数组为生成唯一Anagrams Key键值字符串
    private String generateAnagramsKey(String str) {
        char[] arr = new char[26];
        for(char cur: str.toCharArray()) {
            arr[cur-'a']++;
        }
        return String.valueOf(arr);
    }
}
