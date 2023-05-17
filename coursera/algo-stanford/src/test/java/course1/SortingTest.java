package course1;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class SortingTest {

    @Test
    public void test1() {
        ArrayHelper<Integer> helper = new ArrayHelper<Integer>();

        Sorting main = new Sorting();

        Integer[] arr1 = {60, 10, 40, 90, 32, 80, 45};
        main.quickSort(arr1);
        helper.printArray(arr1);

        try (Scanner in = new Scanner(new File("src\\main\\resources\\quickSort.txt"))) {
            Integer[] arr = new Integer[10000];
            int i = 0;
            while (in.hasNextInt()) {
                arr[i] = in.nextInt();
                i++;
            }

            //helper.printArray(arr);
            main.quickSort(arr);
            //helper.printArray(arr);

            System.out.println("COmpares: " + main.comparesCount);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {

        Sorting main = new Sorting();

        String[] strArr = ("2148  9058  7742  3153  6324  609  7628  5469  7017  504  4092  1582  " +
                "9572  1542  5697  2081  4218  3130  7923  9595  6558  3859  9832  3062  6788  " +
                "7578  7432  479  8439  9079  7173  2667  2770  2655  972  4264  2014  3171  4715 " +
                " 345  4388  3816  8887  3915  3490  2327  123  4596  4307  8737  4007  " +
                "6798  6551  1627  1190  4984  2480  3404  2027  4778  2951  2795  5002  8121  8910  9593  5254  " +
                "448  6237  5565  1816  392  8143  9310  9293  3138  4869  6756  872  6183  3517  " +
                "3513  1676  5498  9172  5739  6108  7538  7671  5780  8666  540  9771  6837  9341 " +
                " 1590  5689  1605  1103  5859").split("  ");
        Integer[] objects = Arrays.stream(strArr).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[][] arrArr = {
                new Integer[]{2, 20, 1, 15, 3, 11, 13, 6, 16, 10, 19, 5, 4, 9, 8, 14, 18, 17, 7, 12},
                objects,
                new Integer[]{2148, 9058, 7742, 3153, 6324, 609, 7628, 5469, 7017, 504}
        };

        int[] expectedCounts = {55, 502, 21};

        for (int i = 0; i < arrArr.length; i++) {
            Integer[] arr = arrArr[i];
            main.quickSort(arr);

            System.out.println("Compares count: " + main.comparesCount);
            Assert.assertEquals(expectedCounts[i], main.comparesCount);
        }

        ArrayHelper<Integer> helper = new ArrayHelper<Integer>();
        helper.printArray(arrArr[1]);
    }
}