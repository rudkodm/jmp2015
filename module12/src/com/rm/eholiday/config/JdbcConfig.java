package com.rm.eholiday.config;

public class JdbcConfig extends PropertiesConfig {

    private String driverPath;
    private String driverClass;
    private String url;
    private String username;
    private String password;
    private long reconnectTimeout;
    private int validationTimeout;

    JdbcConfig() {
        super("jdbc.properties");
        initialize(JdbcConfig.class);
    }

    public String getDriverPath() {
        return driverPath;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getReconnectTimeout() {
        return reconnectTimeout;
    }

    public int getValidationTimeout() {
        return validationTimeout;
    }

}
