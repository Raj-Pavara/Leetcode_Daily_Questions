/* BitWise AND of Numbers Range

 Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.

Example 1:

Input: left = 5, right = 7
Output: 4
Example 2:

Input: left = 0, right = 0
Output: 0
Example 3:

Input: left = 1, right = 2147483647
Output: 0
 
Constraints:

0 <= left <= right <= 231 - 1

*/

public class feb_21_24{
public static void main(String args[]){
    System.out.println(rangeBitwiseAnd(5, 7));
}

public static int rangeBitwiseAnd(int Left, int Right) {
    int temp = Left^Right;
    int c=0;
    while (temp!=0) {
        temp = temp>>1;
        c=c|1;
        c=c<<1;
    }
    c=c>>1;
    return Left&(~c);
 }
}