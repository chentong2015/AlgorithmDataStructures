package collections.hash_map;

import java.util.*;

// TODO. 对HashTable<K,V>中的数据进行排序
// - 基于Key键的值来排序Entry
// - 基于Value的值来排序Entry
public class BaseSortedMap {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(10, "item10");
        map.put(5, "item5");
        map.put(8, "item8");
        map.put(7, "item7");
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .forEach(System.out::println);

        // TODO. 排序后输出成List<Entry>列表集合
        List<Map.Entry<Integer, String>> listEntry = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .toList();
        for (Map.Entry<Integer, String> entry: listEntry) {
            System.out.println(entry.getKey() + " > " + entry.getValue());
        }

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
