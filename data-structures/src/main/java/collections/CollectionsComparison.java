package collections;

import java.util.Collections;
import java.util.Comparator;

public class CollectionsComparison {

    // TODO. 两种反转排序的API
    public static void main(String[] args) {
        Collections.reverseOrder();
        Comparator.reverseOrder();
    }

    // Comparator.comparing()
    // Comparator.comparingInt() 源码完整的三种比较结果
    public static int compare(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }


    // TODO. 实现Comparable接口
    // 1. compareTo()不能跨不同类型进行比较
    // 2. compareTo()必须满足自反性，对称性，传递性
    class ItemWithComparator implements Comparable<ItemWithComparator> {

        private String name;

        @Override
        public int compareTo(ItemWithComparator item) {

            return this.name.compareTo(item.name);
        }
    }

    // TODO. 定义Comparator比较器
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

