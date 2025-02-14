package extra.random;

import java.security.SecureRandom;
import java.util.*;

// Random Drawing Lots
// 关于随机抽签匹配选择的问题: 存在平衡计算的问题
// 1. 数据中每个Item随机抽到一个目标Item
// 2. 所有Item都被抽取到，并且没有冲突
// 3. 两个Item不能互为对方的目标
public class RandomDrawingLots {

    // TODO. 正确的做法是随机打乱一组数据，然后将相邻Item进行匹配(理想情况)
    public static void main(String[] args) {
        String[] items = {"chen", "ting", "victor", "hao", "extra", "case"};
        Map<String, String> result = randomDrawing(items);

        for (Map.Entry<String, String> entry: result.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    // TODO. 如果完全随机生成randomIndex，不一定能产生最后的结果
    // 前面随机匹配后的结果可能导致最后剩余的item没有目标可选
    // 用于抽签的items必须是双数，否则无法两两匹配
    private static Map<String, String> randomDrawing(String[] items) {
        SecureRandom secureRandom = new SecureRandom();
        Map<String, String> resultMap = new HashMap<>();
        Set<Integer> selectedSet = new HashSet<>();

        int sourceIndex = 0;
        while (resultMap.size() != items.length) {
            int randomTargetIndex = secureRandom.nextInt(0,  items.length);

            while (randomTargetIndex == sourceIndex || selectedSet.contains(sourceIndex)
                    || isCircleSelection(resultMap, items[sourceIndex], items[randomTargetIndex])) {
                randomTargetIndex = secureRandom.nextInt( 0, items.length);
            }

            // 添加组队的选择关系
            resultMap.put(items[sourceIndex], items[randomTargetIndex]);
            selectedSet.add(sourceIndex);
            sourceIndex = randomTargetIndex;
        }
        return resultMap;
    }

    private static boolean isCircleSelection(Map<String, String> resultMap, String source, String target) {
        return resultMap.containsKey(target) && Objects.equals(resultMap.get(target), source);
    }
}
