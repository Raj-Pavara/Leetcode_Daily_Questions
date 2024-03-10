import java.util.ArrayList;
import java.util.Arrays;

public class march_10_24 {

  public static void main(String args[]) {
    int arr1[] = { 1, 2, 2, 1 };
    int arr2[] = { 2, 2 };
    int ans[] = intersection(arr1, arr2);
    for (int i = 0; i < ans.length; i++) {
      System.out.println(ans[i]);
    }
  }

  
  public static int[] intersection(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);

    int i = 0, j = 0;
    ArrayList<Integer> al = new ArrayList<>();
    while (i < nums1.length && j < nums2.length) {
      if (nums1[i] == nums2[j]) {
        if (al.size() == 0) {
          al.add(nums1[i]);
        } else if (al.get(al.size() - 1) != nums1[i]) {
          al.add(nums1[i]);
        }
        i++;
        j++;
      } else if (nums1[i] < nums2[j]) {
        i++;
      } else {
        j++;
      }
    }
    int ans[] = new int[al.size()];
    for (int k = 0; k < al.size(); k++) {
      ans[k] = al.get(k);
    }
    return ans;
  }
}
