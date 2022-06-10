package com.mamay.task8.entity;

import lombok.ToString;

import java.time.LocalDate;

@ToString(callSuper = true)
public class OptionalCourse extends AbstractCourse {
    private LocalDate date;

    public OptionalCourse(int id, String name) {
        super(id, name);
        this.date = LocalDate.now();
    }
}