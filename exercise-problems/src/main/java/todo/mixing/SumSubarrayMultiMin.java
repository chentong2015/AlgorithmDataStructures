package todo.mixing;

// Sum of Total Strength of Wizards
// Since the answer may be very large, return it modulo 10^9 + 7.
//
// 1 <= strength.length <= 10^5
// 1 <= strength[i] <= 10^9
//
// strength = [1,3,1,2] -> 44
// The following are all the contiguous groups of wizards:
// - [1] total strength of min([1]) * sum([1]) = 1 * 1 = 1
// - [3] total strength of min([3]) * sum([3]) = 3 * 3 = 9
// - [1] total strength of min([1]) * sum([1]) = 1 * 1 = 1
// - [2] total strength of min([2]) * sum([2]) = 2 * 2 = 4
// - [1,3] total strength of min([1,3]) * sum([1,3]) = 1 * 4 = 4
// - [3,1] total strength of min([3,1]) * sum([3,1]) = 1 * 4 = 4
// - [1,2] total strength of min([1,2]) * sum([1,2]) = 1 * 3 = 3
// - [1,3,1] total strength of min([1,3,1]) * sum([1,3,1]) = 1 * 5 = 5
// - [3,1,2] total strength of min([3,1,2]) * sum([3,1,2]) = 1 * 6 = 6
// - [1,3,1,2] total strength of min([1,3,1,2]) * sum([1,3,1,2]) = 1 * 7 = 7
// The sum total strengths 1 + 9 + 1 + 4 + 4 + 4 + 3 + 5 + 6 + 7 = 44.
public class SumSubarrayMultiMin {

    public int totalStrength(int[] strength) {

        return 0;
    }
}
