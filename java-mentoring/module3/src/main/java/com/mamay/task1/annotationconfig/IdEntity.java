package com.mamay.task1.annotationconfig;

import org.springframework.beans.factory.annotation.Value;

public class IdEntity {

    @Value(value = "1")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
