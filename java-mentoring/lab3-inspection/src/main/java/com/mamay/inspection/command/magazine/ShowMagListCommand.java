package com.mamay.inspection.command.magazine;

import com.mamay.inspection.command.ActionCommand;
import com.mamay.inspection.command.ActionHelper;
import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.manager.ConfigManager;
import com.mamay.inspection.utility.PageCalculator;
import javax.servlet.http.HttpServletRequest;

public class ShowMagListCommand implements ActionCommand {

  @Override
  public String execute(HttpServletRequest request) {
    String sequence = request.getParameter(FormParameter.SEQUENCE);
    int offset = 0;
    if (sequence != null) {
      offset = Integer.parseInt(sequence) * PageCalculator.DEFAULT_PER_PAGE;
      request.setAttribute(FormParameter.SEQUENCE, sequence);
    }
    ActionHelper.prepareMagazineList(request, offset);
    return ConfigManager.getProperty("page.magazine.list");
  }
}
