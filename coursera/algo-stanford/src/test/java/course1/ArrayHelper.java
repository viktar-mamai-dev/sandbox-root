/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

package course1;

import java.util.Arrays;
import java.util.List;

public class ArrayHelper<T> {
    public void printArray(T[] array) {
        System.out.println("Printing array....");
        for (T anArray : array) {
            System.out.print(anArray);
            System.out.print(" ");
        }
        System.out.println("\n============================");
    }

    public void printArrays(T[]... arrayList) {
        System.out.println("Printing arrays....");
        for (T[] array1 : arrayList) {
            for (T anArray : array1) {
                System.out.print(anArray);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("\n============================");
    }

    public void printArray(List<T> array) {
        System.out.println("Printing list....");
        for (T anArray : array) {
            System.out.print(anArray);
            System.out.print(" ");
        }
        System.out.println("\n============================");
    }

    public Integer[] copy(Integer[] array) {
        int n = array.length;
        Integer[] copy = new Integer[n];
        System.arraycopy(array, 0, copy, 0, n);
        return copy;
    }

    public boolean isSorted(T[] array) {
        int n = array.length;
        T[] copy = (T[]) new Object[n];
        System.arraycopy(array, 0, copy, 0, n);
        return Arrays.equals(array, copy);
    }

    private void swap(T[] array, int indexI, int indexJ) {
        if (indexI == indexJ)
            return;
        T temp = array[indexI];
        array[indexI] = array[indexJ];
        array[indexJ] = temp;
    }
}
