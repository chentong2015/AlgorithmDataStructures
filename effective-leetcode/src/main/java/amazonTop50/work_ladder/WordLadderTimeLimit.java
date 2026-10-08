package amazonTop50.work_ladder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TODO. DFS暴力递归导致复杂度过高
public class WordLadderTimeLimit {

    // TODO. 每次移动只能修改一个位置的字符
    // "lest" -> "lose" 改了两个字符
    // "lest" -> "lost" 只改单个字符
    //
    // "hit", "cog", ["hot","dot","dog","lot","log","cog"] -> 5
    // "hit" -> "hot" -> "dot" -> "dog" -> cog"
    //
    // "hit", "cog", ["hot","dot","dog","lot","log"] -> 0
    //
    // "leet" "code" ["lest","leet","lose","code","lode","robe","lost"] -> 6

    private int minSteps = Integer.MAX_VALUE;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<Integer> leftIdSet = new HashSet<>();
        for (int index = 0; index < wordList.size(); index++) {
            leftIdSet.add(index);
        }
        dfs(wordList, leftIdSet, beginWord, endWord, 1);
        return minSteps == Integer.MAX_VALUE ? 0: minSteps;
    }

    private void dfs(List<String> wordList, Set<Integer> leftIdSet, String currentWord, String endWord, int steps) {
        if (currentWord.equals(endWord)) {
            minSteps = Math.min(minSteps, steps);
            return;
        }

        for (int index : leftIdSet) {
            if (canTransform(currentWord, wordList.get(index))) {
                HashSet<Integer> tempSet = new HashSet<>(leftIdSet);
                tempSet.remove(index);
                dfs(wordList, tempSet, wordList.get(index), endWord, steps + 1);
            }
        }
    }

    private boolean canTransform(String startStr, String endStr) {
        int countDiff = 0;
        for (int index = 0; index < startStr.length(); index++) {
            if (startStr.charAt(index) != endStr.charAt(index)) {
                countDiff++;
            }
        }
        return countDiff == 1;
    }
}
