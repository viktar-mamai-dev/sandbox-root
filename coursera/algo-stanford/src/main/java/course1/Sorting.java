package course1;

public class Sorting {

    public int comparesCount = 0;

    private int partition(Integer[] a, int low, int high) {
        if (low >= high) {
            return low;
        }
        int i = low;
        int j = high;
        Integer pivot = a[low];
        while (true) {
            while (compare(a[i], pivot) <= 0) {
                i++;
                if (i == high) {
                    break;
                }
            }

            while (compare(a[j], pivot) > 0) {
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

    private int partition222(Integer[] a, int low, int high) {
        if (low >= high) {
            return low;
        }
        int i = low + 1;
        Integer pivot = a[low];
        for (int j = low + 1; j <= high; j++) {
            if (compare(a[j], pivot) < 0) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, low, i - 1);
        return i - 1;
    }

    private int partition333(Integer[] a, int low, int high) {
        if (low >= high) {
            return low;
        }
        int i = low + 1;
        Integer pivot = a[high];
        swap(a, low, high);
        for (int j = low + 1; j <= high; j++) {
            if (compare(a[j], pivot) < 0) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, low, i - 1);
        return i - 1;
    }

    private int partition444(Integer[] a, int low, int high) {
        if (low >= high) {
            return low;
        }
        comparesCount += (high - low);
        int i = low + 1;
        int pivotIndex = getIndexForMedianAmong(a, low, (low + high) / 2, high);
        Integer pivot = a[pivotIndex];
        swap(a, low, pivotIndex);
        for (int j = low + 1; j <= high; j++) {
            if (compare(a[j], pivot) < 0) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, low, i - 1);
        return i - 1;
    }

    private int getIndexForMedianAmong(Integer[] a, int low, int i, int high) {
        if (a[low] < a[i]) {
            if (a[i] < a[high]) {
                return i;
            }
            return a[low] > a[high] ? low : high;
        }

        if (a[i] > a[high]) {
            return i;
        }

        return a[low] < a[high] ? low : high;
    }

    private void quickSort(Integer[] array, int low, int high) {
        if (low >= high) {
            return;
        }

        int j = partition444(array, low, high);
        quickSort(array, low, j - 1);
        quickSort(array, j + 1, high);

    }

    public void quickSort(Integer[] array) {
        comparesCount = 0;
        quickSort(array, 0, array.length - 1);
    }

    private void swap(Integer[] array, int indexI, int indexJ) {
        if (indexI == indexJ)
            return;
        Integer temp = array[indexI];
        array[indexI] = array[indexJ];
        array[indexJ] = temp;
    }

    private int compare(Integer a, Integer b) {
        //comparesCount++;
        return Integer.compare(a, b);
    }
}
