package arraylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO. 注意三种不可变的ArrayList: List.of() .singletonList() .emptyList() 不能再被修改
public class ArrayListImmutable {

    public static void main(String[] args) {
        List<Integer> needs = List.of(2,3);
        List<String> values = Collections.singletonList("value");
        List<String> empty = Collections.emptyList();

        // TODO. 将不可变ArrayList包装成可变: 支持添加新值和不可变List
        List<String> emptyNotImmutable = new ArrayList<>(empty);
        emptyNotImmutable.add("add");
        emptyNotImmutable.addAll(values);
        emptyNotImmutable.addAll(Collections.emptyList());

        System.out.println(emptyNotImmutable);
    }
}