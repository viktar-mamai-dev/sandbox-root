package part1.week2.iq;

/**
 * Given two integer arrays of size nn, design a subquadratic algorithm to determine whether one is a permutation
 * of the other. That is, do they contain exactly the same entries but, possibly, in a different order
 */
public class Task5 {
    public boolean isPermutation(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            boolean isFound = false;
            for (int j = i; j < b.length && !isFound; j++) {
                if (a[i] == b[j]) {
                    swap(b, i, j);
                    isFound = true;
                }
            }
            if (!isFound) {
                return false;
            }
        }
        return true;
    }

    private void swap(int[] array, int indexI, int indexJ) {
        if (indexI == indexJ)
            return;
        int temp = array[indexI];
        array[indexI] = array[indexJ];
        array[indexJ] = temp;
    }
}
