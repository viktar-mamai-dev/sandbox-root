/*
 * Copyright (c) 2023
 */
package com.mamay.lambdas.pack3;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArraySortedTogether {
  public static void main(String[] args) {
    Integer[] arr1 = {4, 78, 34};
    Integer[] arr2 = {90, 8, 12};

    List<Integer> collect =
        Stream.concat(Stream.of(arr1), Stream.of(arr2)).sorted().collect(Collectors.toList());
    List<Integer> collect2 =
        Stream.of(Arrays.asList(arr1), Arrays.asList(arr2))
            .flatMap(Collection::stream)
            .sorted()
            .collect(Collectors.toList());
    System.out.println(collect2);
  }
}
