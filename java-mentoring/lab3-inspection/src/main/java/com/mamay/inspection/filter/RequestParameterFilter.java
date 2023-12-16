package com.mamay.inspection.filter;

import com.mamay.inspection.constant.FormParameter;
import com.mamay.inspection.service.MagazineService;
import com.mamay.inspection.utility.Checker;
import com.mamay.inspection.utility.PageCalculator;
import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(
    dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE},
    urlPatterns = {"/controller/*"},
    initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp")})
public class RequestParameterFilter implements Filter {

  private String indexPath;

  public void init(FilterConfig fConfig) {
    indexPath = fConfig.getInitParameter("INDEX_PATH");
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    if (checkSequence(httpRequest) && checkMagazineId(httpRequest)) {
      chain.doFilter(request, response);
    } else {
      forward(httpRequest, httpResponse);
    }
  }

  private boolean checkMagazineId(HttpServletRequest httpRequest) {
    String magId = httpRequest.getParameter(FormParameter.MAG_ID);
    if (magId == null || !Checker.isInteger(magId)) return false;
    return MagazineService.findMagazineById(Integer.parseInt(magId)) != null;
  }

  private boolean checkSequence(HttpServletRequest httpRequest) {
    String sequence = httpRequest.getParameter(FormParameter.SEQUENCE);
    if (sequence == null || !Checker.isInteger(sequence)) return false;
    int sequenceNum = Integer.parseInt(sequence);
    int pageCount = PageCalculator.findPageCount();
    return sequenceNum < pageCount && sequenceNum >= 0;
  }

  private void forward(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = httpRequest.getServletContext().getRequestDispatcher(indexPath);
    dispatcher.forward(httpRequest, httpResponse);
  }

  public void destroy() {}
}
