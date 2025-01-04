package collections.hash_set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// TODO. Hash用于方便的计算集合之间的交集合和并集合
public final class BaseHashSet {

    public static void main(String[] args) {
        Set<Integer> squares = new HashSet<>();
        Set<Integer> cubes = new HashSet<>();
        for (int i = 1; i <= 10; i++) {
            squares.add(i * i);
            cubes.add(i * i * i);
        }

        // Outer Join: Union 合并的时候，不会添加同样的Item到Set中
        Set<Integer> union = new HashSet<>(squares);
        union.addAll(cubes);

        // Inner Join: retainAll() 提取公共部分，保留全部
        Set<Integer> intersection = new HashSet<>(squares);
        intersection.retainAll(cubes);


        Set<String> nature = new HashSet<>();
        String[] natureWords = {"all", "nature", "is", "but", "art"};
        nature.addAll(Arrays.asList(natureWords));

        Set<String> divine = new HashSet<>();
        String[] divineWords = {"to", "err", "is", "human", "all"};
        divine.addAll(Arrays.asList(divineWords));

        // Left Join: removeAll() 移除指定的数据,
        Set<String> diff1 = new HashSet<>(nature);
        diff1.removeAll(divine); // {"nature", "but", "art"};

        // ContainsAll() 判断子集关系，不改变Set，只是返回判断的结果
        if (nature.containsAll(divine)) {
            System.out.println("Divine is a subSet of nature");
        }
    }
}
