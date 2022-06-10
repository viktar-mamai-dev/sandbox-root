package com.mamay.inspection.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class Entity implements Serializable {
    private static final long serialVersionUID = -4728266561127280939L;

    private int id;
}