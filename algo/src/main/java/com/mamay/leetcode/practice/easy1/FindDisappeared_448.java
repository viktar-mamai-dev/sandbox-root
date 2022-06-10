package com.mamay.leetcode.practice.easy1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindDisappeared_448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int a1 = nums[i];
            int a2 = nums[a1 - 1];
            while (a1 != a2) {
                swap(nums, i, a1 - 1);
                a1 = nums[i];
                a2 = nums[a1 - 1];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                list.add(i + 1);
            }
        }

        return list;
    }

    private void swap(int[] nums, int a1, int a2) {
        int temp = nums[a1];
        nums[a1] = nums[a2];
        nums[a2] = temp;
    }

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File(INPUT_FILE));
        int q = scanner.nextInt();
        scanner.nextLine();

        FindDisappeared_448 main = new FindDisappeared_448();
        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(OUTPUT_FILE)))) {
            for (int qi = 0; qi < q; qi++) {

                int matrix[] = readIntegerArray(scanner);

                writer.println(main.findDisappearedNumbers(matrix));
            }
            scanner.close();
        }
    }

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

    public List<Integer> findDisappearedNumbers21(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int j = Math.abs(nums[i]) - 1;
            nums[j] = -1 * Math.abs(nums[j]); //make negative
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
