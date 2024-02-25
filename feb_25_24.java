/* Greatest Common Divisor Traversal


 You are given a 0-indexed integer array nums, and you are allowed to traverse between its indices. You can traverse between index i and index j, i != j, if and only if gcd(nums[i], nums[j]) > 1, where gcd is the greatest common divisor.

Your task is to determine if for every pair of indices i and j in nums, where i < j, there exists a sequence of traversals that can take us from i to j.

Return true if it is possible to traverse between all such pairs of indices, or false otherwise.

 

Example 1:

Input: nums = [2,3,6]
Output: true
Explanation: In this example, there are 3 possible pairs of indices: (0, 1), (0, 2), and (1, 2).
To go from index 0 to index 1, we can use the sequence of traversals 0 -> 2 -> 1, where we move from index 0 to index 2 because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1, and then move from index 2 to index 1 because gcd(nums[2], nums[1]) = gcd(6, 3) = 3 > 1.
To go from index 0 to index 2, we can just go directly because gcd(nums[0], nums[2]) = gcd(2, 6) = 2 > 1. Likewise, to go from index 1 to index 2, we can just go directly because gcd(nums[1], nums[2]) = gcd(3, 6) = 3 > 1.
Example 2:

Input: nums = [3,9,5]
Output: false
Explanation: No sequence of traversals can take us from index 0 to index 2 in this example. So, we return false.
Example 3:

Input: nums = [4,3,12,8]
Output: true
Explanation: There are 6 possible pairs of indices to traverse between: (0, 1), (0, 2), (0, 3), (1, 2), (1, 3), and (2, 3). A valid sequence of traversals exists for each pair, so we return true.
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^5
 */

 import java.util.*;


 public class feb_25_24{
 public static void main(String args[]){
    int nums[] = {4,3,12,8};
      System.out.println(canTraverseAllPairs(nums));
 }
    public static void dfs(ArrayList<ArrayList<Integer>>adj,int []vis,int st){
        vis[st]=1;
        for(int i:adj.get(st)){
            if(vis[i]==0){
                dfs(adj,vis,i);
            }
        }
    }
public static void allprime(int n,HashMap<Integer,ArrayList<Integer>>map,int st){
        while(n%2==0){
            map.put(2,map.getOrDefault(2,new ArrayList<>()));
            ArrayList<Integer>temp=map.get(2);
            temp.add(st);
            map.put(2,temp);
            n/=2;
        }
        for(int i=3; i<=Math.sqrt(n); i+=2){
            while(n%i==0){
                map.put(i,map.getOrDefault(i,new ArrayList<>()));
                ArrayList<Integer>temp=map.get(i);
                temp.add(st);
                map.put(i,temp);
                n/=i;
            }
        }
        if(n>2){
            map.put(n,map.getOrDefault(n,new ArrayList<>()));
            ArrayList<Integer>temp=map.get(n);
            temp.add(st);
            map.put(n,temp);
        }
    }
    public static boolean canTraverseAllPairs(int[] nums) {
        ArrayList<ArrayList<Integer>>adj=new ArrayList<>();
        HashMap<Integer,ArrayList<Integer>>map=new HashMap<>();
        
        int n=nums.length;
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }    
        for(int i=0;i<n;i++){
            allprime(nums[i],map,i);
        }
        
        for(ArrayList<Integer>temp:map.values()){
            for(int i=1;i<temp.size();i++){
                adj.get(temp.get(i-1)).add(temp.get(i));
                adj.get(temp.get(i)).add(temp.get(i-1));
            }
        }
        int []vis=new int[n];
        dfs(adj,vis,0);
        for(int i=0;i<n;i++){
            if(vis[i]==0)return false;
        }
        return true;
    }
}

