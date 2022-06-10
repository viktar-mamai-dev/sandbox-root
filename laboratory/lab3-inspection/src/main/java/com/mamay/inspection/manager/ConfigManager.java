package com.mamay.inspection.manager;

import java.util.ResourceBundle;

public class ConfigManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

	private ConfigManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
