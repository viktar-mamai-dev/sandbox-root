package com.mamay.inspection.command.reservation;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.Reservation;
import com.mamay.inspection.entity.User;
import com.mamay.inspection.manager.ConfigManager;
import com.mamay.inspection.service.ReservationService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteReservationCommand implements ActionCommand {

  @Override
  public String execute(HttpServletRequest request) {
    String page = ConfigManager.getProperty("page.index");
    int resId = Integer.parseInt(request.getParameter(FormParameter.RES_ID));
    ReservationService.deleteReservation(resId);
    HttpSession session = request.getSession(false);
    if (session != null) {
      User user = (User) (session.getAttribute(FormParameter.USER));
      String roleName = user.getRoleName();
      if ("reader".equals(roleName)) {
        int userId = user.getId();
        List<Reservation> resList = ReservationService.findByUserId(userId);
        request.setAttribute(FormParameter.RES_LIST, resList);
        page = ConfigManager.getProperty("page.res.item");
      } else if ("moderator".equals(roleName)) {
        List<Reservation> resList = ReservationService.findAllreservations();
        request.setAttribute(FormParameter.RES_LIST, resList);
        page = ConfigManager.getProperty("page.res.list");
      }
    }
    return page;
  }
}
