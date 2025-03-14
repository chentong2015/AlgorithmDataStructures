package collections.hash_map;

import java.util.*;

public class BaseHashMap {

    public static void main(String[] args) {
        // 可以设置HashMap初始容量，但HashMap会将其值优化成"二的幂次方"
        Map<Integer, Integer> hashmap = new HashMap<>(10);
        hashmap.put(1, 1);
        hashmap.put(1, 2); // 更新已经存在的值
        System.out.println(hashmap.get(1));  // 根据Key来获取Value
        System.out.println(hashmap.get(11)); // 不存在的Key返回Null

        // 利用if和or条件进行存储和取值
        hashmap.putIfAbsent(0, 0);
        hashmap.putIfAbsent(2, 3);
        hashmap.getOrDefault(1, -1);

        // 基于Key键值来移除Entry
        hashmap.remove(2);

        // TODO. 判断Key值是否存在 O(1)复杂度
        boolean foundKay = hashmap.containsKey(2);

        // TODO. 可以直接判断Value键值存在，造成O(n)复杂度
        boolean foundValue = hashmap.containsValue(3);

        // 遍历所有的key的集合 hashmap.keySet()
        // 遍历所有的value集合 hashmap.values()
        // 使用Map.Entry<>来遍历所有键值对.entrySet()
        for (Map.Entry<Integer, Integer> entry : hashmap.entrySet()) {
            System.out.print(entry.getKey() + "," + entry.getValue());
        }

        if (hashmap.isEmpty()) {
            System.out.println("hash map is empty now!");
        }

        System.out.println(hashmap.size());

        hashmap.clear();
    }
}
