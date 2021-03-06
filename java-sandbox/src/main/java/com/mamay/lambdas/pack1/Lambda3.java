package com.mamay.lambdas.pack1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Lambda3 {

    public static void main(String[] args) throws Exception {

        // Predicates
        Predicate<String> predicate = (s) -> s.length() > 0;

        System.out.println(predicate.test("foo"));              // true
        System.out.println(predicate.negate().test("foo"));     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        System.out.println(nonNull.or(isNull).test(true));

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
        System.out.println(isNotEmpty.test(""));

        // Functions
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        System.out.println(backToString.apply("123"));     // "123"

        // Suppliers
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person

        // Consumers
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.getFirstName());
        greeter.accept(new Person("Luke", "Skywalker"));

        // Comparators
        Comparator<Person> comparator = Comparator.comparing(Person::getFirstName);
        Comparator<Person> comparator1 = Comparator.comparing(Person::getLastName);

        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Bonderland");

        System.out.println(comparator.compare(p1, p2));             // > 0
        System.out.println(comparator1.reversed().compare(p1, p2));  // < 0

        // Runnables
        Runnable runnable = () -> System.out.println(UUID.randomUUID());
        runnable.run();

        // Callables
        Callable<UUID> callable = UUID::randomUUID;
        UUID uuid = callable.call();

        // constructor reference
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person);
    }

    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    @Getter
    @ToString
    @NoArgsConstructor
    private static class Person {
        private String firstName;
        private String lastName;

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}
