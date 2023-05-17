package com.mamay.inspection.command.user;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.command.ActionHelper;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.User;
import com.mamay.inspection.manager.ConfigManager;
import com.mamay.inspection.service.UserService;
import com.mamay.inspection.utility.Encrypter;
import com.mamay.inspection.validation.RegisterValidation;
import java.text.MessageFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements ActionCommand {

  @Override
  public String execute(HttpServletRequest request) {
    String page = null;
    String userName = request.getParameter(FormParameter.USERNAME);
    String login = request.getParameter(FormParameter.LOGIN);
    String pass = request.getParameter(FormParameter.PASSWORD);
    int age = Integer.parseInt(request.getParameter(FormParameter.AGE));
    User user = new User(userName, age, login);
    if (!RegisterValidation.checkInputParameters(request)) {
      request.setAttribute(FormParameter.USER, user);
      page = ConfigManager.getProperty("page.register");
    } else if (UserService.checkLogin(login) != null) {
      String loginError = ActionHelper.findProperty(request, "error.loginexist");
      request.setAttribute("loginError", loginError);
      request.setAttribute(FormParameter.USER, user);
      page = ConfigManager.getProperty("page.register");
    } else {
      String encryptPassword = Encrypter.crypt(pass);
      user.setPassword(encryptPassword);
      UserService.createUser(user);

      HttpSession session = request.getSession();
      session.setAttribute(FormParameter.USER, user);
      session.setAttribute(FormParameter.ROLE, user.getRoleName());
      String propertyMessage = ActionHelper.findProperty(request, "success.register");
      String successRegister = MessageFormat.format(propertyMessage, userName);
      request.setAttribute(FormParameter.SUCCESS_MESSAGE, successRegister);

      ActionHelper.prepareMagazineList(request, 0);
      page = ConfigManager.getProperty("page.start");
    }
    return page;
  }
}
