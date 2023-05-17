package course2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Sum2 {

    private final int n = 1_000_000;
    private final long[] arr = new long[n];

    public static void main(String[] args) {
        Sum2 main = new Sum2();
        main.readGraph();
        //int count = main.algorithm();
        int count = main.twoSum();
        System.out.println(count);
    }

    private int algorithm() {
        Arrays.sort(arr);
        int count = 0;
        for (long t = -10000; t <= 10000; t++) {
            int j = 0;
            int k = n - 1;
            boolean isFound = false;

            while (j < k && !isFound) {
                long val = arr[j] + arr[k] - t;

                if (val < 0) {
                    j++;

                } else if (val > 0) {
                    k--;
                } else {
                    count++;
                    isFound = true;
                    System.out.println(t + " = " + arr[j] + " + " + arr[k]);
                }
            }
        }
        return count;
    }

    public int twoSum() {
        int count = 0;
        Arrays.sort(arr);
        for (long t = -10000; t <= 10000; t++) {
            // System.out.println("Processing " + t );
            int[] idx = twoSum(t);
            if (idx != null) {
                count++;
                System.out.println(t + " = " + arr[idx[0]] + " + " + arr[idx[1]]);
            }
        }
        return count;
    }

    public int[] twoSum(long target) {
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        long complement;
        //loop to check every element in the array
        for (int i = 0; i < arr.length; i++) {
            complement = target - arr[i];
            //if we already have the complement in HashMap, return an array that contains indices of them.
            if (map.containsKey(complement)) {
                return new int[]{i, map.get(complement)};
            }
            map.put(arr[i], i);
        }
        return null;
    }

    public boolean algo22(long target) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    private void readGraph() {
        int i = 0;
        try (Scanner scanner = new Scanner(new File("src/main/resources/2sum.txt"))) {
            while (scanner.hasNextLong()) {
                arr[i] = scanner.nextLong();
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
