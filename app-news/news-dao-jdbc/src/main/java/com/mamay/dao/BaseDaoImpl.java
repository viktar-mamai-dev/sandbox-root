package com.mamay.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;

public class BaseDaoImpl {
    @Autowired
    protected DataSource dataSource;

    protected Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
}
