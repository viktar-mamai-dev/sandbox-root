package com.mamay.inspection.command.reservation;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.Reservation;
import com.mamay.inspection.manager.ConfigManager;
import com.mamay.inspection.service.ReservationService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class UpdateReservationCommand implements ActionCommand {

  @Override
  public String execute(HttpServletRequest request) {
    int resId = Integer.parseInt(request.getParameter(FormParameter.RES_ID));
    int statusId = Integer.parseInt(request.getParameter(FormParameter.STATUS_ID));
    ReservationService.changeStatus(resId, statusId);
    List<Reservation> resList = ReservationService.findAllreservations();
    request.setAttribute(FormParameter.RES_LIST, resList);
    return ConfigManager.getProperty("page.res.list");
  }
}
