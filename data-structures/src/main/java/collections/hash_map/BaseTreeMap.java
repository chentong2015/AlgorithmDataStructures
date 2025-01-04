package collections.hash_map;

import java.util.*;

// TreeMap<K,V>
// 底层使用红黑树来实现, 使用key来进行结点的比较
// 使用TreeMap来排序和根据区间来快速找寻(利用红黑树的查询效率)范围值
public class BaseTreeMap {

    public static void main(String[] args) {
        // TODO. 创建TreeMap时传递自定义的类型比较器
        TreeMap<Integer, Integer> treeMap = new TreeMap<>((o1, o2) -> {
            if (o1 > o2) {
                return 1;
            } else if (o1 < o2) {
                return -1;
            }
            return 0;
        });
        treeMap.put(5, 50);
        treeMap.put(3, 30);
        treeMap.put(9, 90);
        treeMap.put(4, 40);

        // 返回树"第一个结点"key值(排序后最小的结点值)
        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.firstEntry().getValue());
        System.out.println("-----");

        // TODO: 返回大于等于某key值的子树结构(排序好序子树)
        SortedMap<Integer, Integer> sortedMap = treeMap.tailMap(4);
        System.out.println(sortedMap.firstKey());
        System.out.println(sortedMap.size());
    }

    // 通过TreeMap的排序之后，找到ceiling天花板元素或者floor地板元素
    private void testTreeMap2() {
        NavigableMap<Integer, Integer> intervalMap = new TreeMap<>();
        intervalMap.put(1, 2);
        intervalMap.put(2, 5);
        intervalMap.put(5, 10);
        Map.Entry<Integer, Integer> ceilingEntry = intervalMap.ceilingEntry(2);
        Map.Entry<Integer, Integer> floorEntry = intervalMap.floorEntry(2);
    }
}
