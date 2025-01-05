package collections.arraylist;

import java.util.ArrayList;
import java.util.List;

public class BaseArrayListConvert {

    // TODO. List列表转换成数组
    public static void main(String[] args) {
        List<String> myList = new ArrayList<>();
        myList.add("item1");
        myList.add("item2");

        String[] myArray = new String[myList.size()];
        myArray = myList.toArray(myArray);
        myArray = (String[]) myList.toArray();
    }

    private void convertListToArray(List<String> myList) {
        String[] myArray = new String[myList.size()];
        myArray = myList.toArray(myArray);

        // Object[] -> String[]
        String[] myArray02 = (String[]) myList.toArray();
    }
}
