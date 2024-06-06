/*Hand of Straights

Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.

Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return true if she can rearrange the cards, or false otherwise.

Example 1:

Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
Example 2:

Input: hand = [1,2,3,4,5], groupSize = 4
Output: false
Explanation: Alice's hand can not be rearranged into groups of 4. 

Constraints:

1 <= hand.length <= 104
0 <= hand[i] <= 109
1 <= groupSize <= hand.length 
*/
public class june_06_24 {
public static void main(String args[]){

}
public boolean isNStraightHand(int[] hand, int groupSize) {
    int handSize = hand.length;
    if (handSize % groupSize != 0) {
        return false;
    }

    // TreeMap to store the count of each card value
    Map<Integer, Integer> cardCount = new TreeMap<>();
    for (int i = 0; i < handSize; i++) {
        cardCount.put(hand[i], cardCount.getOrDefault(hand[i], 0) + 1);
    }

    // Process the cards until the map is empty
    while (cardCount.size() > 0) {
        // Get the smallest card value
        int current_card = cardCount.entrySet().iterator().next().getKey();
        // Check each consecutive sequence of groupSize cards
        for (int i = 0; i < groupSize; i++) {
  

import java.util.Map;
import java.util.TreeMap;

            if (!cardCount.containsKey(current_card + i)) return false;
            cardCount.put(
                current_card + i,
                cardCount.get(current_card + i) - 1
            );
            // Remove the card value if its occurrences are exhausted
            if (cardCount.get(current_card + i) == 0) cardCount.remove(
                current_card + i
            );
        }
    }

    return true;
}
}