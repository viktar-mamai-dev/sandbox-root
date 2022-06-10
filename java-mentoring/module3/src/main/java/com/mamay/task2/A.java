package com.mamay.task2;

import java.util.Set;

public class A {

    private String title;
    private Set<Integer> numbers;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(Set<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "A [title=" + title + ", numbers=" + numbers + "]";
    }

}
