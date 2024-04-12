/*Trapping Rain Water

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:

Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9

Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
*/
public class april_12_24{
public static void main(String args[]){
 int height[] = {4,2,0,3,2,5};
 System.out.println(trap(height));
}

public static int trap(int[] height) {
    int l = 0, r = height.length - 1, sum = 0, leftBar = height[l], rightBar = height[r];
    
    while (l < r) {
        if (leftBar <= rightBar) {
            sum += leftBar - height[l];
            l++;
            leftBar = Math.max(leftBar, height[l]);
        } else {
            sum += rightBar - height[r];
            r--;
            rightBar = Math.max(rightBar, height[r]);
        }
    }

    return sum;
}
} 