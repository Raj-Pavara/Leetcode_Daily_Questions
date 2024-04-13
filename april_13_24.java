/*Maximal Rectangle

Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example 1:

Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.
Example 2:

Input: matrix = [["0"]]
Output: 0
Example 3:

Input: matrix = [["1"]]
Output: 1
 

Constraints:

rows == matrix.length
cols == matrix[i].length
1 <= row, cols <= 200
matrix[i][j] is '0' or '1'.
 */

import java.util.Stack;

public class april_13_24 {

  public static void main(String args[]) {
    //    char matrix[][] = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
     char matrix[][] = {
        {'1','0','1','1','0','1'},
     {'1','1','1','1','1','1'},
     {'0','1','1','0','1','1'},
     {'1','1','1','0','1','0'},
     {'0','1','1','1','1','1'},
     {'1','1','0','1','1','1'}};
    System.out.println(maximalRectangle(matrix));
    // int arr[] = {3,1,2,2,2};
    // System.out.println(largestAreaHistogram(arr));
  }



  public static int maximalRectangle(char[][] matrix) {
    int arr[] = new int[matrix[0].length];
    int maxArea = 0;
     for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[0].length; j++) {
            if(matrix[i][j]=='0'){
                arr[j] = 0;
            }
            else{
                arr[j]++;
            }
        }
        maxArea = Math.max(maxArea, largestAreaHistogram(arr));
        
     }  
     return maxArea;
  }

  public static int largestAreaHistogram(int height[]){
        
        // step - 1 next smaller element at Left .  tc O(n) sc O(1).

        int nextsmallerLeft[] = new int[height.length];

        Stack<Integer> s=new Stack<>();

        nextsmallerLeft[0] = -1;
        // -1 means no nextsmallerLeft exists.
        s.push(0);

        for (int i = 1; i < nextsmallerLeft.length; i++) {

            while(height[s.peek()] >= height[i]){
                s.pop();
                if(s.isEmpty()){
                    break;
                }
            }
           
            if(s.isEmpty()){
                nextsmallerLeft[i] = -1;
            }
            else{
                nextsmallerLeft[i] = s.peek();
            }
        
            s.push(i);

        }



        // step - 2 next smaller element at Right .  tc O(n) sc O(1).

        int nextsmallerRight[] = new int[height.length];

        s=new Stack<>();

        nextsmallerRight[height.length - 1] = -1;
        // -1 means no nextsmallerLeft exists.
        s.push(height.length - 1);

        for (int i = height.length - 2; i >= 0; i--) {

            while(height[s.peek()] >= height[i]){
                s.pop();
                if(s.isEmpty()){
                    break;
                }
            }
           
            if(s.isEmpty()){
                nextsmallerRight[i] = -1;
            }
            else{
                nextsmallerRight[i] = s.peek();
            }
        
            s.push(i);

        }


        // step - 3 find all area of rectuangular. tc O(n) sc O(1)

        int max = 0;

        for (int i = 0; i < height.length; i++) {
            
            int currheight = height[i] ;

            int currwidth = nextsmallerRight[i] - nextsmallerLeft[i] -1;

            if(nextsmallerLeft[i] == -1){
                currwidth = nextsmallerRight[i] ;
            }

            if(nextsmallerRight[i] == -1){
                currwidth = height.length - 1 - nextsmallerLeft[i] ;
            }

            if(nextsmallerLeft[i] == -1 && nextsmallerRight[i] == -1){
                currwidth = height.length ;
            } 

            int currArea = currheight * currwidth ;

            max = Math.max(max, currArea);
            
        }


        // step - 4 return max area.
        
        return max;
    
  }
}
