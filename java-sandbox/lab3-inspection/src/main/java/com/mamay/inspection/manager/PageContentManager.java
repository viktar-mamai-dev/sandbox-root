package com.mamay.inspection.manager;

import java.util.Locale;
import java.util.ResourceBundle;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PageContentManager {
  private final String FILE_NAME = "pagecontent";
  private ResourceBundle resourceBundle;

  public PageContentManager() {
    resourceBundle = ResourceBundle.getBundle(FILE_NAME, Locale.getDefault());
  }

  public void changeResource(Locale locale) {
    resourceBundle = ResourceBundle.getBundle(FILE_NAME, locale);
  }

  public String getProperty(String key) {
    log.debug("Get property: " + resourceBundle.getLocale());
    return resourceBundle.getString(key);
  }
}
