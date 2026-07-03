package veepee;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO. 遇到具体业务场景, 考虑使用TDD来驱动开发
// 1. 分析题目SCENARIO, 考虑异常的特殊场景
// 2. 先写测试代码, 根据不同场景来驱动算法API的开发
public class VeepeeTestTDD {

    // @Test
    public void testReservedPriceLessOrEqualThanZero() {
        VeepeeQuestion playerClass = new VeepeeQuestion();
        Map<String, List<Integer>> playersMap = new HashMap<>();
        playersMap.put("A", Collections.emptyList());
        playersMap.put("B", Collections.emptyList());

        VeepeeQuestion.Result result = playerClass.findWinnerAndWinningPrice(0, playersMap);
        // Assertions.assertEquals(result, new Result());
    }
}
