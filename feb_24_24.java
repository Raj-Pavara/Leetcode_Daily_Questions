/*  Find All People With Secret
 * 
You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei. A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.

Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then shared every time a meeting takes place with a person that has the secret. More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.

The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other meetings within the same time frame.

Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer in any order.

 

Example 1:

Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
Output: [0,1,2,3,5]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 5, person 1 shares the secret with person 2.
At time 8, person 2 shares the secret with person 3.
At time 10, person 1 shares the secret with person 5.​​​​
Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.
Example 2:

Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
Output: [0,1,3]
Explanation:
At time 0, person 0 shares the secret with person 3.
At time 2, neither person 1 nor person 2 know the secret.
At time 3, person 3 shares the secret with person 0 and person 1.
Thus, people 0, 1, and 3 know the secret after all the meetings.
Example 3:

Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
Output: [0,1,2,3,4]
Explanation:
At time 0, person 0 shares the secret with person 1.
At time 1, person 1 shares the secret with person 2, and person 2 shares the secret with person 3.
Note that person 2 can share the secret at the same time as receiving it.
At time 2, person 3 shares the secret with person 4.
Thus, people 0, 1, 2, 3, and 4 know the secret after all the meetings.
 

Constraints:

2 <= n <= 105
1 <= meetings.length <= 105
meetings[i].length == 3
0 <= xi, yi <= n - 1
xi != yi
1 <= timei <= 105
1 <= firstPerson <= n - 1
 */




import java.util.*;
import java.util.stream.IntStream;

public class feb_24_24{
public static void main(String args[]){
     int[][] mettings =  {{1,2,5},{2,3,8},{1,5,10}};
     System.out.println(findAllPeople(6,mettings,1));
}

    public static List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        UnionFind union = new UnionFind(n);
        union.union(0, firstPerson);
        Map<Integer, List<int[]>> meetMap = new TreeMap<>();
        for (int[] meet: meetings) {
            meetMap.computeIfAbsent(meet[2], k -> new ArrayList<>()).add(new int[] {meet[0], meet[1]});
        }
        for (Map.Entry<Integer, List<int[]>> entry: meetMap.entrySet()) {
            int t = entry.getKey();
            List<int[]> meets = entry.getValue();
            for (int[] meet: meets) {
                union.union(meet[0], meet[1]);
            }
            for (int[] meet: meets) {
                if (!union.connected(meet[0], 0)) {
                    union.reset(meet[0]);
                    union.reset(meet[1]);
                }
            }
        }
        return union.peopleInGroup(union.find(0));
    }
}

class UnionFind {

    int[] ranks;
    int[] groups;
    int groupsNum;

    public UnionFind(int n) {
        groupsNum = n;
        ranks = new int[n];
        groups = new int[n];
        Arrays.fill(ranks, 1);
        for (int i = 0; i < n; i++) {
            groups[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int q) {
        while (groups[q] != q) {
            groups[q] = groups[groups[q]];
            q = groups[q];
        }
        return q;
    }

    public void reset(int q) {
        groups[q] = q;
        ranks[q] = 1;
    }

    public boolean union(int p, int q) {
        int gp = find(p);
        int gq = find(q);
        if (gp == gq) {
            return false;
        }
        if (ranks[gp] < ranks[gq]) {
            groups[gp] = gq;
            ranks[gq] += ranks[gp];
        } else {
            groups[gq] = gp;
            ranks[gp] += ranks[gq];
        }
        groupsNum--;
        return true;
    }

    public List<Integer> peopleInGroup(int g) {
        return IntStream.range(0, groups.length).filter(i -> find(i) == g).boxed().toList();
    }

}