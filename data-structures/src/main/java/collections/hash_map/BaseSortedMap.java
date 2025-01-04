package collections.hash_map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// TODO. 对HashTable<K,V>中的数据进行排序
// - 基于Key键的值来排序Entry
// - 基于Value的值来排序Entry
public class BaseSortedMap {

    // TODO. HashMap支持根据Value来排序数据
    public static void main(String[] args) {
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
