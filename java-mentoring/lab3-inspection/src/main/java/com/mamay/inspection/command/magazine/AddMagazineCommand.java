package com.mamay.inspection.command.magazine;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.command.ActionHelper;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.Magazine;
import com.mamay.inspection.entity.Period;
import com.mamay.inspection.manager.ConfigManager;
import com.mamay.inspection.service.MagazineService;
import com.mamay.inspection.validation.MagazineValidation;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class AddMagazineCommand implements ActionCommand {

  @Override
  public String execute(HttpServletRequest request) {
    String title = request.getParameter(FormParameter.TITLE);
    String annotation = request.getParameter(FormParameter.ANNOTATION);
    int periodId = Integer.parseInt(request.getParameter(FormParameter.PERIOD_ID));
    String location = request.getParameter(FormParameter.LOCATION);
    Magazine mag = new Magazine(title, annotation);
    mag.setLocation(location);
    if (!MagazineValidation.checkInputParameters(request)) {
      request.setAttribute(FormParameter.MAGAZINE, mag);
      request.setAttribute(FormParameter.PERIOD_ID, periodId);
      List<Period> periodList = MagazineService.findAllPeriods();
      request.setAttribute(FormParameter.PERIOD_LIST, periodList);
      return ConfigManager.getProperty("page.magazine.add");
    } else {
      MagazineService.createMagazine(periodId, mag);
      ActionHelper.prepareMagazineList(request, 0);
      String msg = ActionHelper.findProperty(request, "success.magazine.add");
      request.setAttribute(FormParameter.SUCCESS_MESSAGE, msg);
      return ConfigManager.getProperty("page.magazine.list");
    }
  }
}
