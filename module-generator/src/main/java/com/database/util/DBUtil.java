package com.database.util;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
public class DBUtil {

    public static Connection getConnection(Properties properties) {
        Connection connection = null;
        try {
            Class.forName(properties.getProperty("driver.class"));
            properties.put("useInformationSchema","true");
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        } catch (ClassNotFoundException e) {
            log.error("driver class error", e);
        } catch (SQLException e) {
            log.error("get database connection error", e);
        }
        return connection;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet)  {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            log.error("close error", e);
        }
    }
}
