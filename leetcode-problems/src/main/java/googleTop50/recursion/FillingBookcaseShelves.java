package googleTop50.recursion;

// Filling Bookcase Shelves
// You are given an array books where books[i] = [thicknessi, heighti]
// indicates the thickness and height of the ith book.
// You are also given an integer shelfWidth.
//
// We want to place these books in order onto bookcase shelves that have a total width shelfWidth.
public class FillingBookcaseShelves {

    // TODO. 高阶递归算法: 在递归的过程中携带四个参数
    // 如果当前行可以存放: 取存放和不存放的最小值
    // 如果当前行不能存放: 取存到下一行的结果
    // Use backtracking to find the min height in all possibilities combination
    // [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelfWidth = 4
    //
    // [1,1]
    // [2,3],[2,3]
    // [1,1],[1,1],[1,1],[1,2]
    public int minHeightShelves(int[][] books, int shelfWidth) {
        // Cache to store previous computations
        int[][] memo = new int[books.length][shelfWidth + 1];
        return dpHelper(books, shelfWidth, memo, 0, shelfWidth, 0);
    }

    // TODO. DP优化: 存储过程中计算过的值(第i本书 + 后面剩余空间)
    // N be the length of array books, and W be the shelfWidth
    // O(N * W)
    // O(N * W)
    private int dpHelper(int[][] books, int shelfWidth, int[][] memo, int i, int remainingShelfWidth, int maxHeight) {
        if (i == books.length) {
            return maxHeight;
        }
        // Return answer if already computed
        if (memo[i][remainingShelfWidth] != 0) {
            return memo[i][remainingShelfWidth];
        }

        // Calculate the height of the bookcase if we put the current book on the new shelf
        int option1Height = maxHeight + dpHelper(books, shelfWidth, memo, i + 1, shelfWidth - books[i][0], books[i][1]);
        int option2Height = Integer.MAX_VALUE;
        if (books[i][0] <= remainingShelfWidth) {
            // Calculate height of the bookcase if we put the current book on the current shelf
            option2Height = dpHelper(books, shelfWidth, memo, i + 1, remainingShelfWidth - books[i][0], Math.max(maxHeight, books[i][1]));
        }

        // Store the smaller result in cache
        memo[i][remainingShelfWidth] = Math.min(option1Height, option2Height);
        return memo[i][remainingShelfWidth];
    }

    // TODO. 递归罗列所有情况: 造成Time Limit Exceeded超时
    // O(2^N) 指数级别的复杂度
    private int recursion(int[][] books, int shelfWidth, int index, int totalHeight, int currentMaxHeight, int currentWidth) {
        if (index == books.length) {
            return totalHeight + currentMaxHeight; // 累计最后一次高度
        }
        if (currentWidth == 0) { // 必须保证换行后至少填一个, 避免无限循环
            return recursion(books, shelfWidth, index+1, totalHeight, Math.max(currentMaxHeight, books[index][1]), currentWidth + books[index][0]);
        }

        if (currentWidth + books[index][0] <= shelfWidth) {
            return Math.min(
                    recursion(books, shelfWidth, index+1, totalHeight, Math.max(currentMaxHeight, books[index][1]), currentWidth + books[index][0]),
                    recursion(books, shelfWidth, index, totalHeight + currentMaxHeight, 0, 0)); // new line
        }
        return recursion(books, shelfWidth, index, totalHeight + currentMaxHeight, 0, 0); // new line
    }
}
