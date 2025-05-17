package hash_set;

import java.util.Comparator;
import java.util.TreeSet;

// TODO. Ordered Set 有排序的Set集合
public class BaseTreeSet {

    // TreeSet<>插入和查找都是logN时间复杂度
    public static void main(String[] args) {
        // 默认根据元素“自然大小”排序
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(10);
        treeSet.add(6);
        treeSet.add(8);
        treeSet.add(3);
        treeSet.add(5);
        treeSet.add(4);
        System.out.println(treeSet.contains(5)); // true

        // TODO. 找到最接近的严格>或<的值
        System.out.println(treeSet.lower(5));  // 4
        System.out.println(treeSet.higher(5)); // 6

        // TODO. 找到最接近的>=或<=值(可能返回Null)
        System.out.println(treeSet.ceiling(5));// 5
        System.out.println(treeSet.floor(5));  // 5

        // 自定义Comparator比较器规则(实现倒叙)
        TreeSet<String> treeSetReverse = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        treeSetReverse.add("item1");
        treeSetReverse.add("item4");
        treeSetReverse.add("item3");
        treeSetReverse.add("item2");
        for (String item: treeSetReverse) {
            System.out.println(item);
        }
    }
}
