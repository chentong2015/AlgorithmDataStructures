package collections.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashSetTest {

    public HashSetTest(String name, double period) {}

    // 可以直接将List列表构造成HashSet<T>
    public void testHashSet(List<String> words) {
        Set<String> set = new HashSet<>(words);
    }

    // 通过重写BaseHashSet类型的equal()方法，自定义对象的比较方法，值下面添加的是两个不同的对象
    public void compareItemsInSet() {
        Set<HashSetTest> mySets = new HashSet<>();
        mySets.add(new HashSetTest("name", 100));
        mySets.add(new HashSetTest("name", 200));
    }

    // Array -> ArrayList -> Set
    public void convertListAndSet() {
        String sentence = "One day in the year of the fox";
        String[] wordsArray = sentence.split(" ");
        List<String> wordsList = Arrays.asList(wordsArray);
        Set<String> words = new HashSet<>(wordsList);
        System.out.println(words);
    }
}
