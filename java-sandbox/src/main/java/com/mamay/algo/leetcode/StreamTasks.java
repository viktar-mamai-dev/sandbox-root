package main.java.com.mamay.algo.leetcode;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamTasks {
  public static void main(String[] args) {
    IntStream.concat(IntStream.range(2, 6), IntStream.rangeClosed(-1, 2))
        .forEach(System.out::println);
    // 2, 3, 4, 5, -1, 0, 1, 2

    IntStream.range(5, 30).limit(12).skip(3).limit(6).skip(2).forEach(System.out::println);
    // 10-13
    IntStream.range(0, 10).skip(2).dropWhile(x -> x < 5).limit(3).forEach(System.out::println);
    // 5, 6, 7

    IntStream.range(0, 10).skip(3).takeWhile(x -> x < 5).limit(3).forEach(System.out::println);
    // 3, 4

    IntStream.range(1, 5)
        .flatMap(i -> IntStream.generate(() -> i).limit(i))
        .forEach(System.out::println);
    // 1, 2, 2, 3, 3, 3, 4, 4, 4, 4

    int x = IntStream.range(-2, 2).map(i -> i * 5).reduce(10, Integer::sum);
    System.out.println(x);
    // x: 0

    IntStream.range(0, 10)
        .boxed()
        .collect(Collectors.partitioningBy(i -> i % 2 == 0))
        .entrySet()
        .forEach(System.out::println);
    // false=[1, 3, 5, 7, 9]
    // true=[0, 2, 4, 6, 8]

    IntStream.range(-5, 0).flatMap(i -> IntStream.of(i, -i)).sorted().forEach(System.out::println);
    // -5, -4, -3, -2, -1, 1, 2, 3, 4, 5

    IntStream.range(-5, 0)
        .flatMap(i -> IntStream.of(i, -i))
        .boxed()
        .sorted(Comparator.comparing(Math::abs))
        .forEach(System.out::println);
    // -1, 1, -2, 2, -3, 3, -4, 4, -5, 5

    IntStream.range(1, 5)
        .flatMap(i -> IntStream.generate(() -> i).limit(i))
        .boxed()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet()
        .forEach(System.out::println);
    // 1=1
    // 2=2
    // 3=3
    // 4=4

  }
}
