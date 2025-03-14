package collections.hash_map;

import java.util.*;

// 利用Stream对HashTable<K,V>数据进行排序
public class BaseSortedMap {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(10, "item10");
        map.put(5, "item5");
        map.put(8, "item8");
        map.put(7, "item7");

        // TODO. 返回排序结果List<Entry>列表
        List<Map.Entry<Integer, String>> listEntry = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .toList();
        for (Map.Entry<Integer, String> entry: listEntry) {
            System.out.println(entry.getKey() + " > " + entry.getValue());
        }

        // TODO. 基于Key值的反序排列
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach(System.out::println);

        // TODO. 基于Value值的反序排列
        Map<Character, Integer> countMap = new HashMap<>();
        countMap.put('c', 10);
        countMap.put('a', 15);
        countMap.put('b', 5);
        countMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }
}
