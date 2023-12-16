/*
 * Copyright (c) 2023
 */
package com.mamay.book2.ch5;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class NumberFormatTester {
  public static void main(String[] args) {
    NumberFormat nf = NumberFormat.getInstance();
    try {
      System.out.println(nf.parse("456abc")); // 456
      System.out.println(nf.parse("-123.45x10")); // -123.45
      System.out.println(nf.parse("x10")); // parseexception
    } catch (ParseException e) { // checked exception
      e.printStackTrace();
    }

    LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
    LocalTime time = LocalTime.of(11, 12, 34);
    LocalDateTime dateTime = LocalDateTime.of(date, time);

    DateTimeFormatter shortFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
    System.out.println(shortFormatter.format(dateTime)); // 1/20/20
    System.out.println(shortFormatter.format(date)); // 1/20/20
    System.out.println(shortFormatter.format(time)); // UnsupportedTemporalTypeException
    // the same:
    System.out.println(dateTime.format(shortFormatter)); // 1/20/20
    System.out.println(date.format(shortFormatter)); // 1/20/20
    System.out.println(time.format(shortFormatter)); // UnsupportedTemporalTypeException

    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm"); // custom formatter
    System.out.println(dateTime.format(formatter));
  }
}
