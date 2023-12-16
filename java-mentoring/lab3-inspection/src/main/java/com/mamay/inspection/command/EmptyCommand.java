package com.mamay.inspection.command;

import com.mamay.inspection.manager.ConfigManager;
import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
  @Override
  public String execute(HttpServletRequest request) {
    return ConfigManager.getProperty("page.index");
  }
}
