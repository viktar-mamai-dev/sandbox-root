package com.mamay.inspection.command.subscription;

import javax.servlet.http.HttpServletRequest;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.command.ActionHelper;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.Magazine;
import com.mamay.inspection.entity.Subscription;
import com.mamay.inspection.service.MagazineService;
import com.mamay.inspection.service.SubscriptionService;
import com.mamay.inspection.manager.ConfigManager;
import com.mamay.inspection.validation.SubscriptionValidation;

public class UpdateSubscriptionCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		int magId = Integer.parseInt(request.getParameter(FormParameter.MAG_ID));
		int subId = Integer.parseInt(request.getParameter(FormParameter.SUB_ID));
		String index = request.getParameter(FormParameter.INDEX);
		String duration = request.getParameter(FormParameter.DURATION);
		String price = request.getParameter(FormParameter.PRICE);
		Subscription sub = new Subscription(subId, index, duration, price);
		Magazine mag = MagazineService.findMagazineById(magId);
		if (!SubscriptionValidation.checkInputParameters(request)) {
			request.setAttribute(FormParameter.SUBSCRIPTION, sub);
			request.setAttribute(FormParameter.MAGAZINE, mag);
			return ConfigManager.getProperty("page.sub.update");
		} else {
			SubscriptionService.updateSubscription(sub);
			request.setAttribute(FormParameter.MAGAZINE, mag);
			String message = ActionHelper.findProperty(request, "success.sub.update");
			request.setAttribute(FormParameter.SUCCESS_MESSAGE, message);
			return ConfigManager.getProperty("page.magazine.item");
		}
	}

}
