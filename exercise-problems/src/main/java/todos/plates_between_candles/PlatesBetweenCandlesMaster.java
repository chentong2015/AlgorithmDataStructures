package todos.plates_between_candles;

// TODO. 目标算法: Binary search | Prefix Sum
public class PlatesBetweenCandlesMaster {

    public static void main(String[] args) {
        String str = "***|**|*****|**||**|*";
        int[][] queries = {{1,17},{4,5},{14,17},{5,11},{15,16}};

        PlatesBetweenCandlesMaster instance = new PlatesBetweenCandlesMaster();
        int[] result = instance.platesBetweenCandles(str, queries);
        for (int count: result) {
            System.out.println(count);
        }
    }

    // TODO. 使用二分法查找左右两侧Candle位置，减去开头和结尾的*数目即为统计
    // "||**||**|*"
    //     *||**|    query[3,8] = 2
    //
    // "**|**|***|"
    //    |**|       query[2,5] = 2
    //       |***|   query[5,9] = 3
    //
    // **||||*||||||*|||||||||*||**|*|*||*||**|*|||
    // 12    3      4         5
    //                                   4  32 1
    //
    // O(2N + 2*M*logM)  N是字符的长度, 增加二分查找的统计
    // O(2N + M) M是Query的长度
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int[] leftPlates = countPlates(s);
        int[] rightPlates = countPlates(new StringBuilder(s).reverse().toString());
        int totalPlates = leftPlates[s.length() - 1];

        int idResult = 0;
        int[] result = new int[queries.length];
        for (int[] query: queries) {
            // TODO. 使用二分查找确定两侧Candle位置
            int idFirstCandle = binarySearch(s.toCharArray(), query[0], query[1], true);
            int idLastCandle = -1;
            if (idFirstCandle != -1) {
                idLastCandle =  binarySearch(s.toCharArray(), query[0], query[1], false);
            }

            // TODO. 直接计算Candle中间的统计
            if (idFirstCandle != -1 && idLastCandle != -1 && idFirstCandle + 1 < idLastCandle) {
                result[idResult++] = totalPlates - leftPlates[idFirstCandle] - rightPlates[idLastCandle];
            } else {
                result[idResult++] = 0;
            }
        }
        return result;
    }

    private int[] countPlates(String s) {
        int totalPlates = 0;
        int[] countPlates = new int[s.length()];
        for (int index = 0; index < s.length(); index++) {
            if (s.charAt(index) == '*') {
                totalPlates++;
            }
            countPlates[index] = totalPlates;
        }
        return countPlates;
    }

    // TODO. 二分法查找不适应，可能错过目标字符
    private int binarySearch(char[] charArray, int startIndex, int endIndex, boolean isSearchingLeftCandle) {
        int left = startIndex;
        int right = endIndex;
        int index = -1;
        while (left <= right) {
            int median = left + (right - left) / 2;
            if (charArray[median] == '*') {
                 right = median - 1;
            } else {
                // 继续二分法查找左右区间
                index = median;
                if (isSearchingLeftCandle) {
                    right = median - 1;
                } else {
                    left = median + 1;
                }
            }
        }
        return index;
    }
}
