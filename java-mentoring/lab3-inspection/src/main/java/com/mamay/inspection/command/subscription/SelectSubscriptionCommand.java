package com.mamay.inspection.command.subscription;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.Magazine;
import com.mamay.inspection.entity.Subscription;
import com.mamay.inspection.manager.ConfigManager;
import com.mamay.inspection.service.SubscriptionService;
import javax.servlet.http.HttpServletRequest;

public class SelectSubscriptionCommand implements ActionCommand {

  @Override
  public String execute(HttpServletRequest request) {
    int subId = Integer.parseInt(request.getParameter(FormParameter.SUB_ID));
    Subscription sub = SubscriptionService.findSubscriptionById(subId);
    request.setAttribute(FormParameter.SUBSCRIPTION, sub);
    Magazine mag = sub.getMagazine();
    request.setAttribute(FormParameter.MAGAZINE, mag);
    return ConfigManager.getProperty("page.sub.update");
  }
}
