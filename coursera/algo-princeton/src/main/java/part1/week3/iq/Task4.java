/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

package part1.week3.iq;

/**
 * Nuts and bolts. A disorganized carpenter has a mixed pile of n nuts and n bolts. The goal is to
 * find the corresponding pairs of nuts and bolts. Each nut fits exactly one bolt and each bolt fits
 * exactly one nut. By fitting a nut and a bolt together, the carpenter can see which one is bigger
 * (but the carpenter cannot compare two nuts or two bolts directly). Design an algorithm for the
 * problem that uses at most proportional to n * log n compares (probabilistically).
 */
public class Task4 {
    public void solve(String[] nuts, String[] bolts) {
        matchPairs(nuts, bolts, 0, nuts.length - 1);
    }

    // Method which works just like quick sort
    private void matchPairs(String[] nuts, String[] bolts, int low, int high) {
        if (low < high) {
            // Choose last character of bolts array for nuts partition.
            int pivot = partition(nuts, low, high, bolts[high]);

            // Now using the partition of nuts choose that for bolts partition.
            partition(bolts, low, high, nuts[pivot]);

            // Recur for [low...pivot-1] & [pivot+1...high] for nuts and bolts array.
            matchPairs(nuts, bolts, low, pivot - 1);
            matchPairs(nuts, bolts, pivot + 1, high);
        }
    }

    // Similar to standard partition method. Here we pass the pivot element
    // too instead of choosing it inside the method.
    private int partition(String[] array, int low, int high, String pivot) {
        int i = low;
        for (int j = low; j < high; j++) {
            if (array[j].compareTo(pivot) < 0) {
                swap(array, i, j);
                i++;
            }
            else if (array[j].equals(pivot)) {
                swap(array, j, high);
                j--;
            }
        }
        swap(array, i, high);

        // Return the partition index of an array based on the pivot element of other array.
        return i;
    }

    private void swap(String[] array, int indexI, int indexJ) {
        if (indexI == indexJ)
            return;
        String temp = array[indexI];
        array[indexI] = array[indexJ];
        array[indexJ] = temp;
    }
}
