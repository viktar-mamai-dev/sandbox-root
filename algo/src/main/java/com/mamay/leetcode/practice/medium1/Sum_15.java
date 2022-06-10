package com.mamay.leetcode.practice.medium1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Sum_15 {

    public List<List<Integer>> threeSum(int[] nums, int sum) {
        List<List<Integer>> resList = new ArrayList<>();

        Arrays.sort(nums);
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum2 = nums[i] + nums[j] + nums[k];
                if (sum2 == sum) {

                    String str = nums[i] + ":" + nums[j] + ":" + nums[k];
                    if (!set.contains(str)) {
                        set.add(str);

                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        resList.add(list);
                    }

                    j++; // increment the second value index
                    k--; // decrement the third value index

                } else if (sum2 < sum) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return resList;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        return threeSum(nums, 0);
    }

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    private static int[] readIntegerArray(Scanner scanner) {
        String tokens[] = scanner.nextLine().replaceAll("\\[|\\]", "").split(",");
        int len = tokens.length;
        int res[] = new int[len];
        for (int k = 0; k < len; k++) {
            res[k] = Integer.parseInt(tokens[k].trim());
        }
        System.out.println("Length of array is: " + len);
        return res;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File(INPUT_FILE));
        int q = scanner.nextInt();
        scanner.nextLine();

        Sum_15 main = new Sum_15();
        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
            for (int qi = 0; qi < q; qi++) {
                int matrix[] = readIntegerArray(scanner);

                writer.println(main.threeSum(matrix));
            }
            scanner.close();
        }
    }
}
