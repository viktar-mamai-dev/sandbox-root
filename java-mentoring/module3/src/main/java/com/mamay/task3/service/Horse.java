package com.mamay.task3.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Horse extends IdEntity {

    @EqualsAndHashCode.Include
    private String name;
    private Rider rider;
    private Breed breed;
}
