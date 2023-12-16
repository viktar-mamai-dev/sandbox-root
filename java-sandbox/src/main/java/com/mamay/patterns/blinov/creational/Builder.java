/*
 * Copyright (c) 2023
 */
package com.mamay.patterns.blinov.creational;

import static java.lang.System.out;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class User {
  private String login;
  private String password;
}

abstract class BaseBuilder {
  protected User user = new User();

  public User getUser() {
    return user;
  }

  public abstract void buildLogin();

  public abstract void buildPassword();
}

class DOMBuilder extends BaseBuilder {
  public void buildLogin() {
    user.setLogin("DOM");
  }

  public void buildPassword() {
    user.setPassword("Dom pass");
  }
}

class SAXBuilder extends BaseBuilder {
  public void buildLogin() {
    user.setLogin("SAX");
  }

  public void buildPassword() {
    user.setPassword("Sax pass");
  }
}

class StAXBuilder extends BaseBuilder {
  public void buildLogin() {
    user.setLogin("StAX");
  }

  public void buildPassword() {
    user.setPassword("Stax pass");
  }
}

class Director {
  public static User createUser(BaseBuilder builder) {
    builder.buildLogin();
    builder.buildPassword();
    return builder.getUser();
  }
}

class BuilderRunner {
  public static void main(String args[]) {
    User e1 = Director.createUser(new DOMBuilder());
    User e2 = Director.createUser(new SAXBuilder());
    out.println(e1 + "\n" + e2);
  }
}
