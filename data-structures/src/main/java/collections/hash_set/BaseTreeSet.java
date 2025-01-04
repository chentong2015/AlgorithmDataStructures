package collections.hash_set;

import java.util.Comparator;
import java.util.TreeSet;

public class BaseTreeSet {

    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });

        
    }
}
