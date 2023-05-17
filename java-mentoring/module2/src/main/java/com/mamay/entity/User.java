/*
 * Copyright (c) 2023
 */
package com.mamay.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

  private Long id;
  private String firstName;
  private String lastName;
  private String login;
  private String email;

  private Image image;

  public User(String firstName, String lastName, String login, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.login = login;
    this.email = email;
  }
}
