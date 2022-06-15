package com.mamay.inspection.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User extends Entity {
    private static final long serialVersionUID = 8616481033020433776L;

    private String userName;
    private int age;
    private String login;
    private String password;
    private String roleName = "reader";

    public User(String userName, int age, String login) {
        this.userName = userName;
        this.age = age;
        this.login = login;
    }

    public User(int id, String userName, int age, String login) {
        super(id);
        this.userName = userName;
        this.age = age;
        this.login = login;
    }
}
