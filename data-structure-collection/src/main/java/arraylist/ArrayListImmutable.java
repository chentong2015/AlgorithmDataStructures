package arraylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListImmutable {

    public static void main(String[] args) {
        // TODO. List.of() 列表不可修改
        List<Integer> needs = List.of(2,3);
        // needs.add(12);

        // TODO. singletonList() 列表不可修改
        List<String> values = Collections.singletonList("value");
        // values.add("new value");

        // TODO. emptyList() 列表不可修改
        List<String> empty = Collections.emptyList();
        // empty.add("value");

        // 重新封装成ArrayList可修改
        List<String> emptyNotImmutable = new ArrayList<>(empty);
        emptyNotImmutable.add("add");
        emptyNotImmutable.addAll(values);
        emptyNotImmutable.addAll(empty);

        System.out.println(emptyNotImmutable);
    }
}
