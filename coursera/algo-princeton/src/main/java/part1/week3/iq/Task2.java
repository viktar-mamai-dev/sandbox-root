package part1.week3.iq;

/**
 * Counting inversions. An inversion in an array a[] is a pair of entries a[i] and a[j]
 * such that i < j and a[i]>a[j]. Given an array, design a linearithmic algorithm to
 * count the number of inversions
 */
public class Task2 {

    private int inversionCount(int[] a, int[] aux, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int inversionCount = 0;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                aux[k] = a[j++];
            } else if (j > high) {
                aux[k] = a[i++];
            } else if (a[j] < a[i]) {
                aux[k] = a[j++];
                inversionCount += (mid - i + 1);
            } else {
                aux[k] = a[i++];
            }
        }
        return inversionCount;
    }

    private int inversionCount(int[] a, int[] aux, int low, int high) {
        if (low >= high) {
            return 0;
        }

        int mid = (low + high) / 2;

        int inversionCount = inversionCount(aux, a, low, mid)
                + inversionCount(aux, a, mid + 1, high)
                + inversionCount(a, aux, low, mid, high);
        System.out.println("Low: " + low + " high: " + high + " inversions: " + inversionCount);
        return inversionCount;
    }

    public int inversionCount(int[] a) {
        int n = a.length;
        int[] aux = new int[n];
        for (int i = 0; i < n; i++) {
            aux[i] = a[i];
        }
        return inversionCount(a, aux, 0, n - 1);
    }
}
