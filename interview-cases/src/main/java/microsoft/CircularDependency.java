package microsoft;

import java.util.*;

// TODO: 抽象问题的本质：循环依赖
// Spring使用三级缓存(因为有AOP)来部分解决这个问题
public class CircularDependency {

    private static Set<String> finishedCells;  // 一级缓存
    private static Set<String> creatingCells;  // 二级缓存
    private static HashMap<String, String> allCells = new HashMap<>(); // 包含所有的Cells信息

    public boolean validExcelFormulas(HashMap<String, String> cells) {
        allCells = cells;
        finishedCells = new HashSet<>();
        creatingCells = new HashSet<>();
        for (Map.Entry<String, String> cell : cells.entrySet()) {
            if (!validSingleFormula(cell.getKey(), cell.getValue())) {
                return false;
            }
        }
        return true;
    }

    // 验证一个单独的Cell是否是有效的
    private boolean validSingleFormula(String key, String formula) {
        // 使用缓存来判断已经计算过的Cell，避免重复执行逻辑
        if (finishedCells.contains(key)) return true;
        // 添加正在被创建的Cell key
        creatingCells.add(key);
        Set<String> relatedCellKeys = parseAllRelatedCellKeys(formula);
        for (String relatedCellKey : relatedCellKeys) {
            if (relatedCellKey.length() > 0) {
                if (finishedCells.contains(relatedCellKey)) continue;
                // TODO: 如果用来计算的相关的单元格正在被创建中，则表示出现了循环依赖，无法计算
                if (creatingCells.contains(relatedCellKey)) return false;
                // 这里没有考虑Cell Formula公式的有效性
                // 成功计算之后，可以设置成Cell单元格的计算值
                boolean result = validSingleFormula(relatedCellKey, allCells.get(relatedCellKey));
                // 如果递归中有错，则整个Excel Formula无效
                if (!result) return false;
            }
        }
        // 移除掉正在创建的cell, 保存已经创建好的cell
        creatingCells.remove(key);
        finishedCells.add(key);
        return true;
    }

    private Set<String> parseAllRelatedCellKeys(String formula) {
        Set<String> relatedCellKeys = new HashSet<>();
        if (formula.contains(",")) {
            // 将数组转成list，然后直接添加到结果Set集中
            relatedCellKeys.addAll(Arrays.asList(formula.split(",")));
        } else {
            relatedCellKeys.add(formula);
        }
        return relatedCellKeys;
    }
}
