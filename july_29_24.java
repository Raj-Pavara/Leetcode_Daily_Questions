/*1395. Count Number of Teams

There are n soldiers standing in a line. Each soldier is assigned a unique rating value.

You have to form a team of 3 soldiers amongst them under the following rules:

Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).

Example 1:

Input: rating = [2,5,3,4,1]
Output: 3
Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1). 

Example 2:

Input: rating = [2,1,3]
Output: 0
Explanation: We can't form any team given the conditions.

Example 3:

Input: rating = [1,2,3,4]
Output: 4
 
Constraints:

n == rating.length
3 <= n <= 1000
1 <= rating[i] <= 105
All the integers in rating are unique.

 */
public class july_29_24 {
public static void main(String args[]){

}

    public int numTeams(int[] rating) {
        int n = rating.length;
        int teams = 0;
        Integer[][] increasingCache = new Integer[n][4];
        Integer[][] decreasingCache = new Integer[n][4];

        // Calculate total teams by considering each soldier as a starting point
        for (int startIndex = 0; startIndex < n; startIndex++) {
            teams +=
            countIncreasingTeams(rating, startIndex, 1, increasingCache) +
            countDecreasingTeams(rating, startIndex, 1, decreasingCache);
        }

        return teams;
    }

    private int countIncreasingTeams(
        int[] rating,
        int currentIndex,
        int teamSize,
        Integer[][] increasingCache
    ) {
        int n = rating.length;

        // Base case: reached end of array
        if (currentIndex == n) return 0;

        // Base case: found a valid team of size 3
        if (teamSize == 3) return 1;

        // Return cached result if available
        if (increasingCache[currentIndex][teamSize] != null) {
            return increasingCache[currentIndex][teamSize];
        }

        int validTeams = 0;

        // Recursively count teams with increasing ratings
        for (int nextIndex = currentIndex + 1; nextIndex < n; nextIndex++) {
            if (rating[nextIndex] > rating[currentIndex]) {
                validTeams +=
                countIncreasingTeams(
                    rating,
                    nextIndex,
                    teamSize + 1,
                    increasingCache
                );
            }
        }

        // Cache and return the result
        return increasingCache[currentIndex][teamSize] = validTeams;
    }

    private int countDecreasingTeams(
        int[] rating,
        int currentIndex,
        int teamSize,
        Integer[][] decreasingCache
    ) {
        int n = rating.length;

        // Base case: reached end of array
        if (currentIndex == n) return 0;

        // Base case: found a valid team of size 3
        if (teamSize == 3) return 1;

        // Return cached result if available
        if (decreasingCache[currentIndex][teamSize] != null) {
            return decreasingCache[currentIndex][teamSize];
        }

        int validTeams = 0;

        // Recursively count teams with decreasing ratings
        for (int nextIndex = currentIndex + 1; nextIndex < n; nextIndex++) {
            if (rating[nextIndex] < rating[currentIndex]) {
                validTeams +=
                countDecreasingTeams(
                    rating,
                    nextIndex,
                    teamSize + 1,
                    decreasingCache
                );
            }
        }

        // Cache and return the result
        return decreasingCache[currentIndex][teamSize] = validTeams;
    }
}