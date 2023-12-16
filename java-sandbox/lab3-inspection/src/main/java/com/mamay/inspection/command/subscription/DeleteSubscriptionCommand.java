package com.mamay.inspection.command.subscription;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.command.ActionHelper;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.Magazine;
import com.mamay.inspection.manager.ConfigManager;
import com.mamay.inspection.service.MagazineService;
import com.mamay.inspection.service.SubscriptionService;
import javax.servlet.http.HttpServletRequest;

public class DeleteSubscriptionCommand implements ActionCommand {

  @Override
  public String execute(HttpServletRequest request) {
    String page = null;
    int magId = Integer.parseInt(request.getParameter(FormParameter.MAG_ID));
    int subId = Integer.parseInt(request.getParameter(FormParameter.SUB_ID));
    SubscriptionService.deleteSubscription(subId);
    Magazine mag = MagazineService.findMagazineById(magId);
    request.setAttribute(FormParameter.MAGAZINE, mag);
    String message = ActionHelper.findProperty(request, "success.sub.delete");
    request.setAttribute(FormParameter.SUCCESS_MESSAGE, message);
    page = ConfigManager.getProperty("page.magazine.item");
    return page;
  }
}
