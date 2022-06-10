package part1.week3;

public class Sorting {

    private static final int QUICKSORT_CUTOFF = 10;

    private void merge(Comparable[] a, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int n = high - low + 1;
        Comparable[] aux = new Comparable[n];
        for (int k = 0; k < n; k++) {
            if (i > mid) {
                aux[k] = a[j++];
            } else if (j > high) {
                aux[k] = a[i++];
            } else if (a[j].compareTo(a[i]) < 0) {
                aux[k] = a[j++];
            } else {
                aux[k] = a[i++];
            }
        }
        System.arraycopy(aux, 0, a, low, n);
    }

    private void mergeSort(Comparable[] a, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = (low + high) / 2;
        mergeSort(a, low, mid);
        mergeSort(a, mid + 1, high);
        /*if (a[mid].compareTo(a[mid + 1]) < 0) {
            return;
        }*/
        merge(a, low, mid, high);
    }

    public void mergeSort(Comparable[] a) {
        int n = a.length;
        mergeSort(a,0, n - 1);
    }

    private int partition(Comparable[] a, int low, int high) {
        if (low >= high) {
            return low;
        }
        int i = low;
        int j = high;
        Comparable pivot = a[low];
        while (true) {
            while (a[i].compareTo(pivot) <= 0) {
                i++;
                if (i == high) {
                    break;
                }
            }

            while (a[j].compareTo(pivot) > 0) {
                j--;
                if (j == low) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            swap(a, i, j);
        }
        swap(a, low, j);
        return j;
    }

    private void quickSort(Integer[] array, int low, int high) {
        if (low + QUICKSORT_CUTOFF - 1 >= high) {
            part1.week2.Sorting sorting = new part1.week2.Sorting();
            sorting.insertionSort(array, low, high);
            return;
        }

        int j = partition(array, low, high);
        quickSort(array, low, j - 1);
        quickSort(array, j + 1, high);

    }

    public void quickSort(Integer[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void swap(Comparable[] array, int indexI, int indexJ) {
        if (indexI == indexJ)
            return;
        Comparable temp = array[indexI];
        array[indexI] = array[indexJ];
        array[indexJ] = temp;
    }
}
