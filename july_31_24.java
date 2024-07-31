/*1105. Filling Bookcase Shelves
You are given an array books where books[i] = [thicknessi, heighti] indicates the thickness and height of the ith book. You are also given an integer shelfWidth.

We want to place these books in order onto bookcase shelves that have a total width shelfWidth.

We choose some of the books to place on this shelf such that the sum of their thickness is less than or equal to shelfWidth, then build another level of the shelf of the bookcase so that the total height of the bookcase has increased by the maximum height of the books we just put down. We repeat this process until there are no more books to place.

Note that at each step of the above process, the order of the books we place is the same order as the given sequence of books.

For example, if we have an ordered list of 5 books, we might place the first and second book onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.
Return the minimum possible height that the total bookshelf can be after placing shelves in this manner. 

Example 1:

Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelfWidth = 4
Output: 6
Explanation:
The sum of the heights of the 3 shelves is 1 + 3 + 2 = 6.
Notice that book number 2 does not have to be on the first shelf.

Example 2:

Input: books = [[1,3],[2,4],[3,2]], shelfWidth = 6
Output: 4
 
Constraints:

1 <= books.length <= 1000
1 <= thicknessi <= shelfWidth <= 1000
1 <= heighti <= 1000
 */
public class july_31_24 {
    public static void main(String args[]) {

    }

    
    public int minHeightShelves(int[][] books, int shelfWidth) {
        // Cache to store previous computations
        int[][] memo = new int[books.length][shelfWidth + 1];
        return dpHelper(books, shelfWidth, memo, 0, shelfWidth, 0);
    }

    private int dpHelper(
        int[][] books,
        int shelfWidth,
        int[][] memo,
        int i,
        int remainingShelfWidth,
        int maxHeight
    ) {
        int[] currentBook = books[i];
        int maxHeightUpdated = Math.max(maxHeight, currentBook[1]);
        if (i == books.length - 1) {
            // For the last book, store it in the current shelf if possible,
            // or start a new shelf with it
            if (remainingShelfWidth >= currentBook[0]) return maxHeightUpdated;
            return maxHeight + currentBook[1];
        }
        // Return answer if already computed
        if (memo[i][remainingShelfWidth] != 0) {
            return memo[i][remainingShelfWidth];
        } else {
            // Calculate the height of the bookcase if we put the current book on the new shelf
            int option1Height =
                maxHeight +
                dpHelper(
                    books,
                    shelfWidth,
                    memo,
                    i + 1,
                    shelfWidth - currentBook[0],
                    currentBook[1]
                );
            if (remainingShelfWidth >= currentBook[0]) {
                // Calculate height of the bookcase if we put the current book on the current shelf
                int option2Height = dpHelper(
                    books,
                    shelfWidth,
                    memo,
                    i + 1,
                    remainingShelfWidth - currentBook[0],
                    maxHeightUpdated
                );
                // Store result in cache
                memo[i][remainingShelfWidth] = Math.min(
                    option1Height,
                    option2Height
                );
                return memo[i][remainingShelfWidth];
            }
            // Store result in cache
            memo[i][remainingShelfWidth] = option1Height;
            return memo[i][remainingShelfWidth];
        }
    }

}