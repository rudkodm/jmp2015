package com.rm.eholiday.config;

import java.io.InputStream;

public class Config {

    private static volatile FetchConfig fetch = new FetchConfig();
    private static volatile FileConfig file = new FileConfig();
    private static volatile HttpConfig http = new HttpConfig();
    private static volatile JdbcConfig jdbc = new JdbcConfig();
    private static volatile SearchConfig search = new SearchConfig();
    private static volatile SqlConfig sql = new SqlConfig();
    private static volatile KmlConfig kml = new KmlConfig();
    private static volatile ThreadConfig thread = new ThreadConfig();

    private String fileName;

    Config(String fileName) {
        this.fileName = fileName;
    }

    protected InputStream getInputStream() {
        return Config.class.getClassLoader().getResourceAsStream(fileName);
    }

    public static FetchConfig getFetch() {
        return fetch;
    }

    public static FileConfig getFile() {
        return file;
    }

    public static HttpConfig getHttp() {
        return http;
    }

    public static JdbcConfig getJdbc() {
        return jdbc;
    }

    public static SearchConfig getSearch() {
        return search;
    }

    public static SqlConfig getSql() {
        return sql;
    }

    public static KmlConfig getKml() {
        return kml;
    }

    public static ThreadConfig getThread() {
        return thread;
    }

}
