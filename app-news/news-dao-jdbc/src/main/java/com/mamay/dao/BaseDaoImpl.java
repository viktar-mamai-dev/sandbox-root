package com.mamay.dao;

import java.sql.Connection;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class BaseDaoImpl {
  @Autowired protected DataSource dataSource;

  protected Connection getConnection() {
    return DataSourceUtils.getConnection(dataSource);
  }
}
