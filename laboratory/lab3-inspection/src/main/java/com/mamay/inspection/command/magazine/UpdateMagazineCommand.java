package com.mamay.inspection.command.magazine;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.command.ActionHelper;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.Magazine;
import com.mamay.inspection.entity.Period;
import com.mamay.inspection.service.MagazineService;
import com.mamay.inspection.manager.ConfigManager;
import com.mamay.inspection.validation.MagazineValidation;

public class UpdateMagazineCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter(FormParameter.MAG_ID));
		String title = request.getParameter(FormParameter.TITLE);
		String annotation = request.getParameter(FormParameter.ANNOTATION);
		int periodId = Integer.parseInt(request.getParameter(FormParameter.PERIOD_ID));
		Magazine mag = new Magazine(id, title, annotation);
		if (!MagazineValidation.checkInputParameters(request)) {
			request.setAttribute(FormParameter.MAGAZINE, mag);
			request.setAttribute(FormParameter.PERIOD_ID, periodId);
			List<Period> periodList = MagazineService.findAllPeriods();
			request.setAttribute(FormParameter.PERIOD_LIST, periodList);
			return ConfigManager.getProperty("page.magazine.update");
		} else {
			MagazineService.updateMagazine(periodId, mag);
			ActionHelper.prepareMagazineList(request, 0);
			String msg = ActionHelper.findProperty(request, "success.magazine.update");
			request.setAttribute(FormParameter.SUCCESS_MESSAGE, msg);
			return ConfigManager.getProperty("page.magazine.list");
		}
	}
}
