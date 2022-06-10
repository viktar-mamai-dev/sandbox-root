package part1.week2;

public class Sorting {
    public void selectionSort(Integer[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int indexMinimum = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[indexMinimum]) {
                    indexMinimum = j;
                }
            }
            swap(array, i, indexMinimum);
        }
    }

    public void insertionSort(Integer[] array) {
        insertionSort(array, 0, array.length - 1);
    }

    public void insertionSort(Integer[] array, int low, int high) {
        for (int i = low; i <= high; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    private void swap(Integer[] array, int indexI, int indexJ) {
        if (indexI == indexJ)
            return;
        int temp = array[indexI];
        array[indexI] = array[indexJ];
        array[indexJ] = temp;
    }
}
