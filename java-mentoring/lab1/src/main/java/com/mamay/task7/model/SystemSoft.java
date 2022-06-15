package com.mamay.task7.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class SystemSoft extends BasicSoft {
    protected List<String> typeOS;

    public SystemSoft(String name, double price, double size, List<String> typeOS) {
        super(name, price, size);
        this.typeOS = typeOS;
    }

}
