package part1.week1.iq;

import part1.Constants;

/**
 * Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence of
 * integers followed immediately by a decreasing sequence of integers. Write a program that, given a
 * bitonic array of nn distinct integer values, determines whether a given integer is in the array
 */
public class Task5 {

    public int bitonicSearch(int[] array, int value) {
        return bitonicSearch(array, value, 0, array.length - 1);
    }

    private int bitonicSearch(int[] array, int value, int low, int high) {
        if (high <= 1) {
            return Constants.MINUS_ONE;
        }
        int mid = (low + high) / 2;
        if (value == array[mid]) {
            return mid;
        }

        if (low >= high) {
            return Constants.MINUS_ONE;
        }

        if (array[mid] < array[mid + 1]) {
            if (value < array[mid]) {
                int index = binarySearch(array, value, low, mid - 1);
                if (index != Constants.MINUS_ONE) {
                    return index;
                }
            }
            return bitonicSearch(array, value, mid + 1, high);

        }
        else {
            if (value < array[mid]) {
                int index = binarySearch(array, value, mid + 1, high);
                if (index != Constants.MINUS_ONE) {
                    return index;
                }
            }
            return bitonicSearch(array, value, low, mid - 1);
        }
    }

    private int binarySearch(int[] array, int value, int low, int high) {
        if (low > high) {
            return Constants.MINUS_ONE;
        }
        int mid = (low + high) / 2;
        if (value == array[mid]) {
            return mid;
        }
        else if (value < array[mid]) {
            return binarySearch(array, value, low, mid - 1);
        }
        else {
            return binarySearch(array, value, mid + 1, high);
        }
    }
}
