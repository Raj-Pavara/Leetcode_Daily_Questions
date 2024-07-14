/*726. Number of Atoms
Given a string formula representing a chemical formula, return the count of each atom.

The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.

One or more digits representing that element's count may follow if the count is greater than 1. If the count is 1, no digits will follow.

For example, "H2O" and "H2O2" are possible, but "H1O2" is impossible.
Two formulas are concatenated together to produce another formula.

For example, "H2O2He3Mg4" is also a formula.
A formula placed in parentheses, and a count (optionally added) is also a formula.

For example, "(H2O2)" and "(H2O2)3" are formulas.
Return the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

The test cases are generated so that all the values in the output fit in a 32-bit integer.

 

Example 1:

Input: formula = "H2O"
Output: "H2O"
Explanation: The count of elements are {'H': 2, 'O': 1}.
Example 2:

Input: formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
Example 3:

Input: formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation: The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
 

Constraints:

1 <= formula.length <= 1000
formula consists of English letters, digits, '(', and ')'.
formula is always valid. */

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class july_14_24 {
    public static void main(String args[]) {

    }

     // Global variable
    int index = 0;

    public String countOfAtoms(String formula) {
        // Recursively parse the formula
        Map<String, Integer> finalMap = parseFormula(formula);

        // Sort the final map
        TreeMap<String, Integer> sortedMap = new TreeMap<>(finalMap);

        // Generate the answer string
        StringBuilder ans = new StringBuilder();
        for (String atom : sortedMap.keySet()) {
            ans.append(atom);
            if (sortedMap.get(atom) > 1) {
                ans.append(sortedMap.get(atom));
            }
        }

        return ans.toString();
    }

    // Recursively parse the formula
    private Map<String, Integer> parseFormula(String formula) {
        // Local variable
        Map<String, Integer> currMap = new HashMap<>();
        String currAtom = new String();
        String currCount = new String();

        // Iterate until the end of the formula
        while (index < formula.length()) {
            // UPPERCASE LETTER
            if (Character.isUpperCase(formula.charAt(index))) {
                if (!currAtom.isEmpty()) {
                    if (currCount.isEmpty()) {
                        currMap.put(
                            currAtom,
                            currMap.getOrDefault(currAtom, 0) + 1
                        );
                    } else {
                        currMap.put(
                            currAtom,
                            currMap.getOrDefault(currAtom, 0) +
                            Integer.parseInt(currCount)
                        );
                    }
                }

                currAtom = String.valueOf(formula.charAt(index));
                currCount = "";
                index++;
            }
            // lowercase letter
            else if (Character.isLowerCase(formula.charAt(index))) {
                currAtom += formula.charAt(index);
                index++;
            }
            // Digit. Concatenate the count
            else if (Character.isDigit(formula.charAt(index))) {
                currCount += formula.charAt(index);
                index++;
            }
            // Left Parenthesis
            else if (formula.charAt(index) == '(') {
                index++;
                Map<String, Integer> nestedMap = parseFormula(formula);
                for (String atom : nestedMap.keySet()) {
                    currMap.put(
                        atom,
                        currMap.getOrDefault(atom, 0) + nestedMap.get(atom)
                    );
                }
            }
            // Right Parenthesis
            else if (formula.charAt(index) == ')') {
                // Save the last atom and count of nested formula
                if (!currAtom.isEmpty()) {
                    if (currCount.isEmpty()) {
                        currMap.put(
                            currAtom,
                            currMap.getOrDefault(currAtom, 0) + 1
                        );
                    } else {
                        currMap.put(
                            currAtom,
                            currMap.getOrDefault(currAtom, 0) +
                            Integer.parseInt(currCount)
                        );
                    }
                }

                index++;
                StringBuilder multiplier = new StringBuilder();
                while (
                    index < formula.length() &&
                    Character.isDigit(formula.charAt(index))
                ) {
                    multiplier.append(formula.charAt(index));
                    index++;
                }
                if (multiplier.length() > 0) {
                    int mult = Integer.parseInt(multiplier.toString());
                    for (String atom : currMap.keySet()) {
                        currMap.put(atom, currMap.get(atom) * mult);
                    }
                }

                return currMap;
            }
        }

        // Save the last atom and count
        if (!currAtom.isEmpty()) {
            if (currCount.isEmpty()) {
                currMap.put(currAtom, currMap.getOrDefault(currAtom, 0) + 1);
            } else {
                currMap.put(
                    currAtom,
                    currMap.getOrDefault(currAtom, 0) +
                    Integer.parseInt(currCount)
                );
            }
        }

        return currMap;
    }
}