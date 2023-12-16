package com.mamay.inspection.manager;

import java.util.ResourceBundle;

public class DatabaseManager {
  private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

  private DatabaseManager() {}

  public static String getProperty(String key) {
    return resourceBundle.getString(key);
  }
}
