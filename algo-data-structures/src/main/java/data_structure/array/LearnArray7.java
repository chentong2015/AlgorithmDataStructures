package data_structure.array;

public class LearnArray7 {

    // TODO. 循环数组：从其中框选k段的长度, 保证截取的长度中包含开头或者结尾index
    // Maximum Points You Can Obtain from Cards
    // The points are given in the integer array cardPoints
    // In one step, you can take one card from the beginning or from the end of the row.
    // You have to take exactly k cards.
    // Return the maximum score you can obtain.
    //
    // [1,2,3,4,5,6,1], k = 3 -> 1 + 6 + 5 = 12
    // O(k) O(1) 只需要在数组中循环k个位置的值
    public int maxScore(int[] cardPoints, int k) {
        int baseScore = 0;
        for (int index = 0; index <k; index++) {
            baseScore += cardPoints[index];
        }

        int left = k - 1;
        int index = 0;
        int maxScore = baseScore;
        while (index < k) {
            // 循环数组：每移动一个位置，重新计算累计的总和
            baseScore = baseScore - cardPoints[left] + cardPoints[cardPoints.length - 1 - index];
            maxScore = Math.max(maxScore, baseScore);
            left--;
            index++;
        }
        return maxScore;
    }
}
