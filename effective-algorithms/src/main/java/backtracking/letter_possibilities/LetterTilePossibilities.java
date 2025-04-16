package backtracking.letter_possibilities;

// Letter Tile Possibilities
// You have n tiles, where each tile has one letter tiles[i] printed on it.
// Return the number of possible non-empty sequences of letters
// you can make using the letters printed on those tiles.
//
// 1 <= tiles.length <= 7
// tiles consists of uppercase English letters.
public class LetterTilePossibilities {

    // TODO. 通过DFS循环递归+Backtracking回溯罗列出所有排序组合
    // "AAB" -> 8
    // "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
    // 2 + 1 + 2 + 3
    //
    // "AAABBC" -> 188
    // 3 + 2 + 1 + 2 + 2 + ('3 + '4 + '5 + '6)
    //
    // A A B
    // 0 1 2
    //
    // O(26*25*24....2*1) -> O(7*6*5*4*3*2*1) 与不同字符的数量有关
    // O(7*6*5*4*3*2*1) 递归造成的Stack栈开销
    public int numTilePossibilities(String tiles) {
        char[] arr = tiles.toCharArray();
        return permute(0, arr);
    }

    public int permute(int start, char[] arr) {
        if(start == arr.length) {
            return 0;
        }
        int ans = 0;
        for(int i = start; i < arr.length; i++) {
            if(!isPermutedBefore(start, i-1, arr[i], arr)) {
                // 如果arr[i]在前面没有，则将其交换到开头字符，罗列组合的结果
                // 判断完成后，将其回溯交换会原来的位置
                swap(start, i, arr);
                ans += 1 + permute(start + 1, arr);
                swap(start, i, arr);
            }
        }
        return ans;
    }

    // 判断[i,j]区间内是否包含ch字符，判断能将ch作为开头字符
    public boolean isPermutedBefore(int i, int j, char ch, char[] arr) {
        while(i<=j) {
            if(arr[i++] == ch) {
                return true;
            }
        }
        return false;
    }

    public void swap(int i, int j, char[] arr) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
