package com.mamay.inspection.listener;

import com.mamay.inspection.dbhelper.ConnectionPool;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.log4j.Log4j2;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

@WebListener
@Log4j2
public class SimpleContextListener implements ServletContextListener {

  @Override
  public void contextDestroyed(ServletContextEvent e) {
    ConnectionPool.getInstance().closePool();
  }

  @Override
  public void contextInitialized(ServletContextEvent e) {
    ServletContext ctx = e.getServletContext();
    String prefix = ctx.getRealPath("/");
    String filename = ctx.getInitParameter("init_log4j");
    if (filename != null) {
      File logFile = new File(prefix + filename);
      if (logFile.exists()) {
        PropertyConfigurator.configure(prefix + filename);
      } else {
        BasicConfigurator.configure();
      }
    }

    try {
      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    } catch (SQLException exc) {
      ctx.log("Can't register mysql driver!", exc);
    }
  }
}
