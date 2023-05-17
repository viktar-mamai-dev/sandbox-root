/*
 * Copyright (c) 2023
 */
package book1;

import static java.lang.System.out;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeTester {
  public static void main(String[] args) {
    LocalDate date1 = LocalDate.of(2019, 06, 19);
    LocalTime time1 = LocalTime.of(20, 45);
    LocalDateTime dateTime1 = LocalDateTime.of(date1, time1);

    out.println(date1.format(DateTimeFormatter.ISO_DATE));
    out.println(time1.format(DateTimeFormatter.ISO_TIME));
    out.println(dateTime1.format(DateTimeFormatter.ISO_DATE));
    out.println(dateTime1.format(DateTimeFormatter.ISO_TIME));
    out.println(dateTime1.format(DateTimeFormatter.ISO_DATE_TIME));

    // UnsupportedTemporalTypeException for all
    out.println(date1.format(DateTimeFormatter.ISO_TIME));
    out.println(date1.format(DateTimeFormatter.ISO_DATE_TIME));
    out.println(time1.format(DateTimeFormatter.ISO_DATE));
    out.println(time1.format(DateTimeFormatter.ISO_DATE_TIME));
  }
}
