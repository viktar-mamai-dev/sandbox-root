package com.mamay.inspection.command.subscription;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.command.ActionHelper;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.Magazine;
import com.mamay.inspection.entity.Subscription;
import com.mamay.inspection.manager.ConfigManager;
import com.mamay.inspection.service.MagazineService;
import com.mamay.inspection.service.SubscriptionService;
import com.mamay.inspection.validation.SubscriptionValidation;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class AddSubscriptionCommand implements ActionCommand {

  @Override
  public String execute(HttpServletRequest request) {
    String page = null;
    int magId = Integer.parseInt(request.getParameter(FormParameter.MAG_ID));
    String index = request.getParameter(FormParameter.INDEX);
    String duration = request.getParameter(FormParameter.DURATION);
    String price = request.getParameter(FormParameter.PRICE);
    Subscription sub = new Subscription(index, duration, price);
    if (!SubscriptionValidation.checkInputParameters(request)) {
      request.setAttribute(FormParameter.SUBSCRIPTION, sub);
      List<Magazine> magList = MagazineService.findAllMagazines();
      request.setAttribute(FormParameter.MAG_LIST, magList);
      page = ConfigManager.getProperty("page.sub.add");
    } else {
      Magazine mag = MagazineService.findMagazineById(magId);
      sub.setMagazine(mag);
      SubscriptionService.createSubscription(sub);
      mag.addSubscription(sub);
      request.setAttribute(FormParameter.MAGAZINE, mag);
      String message = ActionHelper.findProperty(request, "success.sub.add");
      request.setAttribute(FormParameter.SUCCESS_MESSAGE, message);
      page = ConfigManager.getProperty("page.magazine.item");
    }
    return page;
  }
}
