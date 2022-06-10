package com.mamay.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
public class DatabaseUtil {

    /**
     * <p>
     * method that helps our dao layer closing connection and statement
     *
     * @param connection - opened connection
     * @param statement  - opened statement
     */
    public static void close(DataSource dataSource, Connection connection, Statement statement) {

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            log.error(e);
        }

        if (connection != null) {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    /**
     * <p>
     * method that helps our dao layer closing connection and statement
     *
     * @param connection - opened connection
     * @param statement  - opened statement
     * @param rs         - opened resultset
     */
    public static void close(DataSource dataSource, Connection connection, Statement statement, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            log.error(e);
        }
        close(dataSource, connection, statement);
    }

}
