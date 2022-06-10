package com.mamay.inspection.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.manager.ConfigManager;

public class LogoutCommand implements ActionCommand {
	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return ConfigManager.getProperty("page.register");
	}
}