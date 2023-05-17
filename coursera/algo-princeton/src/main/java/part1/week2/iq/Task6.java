package part1.week2.iq;

import edu.princeton.cs.algs4.StdRandom;
import part1.week2.PebbleColor;

/**
 * Given an array of nn buckets, each containing a red, white, or blue pebble, sort them by color.
 * The allowed operations are: swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
 * color(i): determine the color of the pebble in bucket i. The performance requirements are as
 * follows:
 *
 * <p>At most n calls to color(). At most n calls to swap(). Constant extra space.
 */
public class Task6 {
  private final PebbleColor[] array;

  public Task6(PebbleColor[] arr) {
    this.array = arr;
  }

  public PebbleColor get(int index) {
    return array[index];
  }

  private void swap(PebbleColor[] array, int indexI, int indexJ) {
    if (indexI == indexJ) return;
    PebbleColor temp = array[indexI];
    array[indexI] = array[indexJ];
    array[indexJ] = temp;
  }

  public void sort() {
    int n = array.length;
    int leftEnd = 0;
    int rightEnd = n - 1;

    int i = 0;
    while (i <= rightEnd) {
      if (PebbleColor.RED.equals(array[i])) {
        if (leftEnd < i) { // if there were some whites - swap
          swap(array, i, leftEnd); // all REDs in the beginning
        }
        i++;
        leftEnd++;
      } else if (PebbleColor.BLUE.equals(array[i])) {
        // skip all BLUE on the current right hand
        while (i < rightEnd && PebbleColor.BLUE.equals(array[rightEnd])) {
          rightEnd--;
        }
        swap(array, i, rightEnd); // all BLUEs in the end
        rightEnd--;
      } else if (PebbleColor.WHITE.equals(array[i])) { // skip all WHITE
        i++;
      }
    }
  }

  /** shuffle algorithm */
  public void shuffle() {
    int n = array.length;
    for (int i = 1; i < n; i++) {
      int randIndex = StdRandom.uniform(i); // 0 <= randIndex <= i
      swap(array, i, randIndex);
    }
  }

  public PebbleColor[] getArray() {
    int n = array.length;
    PebbleColor[] res = new PebbleColor[n];
    System.arraycopy(array, 0, res, 0, n);
    return res;
  }
}
