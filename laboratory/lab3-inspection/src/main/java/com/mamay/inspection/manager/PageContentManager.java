package com.mamay.inspection.manager;

import org.apache.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class PageContentManager {
    private final String FILE_NAME = "pagecontent";
    private final Logger LOG = Logger.getLogger(PageContentManager.class);
    private ResourceBundle resourceBundle;

    public PageContentManager() {
        resourceBundle = ResourceBundle.getBundle(FILE_NAME, Locale.getDefault());
    }

    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(FILE_NAME, locale);
    }

    public String getProperty(String key) {
        LOG.debug("Get property: " + resourceBundle.getLocale());
        return resourceBundle.getString(key);
    }

}
