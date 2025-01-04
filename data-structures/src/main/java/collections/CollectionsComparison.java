package collections;

import java.util.Comparator;

// 自定义Java中对象的比较器: 通过实现接口来完成
// 1. interface Comparator<T>
// 2. interface Comparable<T>
public class CollectionsComparison {

    // TODO. 实现Comparable接口的原则
    // 1. compareTo()不能跨不同类型进行比较
    // 2. compareTo()必须满足自反性，对称性，传递性
    class ItemWithComparator implements Comparable<ItemWithComparator> {

        private String name;

        @Override
        public int compareTo(ItemWithComparator item) {

            return this.name.compareTo(item.name);
        }
    }

    // TODO. 定义完整的Comparator比较器有三种返回结果
    // 定义匿名类比较器, 指定类型T在比较时候所遵循的条件和逻辑
    // Collections.sort(itemList, new itemComparator());
    public final Comparator<Item> itemComparator = new Comparator<Item>() {
        @Override
        public int compare(Item item1, Item item2) {
            if (item1.number > item2.number) {
                return 1;
            } else if (item1.number < item2.number) {
                return -1;
            }
            return 0;
        }
    };

    public class Item {
        int number;
        String name;
    }
}

