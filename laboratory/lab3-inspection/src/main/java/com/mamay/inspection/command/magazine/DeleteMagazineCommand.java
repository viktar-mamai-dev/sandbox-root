package com.mamay.inspection.command.magazine;

import javax.servlet.http.HttpServletRequest;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.command.ActionHelper;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.service.MagazineService;
import com.mamay.inspection.manager.ConfigManager;

public class DeleteMagazineCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(FormParameter.MAG_ID));
		MagazineService.deleteMagazine(id);
		ActionHelper.prepareMagazineList(request, 0);
		String msg = ActionHelper.findProperty(request, "success.magazine.delete");
		request.setAttribute(FormParameter.SUCCESS_MESSAGE, msg);

		return ConfigManager.getProperty("page.magazine.list");
	}

}
