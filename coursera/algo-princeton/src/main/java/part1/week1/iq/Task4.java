package part1.week1.iq;

import java.util.Arrays;

/**
 * 3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional
 * to n^2 in the worst case. You may assume that you can sort the nn integers in time proportional
 * to n^2 or better.
 */
public class Task4 {
  /*
  returns true if exists Triple with the total sum = sum
  complexity = O(n*n)
   */
  public boolean sum3InArray(int[] array, int sum) {
    Arrays.sort(array);

    int n = array.length;
    for (int i = 0; i < n; i++) {
      int j = i + 1;
      int k = n - 1;
      while (j < k) {
        int val = array[i] + array[j] + array[k];
        if (val == sum) {
          return true;
        } else if (val > sum) {
          k--;
        } else {
          j++;
        }
      }
    }
    return false;
  }
}
