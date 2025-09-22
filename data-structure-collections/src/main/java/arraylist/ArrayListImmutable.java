package arraylist;

import java.util.Collections;
import java.util.List;

public class ArrayListImmutable {

    public static void main(String[] args) {
        // TODO. List.of()构建列表不可再被修改
        List<Integer> needs = List.of(2,3);
        // needs.add(12);

        // TODO. singletonList()创建的列表不可修改
        List<String> values = Collections.singletonList("value");
        // values.add("new value");

        // TODO. 返回空List对象的标准方式
        List<String> empty = Collections.emptyList();
    }
}
