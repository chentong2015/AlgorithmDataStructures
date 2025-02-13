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
    //       Start统计该点往后有多少个有效plate
    //              End统计该点往前所多少个有效plate
    //
    // O(N+M) N是字符的长度, M是Query的长度
    // O(M)   结果所需的内容空间
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int[] result = new int[queries.length];

        int[] countPlatesBefore = new int[s.length()];
        boolean hasFoundCandle = false;
        int count = 0;
        for (int index = 0; index < s.length(); index++) {
            if (s.charAt(index) == '|' && !hasFoundCandle) {
                hasFoundCandle = true;
                continue;
            }
            countPlatesBefore[index] = count;
            if (s.charAt(index) == '*' && hasFoundCandle) {
                count++;
            }
        }

        int[] countPlatesAfter = new int[s.length()];
        hasFoundCandle = false;
        count = 0;
        for (int index = s.length() - 1; index >= 0; index--) {
            if (s.charAt(index) == '|' && !hasFoundCandle) {
                hasFoundCandle = true;
                continue;
            }
            countPlatesAfter[index] = count;
            if (s.charAt(index) == '*' && hasFoundCandle) {
                count++;
            }
        }

        for(int index = 0; index< queries.length; index++) {
            int countRight = countPlatesAfter[queries[index][0]];
            int countLeft = countPlatesBefore[queries[index][1]];
            result[index] = Math.min(countLeft, countRight);
        }
        return result;
    }
}
