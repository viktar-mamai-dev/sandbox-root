package com.mamay.task3.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class AnimalService<T> {

    private List<T> animals;
}
