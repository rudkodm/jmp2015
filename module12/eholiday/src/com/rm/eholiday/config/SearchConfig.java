package com.rm.eholiday.config;

public class SearchConfig extends PropertiesConfig {

    private String urlPattern;
    private char decimalSeparator;
    private String decimalFormat;
    private String requestBody;
    private String beginId;
    private String endId;
    private int itemsPerPage;
    private double metersPerLatitudeDegree;
    private double metersPerLongitudeDegree;
    private double northernmostLatitude;
    private double southernmostLatitude;
    private double easternmostLongitude;
    private double westernmostLongitude;
    private double radius;

    SearchConfig() {
        super("search.properties");
        initialize(SearchConfig.class);
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public char getDecimalSeparator() {
        return decimalSeparator;
    }

    public String getDecimalFormat() {
        return decimalFormat;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public String getBeginId() {
        return beginId;
    }

    public String getEndId() {
        return endId;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public double getMetersPerLatitudeDegree() {
        return metersPerLatitudeDegree;
    }

    public double getMetersPerLongitudeDegree() {
        return metersPerLongitudeDegree;
    }

    public double getNorthernmostLatitude() {
        return northernmostLatitude;
    }

    public double getSouthernmostLatitude() {
        return southernmostLatitude;
    }

    public double getEasternmostLongitude() {
        return easternmostLongitude;
    }

    public double getWesternmostLongitude() {
        return westernmostLongitude;
    }

    public double getRadius() {
        return radius;
    }

}
