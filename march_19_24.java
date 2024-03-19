/*Task Scheduler
 
You are given an array of CPU tasks, each represented by letters A to Z, and a cooling time, n. Each cycle or interval allows the completion of one task. Tasks can be completed in any order, but there's a constraint: identical tasks must be separated by at least n intervals due to cooling time.

​Return the minimum number of intervals required to complete all tasks.

Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2

Output: 8

Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.

After completing task A, you must wait two cycles before doing A again. The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle. By the 4th cycle, you can do A again as 2 intervals have passed.

Example 2:

Input: tasks = ["A","C","A","B","D","B"], n = 1

Output: 6

Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.

With a cooling interval of 1, you can repeat a task after just one other task.

Example 3:

Input: tasks = ["A","A","A", "B","B","B"], n = 3

Output: 10

Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.

There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice between repetitions of these tasks.

 

Constraints:

1 <= tasks.length <= 104
tasks[i] is an uppercase English letter.
0 <= n <= 100
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class march_19_24 {

  public static void main(String args[]) {
    char tasks[] = {'A','A','A','B','B','B'};
    System.out.println(leastInterval(tasks, 3));
  }

  public static int leastInterval(char[] tasks, int n) {
      
        Map<Character, Integer> count = new HashMap<>();
        for(char t: tasks) {
            count.put(t, count.getOrDefault(t, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue(count.size(), Collections.reverseOrder());
        pq.addAll(count.values());

        int ans = 0;

        while(!pq.isEmpty()) {
            int time = 0;
            List<Integer> temp = new ArrayList<>();
            for(int i = 0; i < n+1; i++) {
                if(!pq.isEmpty()) {
                    temp.add(pq.remove() - 1);
                    time++;
                }
            }
            for(int t : temp) {
                if(t > 0) pq.add(t);
            }
            ans += pq.isEmpty() ? time : n+1;
        }
        return ans;
    
  }
}
