package googleTop50.backtracking;

import com.sun.tools.javac.Main;

// Filling Bookcase Shelves
// You are given an array books where books[i] = [thicknessi, heighti]
// indicates the thickness and height of the ith book.
// You are also given an integer shelfWidth.
//
// We want to place these books in order onto bookcase shelves that have a total width shelfWidth.
public class FillingBookcaseShelves {

    // TODO. 回溯的本质是可以动态组合(递归)，找到最佳的匹配效果
    // Use backtracking to find the min height in all possibilities combination
    // [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelfWidth = 4
    //
    // [1,1]
    // [2,3],[2,3]
    // [1,1],[1,1],[1,1],[1,2]

    public int minHeightShelves(int[][] books, int shelfWidth) {
        return backtracking(books, shelfWidth, 0, 0, 0);
    }

    // Time:
    // Space:
   private int backtracking(int[][] books, int shelfWidth, int height, int currentWidth, int index) {
        if (index == books.length) {
            return height;
        }

        // must place at least one book for each level 每层至少要存放一个，没有别的选择
        if (currentWidth == 0) {
            currentWidth = shelfWidth - books[index][0];
            return backtracking(books, shelfWidth, height, currentWidth, index+1);
        }

        // put the book in the same line 当前层已有存放，可以追加存储
        int sameLineLevel = Integer.MAX_VALUE;
        if (currentWidth + books[index][0] <= shelfWidth) {
            currentWidth = shelfWidth - books[index][0];
            sameLineLevel = backtracking(books, shelfWidth, height, currentWidth, index+1);
        }

        // put the book to a new line 在当前层已有存放的基础上，跳到下一层存储
        currentWidth = 0;
        int newLineLevel = backtracking(books, shelfWidth, height+1, currentWidth, index);
        return Math.min(sameLineLevel, newLineLevel);
    }
}
