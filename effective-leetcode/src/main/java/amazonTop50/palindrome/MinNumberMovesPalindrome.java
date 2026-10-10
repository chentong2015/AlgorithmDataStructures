package amazonTop50.palindrome;

// Minimum Number of Moves to Make Palindrome
// You are given a string s consisting only of lowercase English letters.
// In one move, you can select any two adjacent characters of s and swap them.
// Return the minimum number of moves needed to make s a palindrome.
//
// The input can always be converted to a palindrome.
// 1 <= s.length <= 2000
// s consists only of lowercase English letters.
public class MinNumberMovesPalindrome {

    // TODO. 双指针算法: 先固定回文的两端, 不断缩小中间范围
    //
    // "aabb" -> 2
    // "aabb" -> "abab" -> "abba"
    // "aabb" -> "abab" -> "baab"
    //
    // a b c a b
    // a b c b a
    //
    // O(N*N)
    // O(1)
    public int minMovesToMakePalindrome(String s) {
        char[] arr = s.toCharArray();
        int left = 0;
        int right = arr.length - 1;
        int moves = 0;
        while(left < right){
            // 查找arr[left]的最远匹配, 使移动距离最小
            int k = right;
            while(left < k && arr[left] != arr[k]){
                k--;
            }

            // arr[left]的字符是回文中的单个字符
            // 单个字符最后应该被放中间，但这层判断只需要移动一步
            if(k == left){
                swap(arr, left, left+1);
                moves++;
            } else{
                moves += move(arr, k, right);
                left++;
                right--;
            }
        }
        return moves;
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 将arr[k]匹配字符通过相邻交换移动到arr[right]
    private int move(char[] arr, int start, int end) {
        int count = 0;
        while(start < end){
            swap(arr, start, start+1);
            count++;
            start++;
        }
        return count;
    }
}