package book2.ch4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
import java.util.stream.*;

public class StreamsTester1 {
    public static void main(String[] args) {
        IntStream is1 = IntStream.empty();

        LongStream ls1 = LongStream.of(1, 2, 3);//.build()
        OptionalLong opt1 = ls1.map(n -> n * 10).filter(n -> n < 5).findFirst();
        if (opt1.isPresent()) System.out.println(opt1.getAsLong());
        opt1.ifPresent(System.out::println);

        Stream<Integer> s1 = Stream.of(1);
        IntStream is2 = s1.mapToInt(x -> x);
        Stream<Integer> s2 = Stream.of(1);
        DoubleStream ds1 = s2.mapToDouble(x -> x);
        IntStream is3 = ds1.mapToInt(x -> (int) x);
        is3.forEach(System.out::print);

        Stream<String> s4 = Stream.empty();
        Stream<String> s5 = Stream.empty();

        Map<Boolean, List<String>> map1 = s4.collect(Collectors.partitioningBy(b -> b.startsWith("c")));
        Map<Boolean, List<String>> map2 = s5.collect(Collectors.groupingBy(b -> b.startsWith("c")));
        System.out.println(map1 + " " + map2);

        DoubleStream is = DoubleStream.of(0, 2, 4); //1
        double sum = is.filter(i -> i % 2 != 0).sum(); //2

        System.out.println(sum); //3

        method1();
        method2();
    }

    public static void method2() {
        Map<String, Map<Double, List<Book>>> classified = null;
        List<Book> books = Arrays.asList(
                new Book("Freedom at Midnight", 5.0),
                new Book("Gone with the wind", 5.0),
                new Book("Midnight Cowboy", 15.0),
                new Book("Freedom at Midnight", 5.0),
                new Book("Gone with the wind", 5.0),
                new Book("Midnight Cowboy", 16.0),
                new Book("Freedom at Midnight", 25.0),
                new Book("Gone with the wind", 5.0),
                new Book("Midnight Cowboy", 17.0)
        );
        classified = books.stream().collect(Collectors.groupingBy(
                Book::getTitle, Collectors.groupingBy(Book::getPrice)

        ));
        cout(classified);

    }

    private static void cout(Map<String, Map<Double, List<Book>>> classified) {
        classified.forEach((key, value) -> {
            System.out.println(key + " : ");
            value.forEach((key2, value2) -> {
                System.out.println("\t" + key2 + " : " + value2);
            });
        });
    }

    public static void method1() {
        List<Book> books = Arrays.asList(
                new Book("Freedom at Midnight", 5.0),
                new Book("Gone with the wind", 5.0),
                new Book("Midnight Cowboy", 15.0)
        );

        books.stream()
                .filter(b -> b.getTitle().startsWith("F"))
                .forEach(b -> b.setPrice(10.0)); // modifies element in list
        books.stream()
                .forEach(b -> System.out.println(b.getTitle() + ":" + b.getPrice())); // modified
    }

    private static class Book {
        @Override
        public String toString() {
            return "Book{" +
                    "title='" + title + '\'' +
                    ", price=" + price +
                    '}';
        }

        String title;
        double price;

        Book(String name, double price) {
            this.title = name;
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
