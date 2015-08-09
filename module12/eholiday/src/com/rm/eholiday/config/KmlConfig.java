package com.rm.eholiday.config;

public class KmlConfig extends PropertiesConfig {

    private String encoding;
    private String xmlns;
    private String docName;
    private String docDescription;
    private char decimalSeparator;
    private String decimalFormat;
    private String coordinateSeparator;
    private boolean compact;
    private String newLine;
    private String tab;

    public KmlConfig() {
        super("kml.properties");
        initialize(KmlConfig.class);
    }

    public String getEncoding() {
        return encoding;
    }

    public String getXmlns() {
        return xmlns;
    }

    public String getDocName() {
        return docName;
    }

    public String getDocDescription() {
        return docDescription;
    }

    public char getDecimalSeparator() {
        return decimalSeparator;
    }

    public String getDecimalFormat() {
        return decimalFormat;
    }

    public String getCoordinateSeparator() {
        return coordinateSeparator;
    }

    public boolean isCompact() {
        return compact;
    }

    public String getNewLine() {
        return newLine;
    }

    public String getTab() {
        return tab;
    }

}
