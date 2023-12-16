package com.mamay.inspection.validation;

import com.mamay.inspection.command.ActionHelper;
import com.mamay.inspection.constant.FormParameter;
import javax.servlet.http.HttpServletRequest;

public class MagazineValidation {

  public static boolean checkInputParameters(HttpServletRequest request) {
    boolean flag = true;
    String title = request.getParameter(FormParameter.TITLE);
    String annotation = request.getParameter(FormParameter.ANNOTATION);
    String periodicity = request.getParameter(FormParameter.PERIOD_ID);
    String emptyField = ActionHelper.findProperty(request, "error.empty");
    if (checkEmpty(title)) {
      request.setAttribute(FormParameter.TITLE_ERROR, emptyField);
      flag = false;
    }
    if (checkEmpty(annotation)) {
      request.setAttribute(FormParameter.ANNOTATION_ERROR, emptyField);
      flag = false;
    }
    if (checkEmpty(periodicity)) {
      request.setAttribute(FormParameter.PERIODICITY_ERROR, emptyField);
      flag = false;
    }
    return flag;
  }

  private static boolean checkEmpty(String field) {
    return field == null || "".equals(field.trim());
  }
}
