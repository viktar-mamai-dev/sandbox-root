package com.mamay.inspection.command;

import javax.servlet.http.HttpServletRequest;

public class RedirectionCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		return request.getParameter("page");
	}
}
