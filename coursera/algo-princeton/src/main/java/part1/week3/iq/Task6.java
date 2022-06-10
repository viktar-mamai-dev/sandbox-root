/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

package part1.week3.iq;

import java.util.ArrayList;
import java.util.List;

/**
 * Decimal dominants. Given an array with n keys, design an algorithm to find all values that occur
 * more than k times. The expected running time of your algorithm should be linear
 */
public class Task6 {
    private List<Integer> resultList = new ArrayList<>();

    public List<Integer> findFrequent(int[] array, int frequency) {
        resultList.clear();
        findFrequent(array, frequency, 0, array.length - 1);
        return resultList;
    }

    public void findFrequent(int[] array, int k, int low, int high) {
        if (low >= high) {
            return;
        }

        int leftBound = low;
        int rightBound = high;

        int i = low;
        int v = array[low];
        while (i <= rightBound) {
            if (array[i] > v) {
                swap(array, i, rightBound--);
            } else if (array[i] < v) {
                swap(array, leftBound++, i++);
            } else {
                i++;
            }
        }

        if (rightBound - leftBound + 1 >= k) {
            resultList.add(v);
        }
        findFrequent(array, k, low, leftBound - 1);
        findFrequent(array, k, rightBound + 1, high);
    }

    private void swap(int[] array, int indexI, int indexJ) {
        if (indexI == indexJ)
            return;
        int temp = array[indexI];
        array[indexI] = array[indexJ];
        array[indexJ] = temp;
    }
}
