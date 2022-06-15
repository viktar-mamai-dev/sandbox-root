package com.mamay.task1.annotationconfig;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@ToString
public class IdEntity {

    @Value(value = "1")
    private Long id;
}
