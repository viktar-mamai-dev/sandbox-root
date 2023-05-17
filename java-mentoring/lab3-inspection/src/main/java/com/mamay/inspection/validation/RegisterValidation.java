package com.mamay.inspection.validation;

import com.mamay.inspection.command.ActionHelper;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.utility.Checker;
import javax.servlet.http.HttpServletRequest;

public class RegisterValidation {
  private static final int PASS_MIN_LENGTH = 6;
  private static final int PASS_MAX_LENGTH = 16;
  private static final int AGE_MIN = 18;
  private static final int AGE_MAX = 100;

  public static boolean checkInputParameters(HttpServletRequest request) {
    boolean flag = true;
    String userName = request.getParameter(FormParameter.USERNAME);
    String login = request.getParameter(FormParameter.LOGIN);
    String pass = request.getParameter(FormParameter.PASSWORD);
    String passConfirm = request.getParameter(FormParameter.PASS_CONFIRM);
    String age = request.getParameter(FormParameter.AGE);
    String emptyField = ActionHelper.findProperty(request, "error.empty");
    if (checkEmpty(userName)) {
      request.setAttribute(FormParameter.USERNAME_ERROR, emptyField);
      flag = false;
    }
    if (checkEmpty(login)) {
      request.setAttribute(FormParameter.LOGIN_ERROR, emptyField);
      flag = false;
    }
    if (!checkPassword(pass)) {
      String passwordError = ActionHelper.findProperty(request, "error.password.input");
      request.setAttribute(FormParameter.PASSWORD_ERROR, passwordError);
      flag = false;
    } else if (!checkConfirmPassword(pass, passConfirm)) {
      String passwordError = ActionHelper.findProperty(request, "error.password.confirm");
      request.setAttribute(FormParameter.PASS_CONFIRM_ERROR, passwordError);
      flag = false;
    }
    if (!checkAge(age)) {
      String ageError = ActionHelper.findProperty(request, "error.age");
      request.setAttribute(FormParameter.AGE_ERROR, ageError);
      flag = false;
    }
    return flag;
  }

  private static boolean checkPassword(String enterPassword) {
    int length = enterPassword.length();
    return checkEmpty(enterPassword) && length >= PASS_MIN_LENGTH && length <= PASS_MAX_LENGTH;
  }

  private static boolean checkConfirmPassword(String pass, String confirmPass) {
    return checkEmpty(confirmPass) && pass.equals(confirmPass);
  }

  private static boolean checkAge(String age) {
    int realAge = Integer.parseInt(age);
    return Checker.isInteger(age) && realAge >= AGE_MIN || realAge <= AGE_MAX;
  }

  private static boolean checkEmpty(String field) {
    return field == null || "".equals(field.trim());
  }
}
