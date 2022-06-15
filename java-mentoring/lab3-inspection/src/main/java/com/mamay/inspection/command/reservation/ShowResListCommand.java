package com.mamay.inspection.command.reservation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.Reservation;
import com.mamay.inspection.service.ReservationService;
import com.mamay.inspection.manager.ConfigManager;

public class ShowResListCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		List<Reservation> resList = ReservationService.findAllreservations();
		request.setAttribute(FormParameter.RES_LIST, resList);
		return ConfigManager.getProperty("page.res.list");
	}

}
