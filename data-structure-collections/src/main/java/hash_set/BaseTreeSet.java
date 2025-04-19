package hash_set;

import java.util.Comparator;
import java.util.TreeSet;

public class BaseTreeSet {

    public static void main(String[] args) {
        // TODO. 默认根据元素“自然大小”排序
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(10);
        treeSet.add(6);
        treeSet.add(8);
        treeSet.add(3);
        treeSet.add(5);
        treeSet.add(4);

        // TODO. 调用API找到最接近的值
        // Returns the greatest element in this set strictly less than the given element
        System.out.println(treeSet.lower(5)); // 4
        System.out.println(treeSet.contains(5)); // true
        // Returns the least element in this set strictly greater than the given element
        System.out.println(treeSet.higher(5)); // 6

        // TODO. 找到等于或更大/更小值(可能返回Null)
        Integer high = treeSet.ceiling(5);
        Integer low = treeSet.floor(5);

        for (int item: treeSet) {
            System.out.println(item);
        }
    }

    // TODO. 自定义元素的Comparator比较器规则(实现倒叙)
    private static void testTreeSet() {
        TreeSet<String> treeSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        treeSet.add("item1");
        treeSet.add("item4");
        treeSet.add("item3");
        treeSet.add("item2");
        for (String item: treeSet) {
            System.out.println(item);
        }
    }
}
