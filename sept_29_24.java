/*432. All O`one Data Structure

Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.

Implement the AllOne class:

AllOne() Initializes the object of the data structure.
inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
Note that each function must run in O(1) average time complexity.

Example 1:

Input
["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
Output
[null, null, null, "hello", "hello", null, "hello", "leet"]

Explanation
AllOne allOne = new AllOne();
allOne.inc("hello");
allOne.inc("hello");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "hello"
allOne.inc("leet");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "leet"
 
Constraints:

1 <= key.length <= 10
key consists of lowercase English letters.
It is guaranteed that for each call to dec, key is existing in the data structure.
At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class sept_29_24 {
    public static void main(String args[]) {

    }

    int freq;
    Node prev;
    Node next;
    Set<String> strSet = new HashSet<>();

    Node(int freq) {
        this.freq = freq;
    }
}

class AllOne {

    Node head;
    Node tail;
    Map<String, Node> strToNodeMap = new HashMap<>();

    AllOne() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key) {
        if (strToNodeMap.containsKey(key)) {
            Node node = strToNodeMap.get(key);
            int freq = node.freq;
            node.strSet.remove(key);

            Node nextNode = node.next;
            if (nextNode == tail || nextNode.freq != freq + 1) {
                Node newNode = new Node(freq + 1);
                newNode.strSet.add(key);
                newNode.prev = node;
                newNode.next = nextNode;
                node.next = newNode;
                nextNode.prev = newNode;
                strToNodeMap.put(key, newNode);
            } else {
                nextNode.strSet.add(key);
                strToNodeMap.put(key, nextNode);
            }

            if (node.strSet.isEmpty()) {
                deleteNode(node);
            }
        } else {
            Node firstNode = head.next;
            if (firstNode == tail || firstNode.freq > 1) {
                Node newNode = new Node(1);
                newNode.strSet.add(key);
                newNode.prev = head;
                newNode.next = firstNode;
                head.next = newNode;
                firstNode.prev = newNode;
                strToNodeMap.put(key, newNode);
            } else {
                firstNode.strSet.add(key);
                strToNodeMap.put(key, firstNode);
            }
        }
    }

    public void dec(String key) {
        if (!strToNodeMap.containsKey(key)) {
            return;
        }

        Node node = strToNodeMap.get(key);
        node.strSet.remove(key);
        int freq = node.freq;

        if (freq == 1) {
            strToNodeMap.remove(key);
        } else {
            Node prevNode = node.prev;
            if (prevNode == head || prevNode.freq != freq - 1) {
                Node newNode = new Node(freq - 1);
                newNode.strSet.add(key);
                newNode.prev = prevNode;
                newNode.next = node;
                prevNode.next = newNode;
                node.prev = newNode;
                strToNodeMap.put(key, newNode);
            } else {
                prevNode.strSet.add(key);
                strToNodeMap.put(key, prevNode);
            }
        }

        if (node.strSet.isEmpty()) {
            deleteNode(node);
        }
    }

    public String getMaxKey() {
        if (tail.prev == head) {
            return "";
        }
        return tail.prev.strSet.iterator().next();
    }

    public String getMinKey() {
        if (head.next == tail) {
            return "";
        }
        return head.next.strSet.iterator().next();
    }

    private void deleteNode(Node node) {
        Node prevNode = node.prev;
        Node afterNode = node.next;

        prevNode.next = afterNode;
        afterNode.prev = prevNode;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */