package part1.week3.iq;

import java.util.Arrays;

/**
 * Merging with smaller auxiliary array. Suppose that the subarray a[0] to a[n−1] is sorted and
 * the subarray a[n] to a[2∗n−1] is sorted. How can you merge the two subarrays so that a[0] to
 * a[2∗n−1] is sorted using an auxiliary array of length n (instead of 2n)?
 */
public class Task1 {
    /**
     * size(a) = 2*n
     *
     * @param a
     */
    public Integer[] merge(Integer[] a) {
        int n = a.length / 2;
        final Integer[] aux = new Integer[n]; // we guarantee that size(aux) = n
        int i = 0;
        int j = n;
        // 1st step of merge
        for (int k = 0; k < n; k++) {
            if (a[j] < a[i]) {
                aux[k] = a[j++];
            }
            else {
                aux[k] = a[i++];
            }
        }
        for (int k = 0; k < n - i; k++) {
            a[n + k] = a[i + k];
        }

        for (int k = 0; k < n; k++) {
            a[k] = aux[k];
        }

        i = n;
        int mid = j;
        int high = a.length;
        for (int k = 0; k < n; k++) {
            if (i >= mid) {
                aux[k] = a[j++];
            }
            else if (j >= high) {
                aux[k] = a[i++];
            }
            else if (a[j] < a[i]) {
                aux[k] = a[j++];
            }
            else {
                aux[k] = a[i++];
            }
        }
        for (int k = 0; k < n; k++) {
            a[k + n] = aux[k];
        }
        return a;
    }
}
