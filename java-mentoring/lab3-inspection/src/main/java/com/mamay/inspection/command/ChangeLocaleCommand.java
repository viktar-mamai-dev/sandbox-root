package com.mamay.inspection.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.manager.ConfigManager;

public class ChangeLocaleCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String language = request.getParameter(FormParameter.LANGUAGE);
		HttpSession session = request.getSession();
		session.setAttribute(FormParameter.LANGUAGE, language);

		ActionHelper.prepareMagazineList(request, 0);
		return ConfigManager.getProperty("page.magazine.list");
	}
}
