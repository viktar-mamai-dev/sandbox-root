package com.mamay.task1.javaconfig;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class Race extends IdEntity {

    private String place;
    private LocalDateTime time;
    private List<Horse> horses;
    private int length;
}
