package hashmap;

import java.util.HashMap;

// Minimum Index Sum of Two Lists
// Given two arrays of strings list1 and list2,
// find the common strings with the least index sum.
//
// A common string is a string that appeared in both list1 and list2.
// A common string with the least index sum is a common string such that
// if it appeared at list1[i] and list2[j]
// then i + j should be the minimum value among all the other common strings.
//
// Return all the common strings with the least index sum. Return the answer in any order.
//
// 1 <= list1.length, list2.length <= 1000
// 1 <= list1[i].length, list2[i].length <= 30
// list1[i] and list2[i] consist of spaces ' ' and English letters.
// All the strings of list1 are unique.
// All the strings of list2 are unique.
// There is at least a common string between list1 and list2.
public class MinIndexSumTwoLists {

    // TODO. HashMap<String,Integer>存储唯一字符串和对应的坐标关系
    // list1 = ["Shogun","Tapioca Express","Burger King","KFC"],
    // list2 = ["Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"]
    // -> ["Shogun"]
    //
    // list1 = ["Shogun","Tapioca Express","Burger King","KFC"],
    // list2 = ["KFC","Shogun","Burger King"]
    // -> ["Shogun"]
    // -> sum = (0 + 1) = 1.
    //
    // list1 = ["happy","sad","good"],
    // list2 = ["sad","happy","good"]
    // -> ["sad","happy"]
    // "happy" with index sum = (0 + 1) = 1.
    // "sad" with index sum = (1 + 0) = 1.
    //
    // O(N + M + L) 遍历两个数组的全部值
    // O(M + L)     L是结果字符串的数量
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> str1IndexMap = new HashMap<>();
        for (int index = 0; index < list1.length; index++) {
            str1IndexMap.put(list1[index], index);
        }

        int offset = 0;
        String[] tempStrings = new String[list2.length];

        int minSumIndex = list1.length + list2.length;
        for (int index = 0; index < list2.length; index++) {
            String str2 = list2[index];
            if (str1IndexMap.containsKey(str2)) {
                int sumIndex = str1IndexMap.get(str2) + index;
                if (minSumIndex > sumIndex) {
                    minSumIndex = sumIndex;
                    offset = 0;
                }
                // 追加相同Sum或者更小Sum的字符串
                if (minSumIndex == sumIndex) {
                    tempStrings[offset] = str2;
                    offset++;
                }
            }
        }

        String[] result = new String[offset];
        System.arraycopy(tempStrings, 0, result, 0, offset);
        return result;
    }
}
