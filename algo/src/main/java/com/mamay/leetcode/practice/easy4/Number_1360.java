package com.mamay.leetcode.practice.easy4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Number_1360 {
  public int daysBetweenDates(String date1, String date2) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date11 = LocalDate.parse(date1, dateTimeFormatter);
    LocalDate date22 = LocalDate.parse(date2, dateTimeFormatter);
    int between = (int) ChronoUnit.DAYS.between(date11, date22);
    return Math.abs(between);
  }

  public static void main(String[] args) throws IOException {
    Path inputPath = Paths.get("leetcode", "src", "main", "resources", "input.txt");
    Path outputPath = Paths.get("leetcode", "src", "main", "resources", "output.txt");
    Number_1360 main = new Number_1360();
    try (Scanner scanner = new Scanner(inputPath)) {
      int q = scanner.nextInt();
      scanner.nextLine();

      try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {
        for (int qi = 0; qi < q; qi++) {
          String date1 = scanner.nextLine();
          String date2 = scanner.nextLine();
          writer.println(main.daysBetweenDates(date1, date2));
        }
      }
    }
  }
}
