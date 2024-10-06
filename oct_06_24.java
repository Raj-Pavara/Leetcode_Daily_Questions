/*1813. Sentence Similarity III

You are given two strings sentence1 and sentence2, each representing a sentence composed of words. A sentence is a list of words that are separated by a single space with no leading or trailing spaces. Each word consists of only uppercase and lowercase English characters.

Two sentences s1 and s2 are considered similar if it is possible to insert an arbitrary sentence (possibly empty) inside one of these sentences such that the two sentences become equal. Note that the inserted sentence must be separated from existing words by spaces.

For example,

s1 = "Hello Jane" and s2 = "Hello my name is Jane" can be made equal by inserting "my name is" between "Hello" and "Jane" in s1.
s1 = "Frog cool" and s2 = "Frogs are cool" are not similar, since although there is a sentence "s are" inserted into s1, it is not separated from "Frog" by a space.
Given two sentences sentence1 and sentence2, return true if sentence1 and sentence2 are similar. Otherwise, return false.

Example 1:

Input: sentence1 = "My name is Haley", sentence2 = "My Haley"

Output: true

Explanation:

sentence2 can be turned to sentence1 by inserting "name is" between "My" and "Haley".

Example 2:

Input: sentence1 = "of", sentence2 = "A lot of words"

Output: false

Explanation:

No single sentence can be inserted inside one of the sentences to make it equal to the other.

Example 3:

Input: sentence1 = "Eating right now", sentence2 = "Eating"

Output: true

Explanation:

sentence2 can be turned to sentence1 by inserting "right now" at the end of the sentence.

Constraints:

1 <= sentence1.length, sentence2.length <= 100
sentence1 and sentence2 consist of lowercase and uppercase English letters and spaces.
The words in sentence1 and sentence2 are separated by a single space. */
public class oct_06_24 {
    public static void main(String args[]) {

    }

    private List<String> convert(String sentence) {
        sentence += " ";
        List<String> ans = new ArrayList<>();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == ' ') {
                ans.add(word.toString());
                word.setLength(0); // Clear the StringBuilder for the next word
            } else {
                word.append(sentence.charAt(i));
            }
        }

        return ans;
    }

    public boolean areSentencesSimilar(String x, String y) {
        // Ensure x is always the longer sentence to avoid else-if conditions
        if (x.length() < y.length()) {
            String temp = x;
            x = y;
            y = temp;
        }

        // First, store the words from both sentences
        List<String> vx = convert(x);
        List<String> vy = convert(y);

        int l = 0;
        // Match words from the prefix part
        for (int i = 0; i < vy.size(); i++) {
            if (vx.get(i).equals(vy.get(i))) {
                l++;
            } else {
                break;
            }
        }

        int ind = vx.size() - 1, r = vy.size();
        // Match words from the suffix part
        for (int i = vy.size() - 1; i >= 0; i--) {
            if (vy.get(i).equals(vx.get(ind))) {
                ind--;
                r = i;
            } else {
                break;
            }
        }

        // Check if the suffix overlaps with the prefix
        return r <= l;
    }
}