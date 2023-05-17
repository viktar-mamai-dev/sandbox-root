package com.mamay.inspection.command;

import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.entity.Magazine;
import com.mamay.inspection.manager.PageContentManager;
import com.mamay.inspection.service.MagazineService;
import com.mamay.inspection.utility.PageCalculator;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionHelper {

  public static void prepareMagazineList(HttpServletRequest request, int offset) {
    List<Magazine> magList = MagazineService.findAllMagazines(offset);
    request.setAttribute(FormParameter.MAG_LIST, magList);
    int pageCount = PageCalculator.findPageCount();
    request.setAttribute(FormParameter.PAGE_COUNT, pageCount);
    request.setAttribute(FormParameter.PER_PAGE, PageCalculator.DEFAULT_PER_PAGE);
  }

  public static String findProperty(HttpServletRequest request, String key) {
    PageContentManager manager = new PageContentManager();
    HttpSession session = request.getSession();
    String language = (String) session.getAttribute(FormParameter.LANGUAGE);
    if (language != null) {
      manager.changeResource(new Locale(language));
    }
    return manager.getProperty(key);
  }
}
