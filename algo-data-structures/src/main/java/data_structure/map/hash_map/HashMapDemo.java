package data_structure.map.hash_map;

import java.util.*;

// HashMap<,>         对Map接口的实现，包含<key, value>键值对: 不保证元素的迭代顺序，不保证顺序不变 !!
//   LinkedHashMap<,> 确定迭代的顺序，必须是插入元素的顺序
//   TreeMap<>        对元素进行自然排序，或者提供Comparator
public class HashMapDemo {

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

    public void testHashMap() {
        Map<Integer, Integer> hashmap = new HashMap<>();
        hashmap.putIfAbsent(0, 0);
        hashmap.putIfAbsent(2, 3);
        hashmap.put(1, 1);
        hashmap.put(1, 2); // 更新已经存在的值
        System.out.println("The value of key 1 is: " + hashmap.get(1));

        hashmap.remove(2);
        // 直接判断键这值是否存在
        boolean foundKay = hashmap.containsKey(2);
        boolean foundValue = hashmap.containsValue(3);

        // 遍历所有的key的集合 hashmap.keySet()
        // 遍历所有的value集合 hashmap.values()
        // 使用Map.Entry<>来遍历每一对的键值.entrySet()
        for (Map.Entry<Integer, Integer> entry : hashmap.entrySet()) {
            System.out.print("(" + entry.getKey() + "," + entry.getValue() + ") ");
        }

        System.out.println("The size : " + hashmap.size());
        if (hashmap.isEmpty()) {
            System.out.println("hash map is empty now!");
        }
        hashmap.clear();
    }
}
