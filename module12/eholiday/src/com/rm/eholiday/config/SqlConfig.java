package com.rm.eholiday.config;

public class SqlConfig extends PropertiesConfig {

    private String insertRow;
    private String selectIds;
    private String selectRows;

    public SqlConfig() {
        super("sql.properties");
        initialize(SqlConfig.class);
    }

    public String getInsertRow() {
        return insertRow;
    }

    public String getSelectIds() {
        return selectIds;
    }

    public String getSelectRows() {
        return selectRows;
    }

}
