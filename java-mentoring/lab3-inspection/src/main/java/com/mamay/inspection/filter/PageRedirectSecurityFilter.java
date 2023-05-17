package com.mamay.inspection.filter;

import java.io.IOException;
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
    urlPatterns = {"/jsp/*"},
    initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp")})
public class PageRedirectSecurityFilter implements Filter {
  private String indexPath;

  public void init(FilterConfig fConfig) {
    indexPath = fConfig.getInitParameter("INDEX_PATH");
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(indexPath);
    dispatcher.forward(httpRequest, httpResponse);
    // chain.doFilter(request, response);
  }

  public void destroy() {}
}
