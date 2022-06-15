package com.mamay.task1.annotationconfig;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@Component(value = "race1")
@Getter
@Setter
@ToString
public class Race extends IdEntity {

    @Value(value = "Central Ippodrom")
    private String place;

    private LocalDateTime time = LocalDateTime.of(2019, Month.DECEMBER, 5, 9, 30);
    @Autowired
    private List<Horse> horses;
    @Value(value = "100")
    private int length;
}
