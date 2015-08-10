package com.rm.eholiday.sql;

import com.rm.eholiday.Log;
import com.rm.eholiday.config.Config;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

class JdbcUtil {

    private static final String DRIVER_PATH = Config.getJdbc().getDriverPath();
    private static final String DRIVER_CLASS = Config.getJdbc().getDriverClass();
    private static final String JDBC_URL = Config.getJdbc().getUrl();
    private static final String USERNAME = Config.getJdbc().getUsername();
    private static final String PASSWORD = Config.getJdbc().getPassword();
    private static final long RECONNECT_TIMEOUT = Config.getJdbc().getReconnectTimeout();
    private static final int VALIDATION_TIMEOUT = Config.getJdbc().getValidationTimeout();

    private static Log log = Log.newLog("JdbcUtil");

    private static class DriverWrapper implements Driver {

        private Driver driver;

        private DriverWrapper(Driver driver) {
            this.driver = driver;
        }

        @Override
        public Connection connect(String url, Properties info) throws SQLException {
            return driver.connect(url, info);
        }

        @Override
        public boolean acceptsURL(String url) throws SQLException {
            return driver.acceptsURL(url);
        }

        @Override
        public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
            return driver.getPropertyInfo(url, info);
        }

        @Override
        public int getMajorVersion() {
            return driver.getMajorVersion();
        }

        @Override
        public int getMinorVersion() {
            return driver.getMinorVersion();
        }

        @Override
        public boolean jdbcCompliant() {
            return driver.jdbcCompliant();
        }

        @Override
        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return null;
        }

    }

    static {
        try {
            URL url = new URL(DRIVER_PATH);
            URLClassLoader ucl = new URLClassLoader(new URL[] { url });
            Driver driver = (Driver)Class.forName(DRIVER_CLASS, true, ucl).newInstance();
            DriverManager.registerDriver(new DriverWrapper(driver));
        } catch (MalformedURLException e) {
            log.error("JDBC driver path URL is malformed: " + DRIVER_PATH, e);
        } catch (ClassNotFoundException e) {
            log.error("Class is not found: " + DRIVER_CLASS, e);
        } catch (InstantiationException e) {
            log.error("Unable to instantiate class: " + DRIVER_CLASS, e);
        } catch (IllegalAccessException e) {
            log.error("Class or its nullary constructor is not accessible: " + DRIVER_CLASS, e);
        } catch (SQLException e) {
            log.error("Unable to register driver: " + DRIVER_CLASS, e);
        }
    }

    public static Connection getConnection() {
        while (true) {
            try {
                return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                log.error("Unable to connect to the database", e);
            }

            try {
                Thread.sleep(RECONNECT_TIMEOUT);
            } catch (InterruptedException e) {
                String msg = "Connecting to the database was interrupted";
                log.error(msg, e);
                throw new IllegalStateException(msg, e);
            }
        }
    }

    public static Connection ensureConnection(Connection connection) {
        try {
            return connection == null || !connection.isValid(VALIDATION_TIMEOUT) ? getConnection() : connection;
        } catch (SQLException e) {
            String msg = "Unable to validate connection. Validation timeout: " + VALIDATION_TIMEOUT;
            log.error(msg, e);
            throw new IllegalStateException(msg, e);
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Unable to close connection", e);
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("Unable to close statement", e);
            }
        }
    }

}
