package com.mamay.inspection.command.user;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.command.ActionHelper;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.User;
import com.mamay.inspection.service.UserService;
import com.mamay.inspection.manager.ConfigManager;
import com.mamay.inspection.utility.Encrypter;

public class LoginCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		String login = request.getParameter(FormParameter.LOGIN);
		String password = request.getParameter(FormParameter.PASSWORD);
		String pass = Encrypter.crypt(password);
		User user = UserService.checkLoginPassword(login, pass);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute(FormParameter.USER, user);
			session.setAttribute(FormParameter.ROLE, user.getRoleName());

			ActionHelper.prepareMagazineList(request, 0);
			String propertyMessage = ActionHelper.findProperty(request, "success.login");
			String userName = user.getUserName();
			String successLogin = MessageFormat.format(propertyMessage, userName);
			request.setAttribute(FormParameter.SUCCESS_MESSAGE, successLogin);
			page = ConfigManager.getProperty("page.start");

		} else {
			String loginError = ActionHelper.findProperty(request, "error.loginerror");
			request.setAttribute("loginError", loginError);
			request.setAttribute(FormParameter.LOGIN, login);
			request.setAttribute(FormParameter.PASSWORD, pass);
			page = ConfigManager.getProperty("page.login");
		}
		return page;
	}
}