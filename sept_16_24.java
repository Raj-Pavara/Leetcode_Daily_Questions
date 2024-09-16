/*539. Minimum Time Difference

Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.
 
Example 1:

Input: timePoints = ["23:59","00:00"]
Output: 1

Example 2:

Input: timePoints = ["00:00","23:59","00:00"]
Output: 0

Constraints:

2 <= timePoints.length <= 2 * 104
timePoints[i] is in the format "HH:MM". 
*/
public class sept_16_24 {
    public static void main(String args[]) {

    }

    public int findMinDifference(List<String> tp) {
        int mini = Integer.MAX_VALUE;
        List<Integer> minutes = new ArrayList<>();

        // Convert each time point to minutes
        for (String val : tp) {
            int hr = 10 * (val.charAt(0) - '0') + (val.charAt(1) - '0'); // Extract hours
            int min = 10 * (val.charAt(3) - '0') + (val.charAt(4) - '0'); // Extract minutes
            minutes.add(hr * 60 + min); // Convert to total minutes
        }

        // Sort the minutes
        Collections.sort(minutes);

        // Calculate the minimum difference between adjacent times
        for (int i = 1; i < minutes.size(); i++) {
            mini = Math.min(mini, minutes.get(i) - minutes.get(i - 1));
        }

        // Also, check the difference between the first and last time, considering the
        // 24-hour wraparound
        int n = minutes.size();
        mini = Math.min(mini, 1440 - (minutes.get(n - 1) - minutes.get(0)));

        return mini;
    }
}