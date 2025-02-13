package amazon.plates_between_candles;

public class PlatesBetweenCandlesMaster {

    // TODO. 直接判断Query的两端，然后得出中间段的统计
    // "||**||**|*"
    //     *||**|, query[3, 8]
    //     2 - 4
    //
    // "**|**|***|",
    //    |**|, query[2,5]
    //       |***|, query[5,9]
    //        3 - 5
    //
    // **||||*||||||*|||||||||*||**|*|*||*||**|*||||*|*|*|*|**||
    //
    // O(N+M) N是字符的长度, M是Query的长度
    // O(M)   结果所需的内容空间
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int[] result = new int[queries.length];

        return result;
    }
}
