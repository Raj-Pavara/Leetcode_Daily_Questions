/*386. Lexicographical Numbers

Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.

You must write an algorithm that runs in O(n) time and uses O(1) extra space. 

Example 1:

Input: n = 13
Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]

Example 2:

Input: n = 2
Output: [1,2]

Constraints:

1 <= n <= 5 * 104 */

import java.util.ArrayList;
import java.util.List;

public class sept_21_24 {
    public static void main(String args[]) {

    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> al = new ArrayList<>();
        int curr = 1;
        for (int i = 1; i <= n; i++) {
            al.add(curr);
            if (curr * 10 <= n)
                curr = curr * 10;
            else {
                while (curr % 10 == 9 || curr >= n) {
                    curr = curr / 10;
                }
                curr += 1;
            }
        }
        return al;

    }
}