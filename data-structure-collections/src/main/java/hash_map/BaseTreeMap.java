package hash_map;

import java.util.*;

// TreeMap<K,V> 排序的Hash表结构，底层使用红黑树来实现
// - 默认根据Key值进行自然排序
// - 自定义比较器实现根据Key值的倒叙排序
public class BaseTreeMap {

    public static void main(String[] args) {
        // TODO. 默认根据Key值自然排序Entry数据
        TreeMap<Integer, Integer> treeMap0 = new TreeMap<>();
        treeMap0.put(5, 50);
        treeMap0.put(3, 30);
        treeMap0.put(9, 90);

        // TODO. 自定义根据Key键值倒叙排序，但无法根据Value值来排序 ！
        TreeMap<Integer, String> treeMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else if (o1 < o2) {
                    return -1;
                }
                return 0;
            }
        });
        treeMap.put(10, "item 10");
        treeMap.put(5, "item 5");
        treeMap.put(7, "item 7");
    }

    private void testTreeMapAPI() {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        // 返回树"第一个结点"key值(排序后最小的结点值)
        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.firstEntry().getValue());

        // TODO: 返回大于等于某key值的子树结构(排序好序子树)
        SortedMap<Integer, Integer> sortedMap = treeMap.tailMap(4);
        System.out.println(sortedMap.firstKey());
        System.out.println(sortedMap.size());

        // 通过TreeMap的排序之后，找到ceiling天花板元素或者floor地板元素
        NavigableMap<Integer, Integer> intervalMap = new TreeMap<>();
        intervalMap.put(1, 2);
        intervalMap.put(2, 5);
        intervalMap.put(5, 10);
        Map.Entry<Integer, Integer> ceilingEntry = intervalMap.ceilingEntry(2);
        Map.Entry<Integer, Integer> floorEntry = intervalMap.floorEntry(2);
    }
}
