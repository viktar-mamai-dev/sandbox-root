package com.mamay.inspection.command.magazine;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.Period;
import com.mamay.inspection.service.MagazineService;
import com.mamay.inspection.manager.ConfigManager;

public class NewMagazineCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		List<Period> periodList = MagazineService.findAllPeriods();
		request.setAttribute(FormParameter.PERIOD_LIST, periodList);
		return ConfigManager.getProperty("page.magazine.add");
	}

}
