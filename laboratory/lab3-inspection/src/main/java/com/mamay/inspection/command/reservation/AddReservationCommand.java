package com.mamay.inspection.command.reservation;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.Reservation;
import com.mamay.inspection.entity.User;
import com.mamay.inspection.service.ReservationService;
import com.mamay.inspection.manager.ConfigManager;

public class AddReservationCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			int userId = ((User) session.getAttribute(FormParameter.USER)).getId();
			int subId = Integer.parseInt(request.getParameter(FormParameter.SUB_ID));
			int count = Integer.parseInt(request.getParameter(FormParameter.COUNT));
			ReservationService.createReservation(userId, subId, count);
			List<Reservation> resList = ReservationService.findByUserId(userId);
			request.setAttribute(FormParameter.RES_LIST, resList);
			return ConfigManager.getProperty("page.res.item");
		}
		return ConfigManager.getProperty("page.index");
	}

}
