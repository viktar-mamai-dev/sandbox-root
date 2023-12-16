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

public class ShowUserItemCommand implements ActionCommand {

  @Override
  public String execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    if (session.getAttribute(FormParameter.USER) != null) {
      User user = (User) session.getAttribute(FormParameter.USER);
      int id = user.getId();
      List<Reservation> resList = ReservationService.findByUserId(id);
      request.setAttribute(FormParameter.RES_LIST, resList);
      return ConfigManager.getProperty("page.res.item");
    }
    return ConfigManager.getProperty("page.index");
  }
}
