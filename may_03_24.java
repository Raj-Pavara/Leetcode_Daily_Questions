/*Compare Version Numbers

Given two version numbers, version1 and version2, compare them.

Version numbers consist of one or more revisions joined by a dot '.'. Each revision consists of digits and may contain leading zeros. Every revision contains at least one character. Revisions are 0-indexed from left to right, with the leftmost revision being revision 0, the next revision being revision 1, and so on. For example 2.5.33 and 0.1 are valid version numbers.

To compare version numbers, compare their revisions in left-to-right order. Revisions are compared using their integer value ignoring any leading zeros. This means that revisions 1 and 001 are considered equal. If a version number does not specify a revision at an index, then treat the revision as 0. For example, version 1.0 is less than version 1.1 because their revision 0s are the same, but their revision 1s are 0 and 1 respectively, and 0 < 1.

Return the following:

If version1 < version2, return -1.
If version1 > version2, return 1.
Otherwise, return 0.
 
Example 1:

Input: version1 = "1.01", version2 = "1.001"
Output: 0
Explanation: Ignoring leading zeroes, both "01" and "001" represent the same integer "1".

Example 2:

Input: version1 = "1.0", version2 = "1.0.0"
Output: 0
Explanation: version1 does not specify revision 2, which means it is treated as "0".

Example 3:

Input: version1 = "0.1", version2 = "1.1"
Output: -1
Explanation: version1's revision 0 is "0", while version2's revision 0 is "1". 0 < 1, so version1 < version2.
 
Constraints:

1 <= version1.length, version2.length <= 500
version1 and version2 only contain digits and '.'.
version1 and version2 are valid version numbers.
All the given revisions in version1 and version2 can be stored in a 32-bit integer.
 
*/
public class may_03_24 {
    public static void main(String args[]) {
        System.out.println(compareVersion("1.0", "1.0.0"));
    }

    public static int compareVersion(String version1, String version2) {
        int i = 0, j = 0;
      while (i < version1.length() && j < version2.length()) {
          int a = i;
          while (version1.charAt(i) != '.') {
              i++;
              if (version1.length() == i) {
                  break;
              }
          }
          int b = j;
          while (version2.charAt(j) != '.') {
              j++;
              if (version2.length() == j) {
                  break;
              }
          }
          int x;
          x = Integer.parseInt(version1.substring(a, i));
          i++;
          int y;
          y = Integer.parseInt(version2.substring(b, j));
          j++;
          if (x < y) {
              return -1;
          } else if (x > y) {
              return 1;
          }

        
      }

      while (i < version1.length()) {
          if (version1.charAt(i) != '.' && version1.charAt(i) != '0') {
              return 1;
          }
          i++;
      }

      while (j < version2.length()) {
          if (version2.charAt(j) != '.' && version2.charAt(j) != '0') {
              return -1;
          }
          j++;
      }
      return 0;
  }
}