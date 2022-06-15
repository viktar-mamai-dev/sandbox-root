package com.mamay.task1.annotationconfig;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
public class Horse extends IdEntity {

    @Value(value = "Thunder")
    private String name;
    @Autowired
    private Rider rider;
    @Autowired
    private Breed breed;
}
