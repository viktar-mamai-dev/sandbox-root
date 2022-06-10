package com.mamay.inspection.command.magazine;

import javax.servlet.http.HttpServletRequest;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.Magazine;
import com.mamay.inspection.service.MagazineService;
import com.mamay.inspection.manager.ConfigManager;

public class ShowMagItemCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(FormParameter.MAG_ID));
		Magazine mag = MagazineService.findMagazineById(id);
		request.setAttribute(FormParameter.MAGAZINE, mag);
		return ConfigManager.getProperty("page.magazine.item");
	}

}
