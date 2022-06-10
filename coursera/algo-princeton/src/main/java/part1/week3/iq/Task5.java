/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

package part1.week3.iq;

/**
 * Given two sorted arrays a[] and b[], of sizes n1 and n2 respectively, design an algorithm to find
 * the k th largest key. The order of growth of the worst case running time of your algorithm should be log(n1+n2)
 */
public class Task5 {
    public int rank(int[] a, int[] b, int k) {
        return rank(a, 0, b, 0, k);
    }

    public int rank(int[] a, int alow, int[] b, int blow, int k) {
        if (alow >= a.length) {
            return rank(b, blow, k);
        }
        if (blow >= b.length) {
            return rank(a, alow, k);
        }
        if (k == 1) {
            return Math.min(a[alow], b[blow]);
        }
        if (a[alow] < b[blow]) {
            return rank(a, alow + 1, b, blow, k - 1);
        } else {
            return rank(a, alow, b, blow + 1, k - 1);
        }
    }

    public int rank(int[] a, int alow, int k) {
        if (alow >= a.length) {
            throw new IllegalArgumentException("Rank is greater than length of array");
        }
        if (k == 1) {
            return a[alow];
        }
        return rank(a, alow + 1, k - 1);
    }
}
