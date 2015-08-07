package com.rm.eholiday.config;

import java.text.DateFormat;

public class FileConfig extends PropertiesConfig {

    private String charset;
    private String workDir;
    private String idCache;
    private String fetchFailed;
    private String storeFailed;
    private String kmlFile;
    private DateFormat dateFormat;

    FileConfig() {
        super("file.properties");
        initialize(FileConfig.class);
    }

    public String getCharset() {
        return charset;
    }

    public String getWorkDir() {
        return workDir;
    }

    public String getIdCache() {
        return idCache;
    }

    public String getFetchFailed() {
        return fetchFailed;
    }

    public String getStoreFailed() {
        return storeFailed;
    }

    public String getKmlFile() {
        return kmlFile;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

}
