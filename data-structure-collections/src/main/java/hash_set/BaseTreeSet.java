package hash_set;

import java.util.Comparator;
import java.util.TreeSet;

public class BaseTreeSet {

    // TODO. 默认根据元素的“自然大小”排序
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(10);
        treeSet.add(6);
        treeSet.add(8);
        treeSet.add(3);
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
