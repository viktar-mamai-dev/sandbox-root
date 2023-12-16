package com.mamay;

import com.mamay.leetcode.AlgoRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class FileReaderWriter {

    /* Specify input and output files here */
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    private static final String REGEX_DELETE = "[\\[\\]]";
    private static final String REGEX_SPLIT = "[, ]+";
    private static final String REGEX_ONELINE_SPLIT = "](\\s)+,\\s+\\[";

    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = FileReaderWriter.class.getClassLoader();
        URL inputResource = classLoader.getResource(INPUT_FILE);
        URL outputResource = classLoader.getResource(OUTPUT_FILE);

        try (Scanner scanner = new Scanner(new File(inputResource.getPath()));
             PrintWriter writer = new PrintWriter(new FileWriter(outputResource.getPath()))) {

            /* Step 1. Implement your file read here*/
            int[] integerArray = readIntegerArray(scanner);
            out.println(Arrays.toString(integerArray));
            String[] stringArray = readStringArray(scanner);
            out.println(Arrays.toString(stringArray));

            int[][] intMatrixOneLined = readMatrixOneLined(scanner);
            cout(intMatrixOneLined);

            int[][] intMatrixSized = readMatrixWithSize(scanner, 3);
            cout(intMatrixSized);

            int[][] intMatrix = readMatrix(scanner);
            cout(intMatrix);
            /* comment above code */
/*
            int q = scanner.nextInt();
            scanner.nextLine();
*/
            /* Step 2. Implement yor algorithm logix here */

            AlgoRunner algoRunner = new AlgoRunner();

            /* Step 3. Implement your file write here */

            writer.println("FILE writing");
        }
    }

    private static String[] readStringArray(Scanner scanner) {
        String[] tokens = scanner.nextLine().replaceAll(REGEX_DELETE, "").split(REGEX_SPLIT);
        int len = tokens.length;
        System.out.println("Length of array is: " + len);
        return tokens;
    }

    private static int[] readIntegerArray(Scanner scanner) {
        String[] tokens = scanner.nextLine().replaceAll(REGEX_DELETE, "").split(REGEX_SPLIT);
        int len = tokens.length;
        int[] res = new int[len];
        for (int k = 0; k < len; k++) {
            res[k] = Integer.parseInt(tokens[k].trim());
        }
        System.out.println("Length of array is: " + len);
        return res;
    }

    private static int[][] readMatrix(Scanner scanner) {
        out.println("Entering read matrix method");
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line == null || line.trim().isEmpty()) break;
            lines.add(line);
        }
        int len = lines.size();
        int[][] res = new int[len][];
        for (int k = 0; k < len; k++) {
            String[] tokens = lines.get(k).replaceAll(REGEX_DELETE, "").split(REGEX_SPLIT);
            out.println(Arrays.toString(tokens));
            int len2 = tokens.length;
            res[k] = new int[len2];
            for (int m = 0; m < len2; m++) {
                res[k][m] = Integer.parseInt(tokens[m]);
            }
        }
        System.out.println("Size of matrix is: " + len + " * " + res[0].length);
        out.println("Exiting read matrix method");
        return res;
    }

    private static int[][] readMatrixWithSize(Scanner scanner, int size) {
        out.println("Entering read matrix with size method");

        int[][] res = new int[size][size];
        for (int k = 0; k < size; k++) {
            if (!scanner.hasNextLine()) {
                out.println("The size " + size + " is bigger than lines in files");
                break;
            }
            String[] tokens = scanner.nextLine().replaceAll(REGEX_DELETE, "").split(REGEX_SPLIT);
            int len2 = tokens.length;
            res[k] = new int[len2];
            for (int m = 0; m < len2; m++) {
                res[k][m] = Integer.parseInt(tokens[m]);
            }
        }
        System.out.println("Size of matrix is: " + size + " * " + size);
        out.println("Exiting read matrix with size method");
        return res;
    }

    private static int[][] readMatrixOneLined(Scanner scanner) {
        out.println("Entering read matrix one lined method");
        String[] lines = scanner.nextLine().split(REGEX_ONELINE_SPLIT);
        int len = lines.length;
        int[][] res = new int[len][];

        for (int k = 0; k < len; k++) {
            String[] tokens = lines[k].replaceAll(REGEX_DELETE, "").split(REGEX_SPLIT);
            int len2 = tokens.length;
            res[k] = new int[len2];
            for (int m = 0; m < len2; m++) {
                res[k][m] = Integer.parseInt(tokens[m]);
            }
        }
        System.out.println("Size of matrix is: " + len + " * " + res[0].length);
        out.println("Exiting read matrix one lined method");
        return res;
    }

    private static void cout(int[][] matrix) {
        for (int[] array : matrix) {
            out.println(Arrays.toString(array));
        }
    }

    private static void cout(String[][] matrix) {
        for (String[] array : matrix) {
            out.println(Arrays.toString(array));
        }
    }
}
