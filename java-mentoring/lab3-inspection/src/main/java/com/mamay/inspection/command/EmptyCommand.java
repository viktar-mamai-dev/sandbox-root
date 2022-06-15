package com.mamay.inspection.command;

import javax.servlet.http.HttpServletRequest;

import com.mamay.inspection.manager.ConfigManager;

public class EmptyCommand implements ActionCommand {
	@Override
	public String execute(HttpServletRequest request) {
		return ConfigManager.getProperty("page.index");
	}
}
