package com.mamay.inspection.command.subscription;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.Magazine;
import com.mamay.inspection.service.MagazineService;
import com.mamay.inspection.manager.ConfigManager;

public class NewSubscriptionCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		List<Magazine> magList = MagazineService.findAllMagazines();
		request.setAttribute(FormParameter.MAG_LIST, magList);
		return ConfigManager.getProperty("page.sub.add");
	}
}
