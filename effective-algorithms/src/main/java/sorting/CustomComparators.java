package sorting;

import java.util.Comparator;

// TODO. 特定类型的Comparator比较器
// Comparator.comparing(new Function<T, T>());
// Comparator.comparingInt(ToIntFunction<? super T>)
// Comparator.comparingDouble(ToDoubleFunction<? super T>)
public class CustomComparators {

    // TODO. 实现Comparable接口, 定义新的比较器类型
    // 1. compareTo() 不能跨不同类型进行比较
    // 2. compareTo() 必须满足自反性，对称性，传递性
    static class ItemWithComparator implements Comparable<ItemWithComparator> {
        private String name;

        @Override
        public int compareTo(ItemWithComparator item) {
            return this.name.compareTo(item.name);
        }
    }

    // TODO. 定义Comparator匿名类比较器
    public final Comparator<Item> itemComparator = new Comparator<Item>() {
        @Override
        public int compare(Item item1, Item item2) {
            if (item1.number > item2.number) {
                // 返回正数表示item1应该放在item2前面
                return 1;
            } else if (item1.number < item2.number) {
                // 返回负数表示item1应该放在item2后面
                return -1;
            }
            // 再根据第二个条件进行排序
            return item1.name.compareTo(item2.name);
        }
    };

    public class Item {
        int number;
        String name;
    }
}

