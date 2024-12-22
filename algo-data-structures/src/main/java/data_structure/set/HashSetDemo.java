package data_structure.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 1. 没有顺序限制, 不能够取指定位置的Item
// 2. 不能包含重复的数据, 默认会通过equals()来判断比较其中的元素
//
// Set<E>接口的实现
// - HashSet<E>       不保证迭代的顺序，顺序不固定: 没有第一个和最后一个元素的概念
//   - LinkedHashSet<E> 迭代顺序和插入映射中顺序一致
//   - TreeSet<E>       排序SortedSet，通过Tree实现对key的自然排序
public final class HashSetDemo {

    public static void main(String[] args) {
        Set<Integer> squares = new HashSet<>();
        Set<Integer> cubes = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            squares.add(i * i);
            cubes.add(i * i * i);
        }
        // Union 合并的时候，不会添加同样的Item到Set中  Outer Join
        Set<Integer> union = new HashSet<>(squares);
        union.addAll(cubes);
        System.out.println("The amount size is " + union.size());

        // Intersection: retainAll() 提取公共部分，保留全部 Inner Join
        Set<Integer> intersection = new HashSet<>(squares);
        intersection.retainAll(cubes);

        // removeAll() 移除指定的数据
        Set<String> nature = new HashSet<>();
        String[] natureWords = {"all", "nature", "is", "but", "art"};
        nature.addAll(Arrays.asList(natureWords));

        Set<String> divine = new HashSet<>();
        String[] divineWords = {"to", "err", "is", "human", "all"};
        divine.addAll(Arrays.asList(divineWords));

        Set<String> diff1 = new HashSet<>(nature);
        diff1.removeAll(divine); // {"nature", "but", "art"}; Left Join 等效于SQL中的实现

        // ContainsAll() 判断一个Set是否是另一个Set的子集
        // 不改变Set，只是返回判断的结果
        if (nature.containsAll(divine)) {
            System.out.println("Divine is a subSet of nature");
        }
    }
}
