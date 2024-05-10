/*K-th Smallest Prime Fraction

You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique. You are also given an integer k.

For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].

Return the kth smallest fraction considered. Return your answer as an array of integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j].

 

Example 1:

Input: arr = [1,2,3,5], k = 3
Output: [2,5]
Explanation: The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
The third fraction is 2/5.
Example 2:

Input: arr = [1,7], k = 1
Output: [1,7]
 

Constraints:

2 <= arr.length <= 1000
1 <= arr[i] <= 3 * 104
arr[0] == 1
arr[i] is a prime number for i > 0.
All the numbers of arr are unique and sorted in strictly increasing order.
1 <= k <= arr.length * (arr.length - 1) / 2
 

Follow up: Can you solve the problem with better than O(n2) complexity?
 */
public class may_10_24 {
    public static void main(String args[]) {

    }

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        double left = 0, right = 1.0;

        // Binary search for finding the kth smallest prime fraction
        while (left < right) {
            // Calculate the middle value
            double mid = (left + right) / 2;

            // Initialize variables to keep track of maximum fraction and indices
            double maxFraction = 0.0;
            int totalSmallerFractions = 0, numeratorIdx = 0, denominatorIdx = 0;
            int j = 1;

            // Iterate through the array to find fractions smaller than mid
            for (int i = 0; i < n - 1; i++) {
                while (j < n && arr[i] >= mid * arr[j]) {
                    j++;
                }

                // Count smaller fractions
                totalSmallerFractions += (n - j);

                // If we have exhausted the array, break
                if (j == n)
                    break;

                // Calculate the fraction
                double fraction = (double) arr[i] / arr[j];

                // Update maxFraction and indices if necessary
                if (fraction > maxFraction) {
                    numeratorIdx = i;
                    denominatorIdx = j;
                    maxFraction = fraction;
                }
            }

            // Check if we have found the kth smallest prime fraction
            if (totalSmallerFractions == k) {
                return new int[] { arr[numeratorIdx], arr[denominatorIdx] };
            } else if (totalSmallerFractions > k) {
                right = mid; // Adjust the range for binary search
            } else {
                left = mid; // Adjust the range for binary search
            }
        }
        return new int[] {}; // Return empty array if kth smallest prime fraction not found
    }
}