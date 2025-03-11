package leetcode3.website_visist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// Analyze User Website Visit Pattern
// Input:
//   username = ["joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"],
//   timestamp = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
//   website = ["home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"]
// Output:
//   ["home", "about", "career"]
public class UserWebsiteVisitPattern {

    private int maxCount = 0;
    private String resultSequence = "";

    // String -> Set<String>
    // - Key键值是3个网站的组合
    // - Value值是用户的集合，用户快速判断用户是否访问过
    private final HashMap<String, Set<String>> counterMap = new HashMap<>();

    public String find3SequenceWebsiteVisited(String[] usernames, String[] websites) {
        int index = 0;
        while (index < usernames.length) {
            int right = index + 1;
            while (right < usernames.length && usernames[index].equals(usernames[right]))  {
                right++;
            }

            // 长度至少大于三，才有统计结果
            if (right - index >= 3) {
                count3SequenceWebsite(usernames[index], websites, index, right);
            }
            index= right;
        }
        return resultSequence;
    }

    // O(N*N*N) 必须遍历所有3Sequence的组合可能
    // O(K + U) 存储3Sequence的所有可能，存储每个Key值对应的离散用户
    private void count3SequenceWebsite(String username, String[] websites, int start, int end) {
        for (int i = start; i < end; i++) {
            for (int j = i+1; j < end; j++) {
                for (int k = j+1; k < end; k++) {
                    String key = buildKey(websites, i, j, k);
                    if (counterMap.containsKey(key)) {
                        // Set集合可以直接添加元素，自动剔除重复值
                        counterMap.get(key).add(username);
                    } else {
                        Set<String> set = new HashSet<>();
                        set.add(username);
                        counterMap.put(key, set);
                    }
                    int count = counterMap.get(key).size();
                    if (count > maxCount) {
                        maxCount = count;
                        resultSequence = key;
                    } else if (count == maxCount) {
                        if (key.compareTo(resultSequence) < 0) {
                            resultSequence = key;
                        }
                    }
                }
            }
        }
    }

    // 生成用于唯一判断的Key键值
    private String buildKey(String[] websites, int i, int j, int k) {
        return websites[i] + "-" + websites[j] + "-" + websites[k];
    }
}
