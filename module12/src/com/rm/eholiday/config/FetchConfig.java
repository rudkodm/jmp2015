package com.rm.eholiday.config;

import java.text.DateFormat;

public class FetchConfig extends PropertiesConfig {

    private String urlPattern;
    private String coordinatesUrlPattern;
    private String websiteUrlPattern;
    private String facebookUrlPattern;
    private String phonesUrlPattern;
    private DateFormat dateFormat;

    private String keyNotFound;
    private String keyDisabled;
    private String keyNoImage;
    private String keyPriceFrom;
    private String keyCategory;
    private String keyAddress;
    private String keyWebsite;
    private String keyEmail;
    private String keySkype;
    private String keyGG;
    private String keyFacebook;
    private String keyPhone;
    private String keyMobilePhone;
    private String keyInfoline;
    private String keyFax;

    private String beginName;
    private String beginInDbSince;
    private String beginPhotoUrl;
    private String beginStars;
    private String beginPriceFrom;
    private String beginCurrency;
    private String beginPriceUnit;
    private String beginStreetAddress;
    private String beginPostalCode;
    private String beginLocality;
    private String beginRegion;
    private String beginCountry;
    private String beginCoorK1;
    private String beginCoorK2;
    private String beginWebsite;
    private String beginWebsiteDirect;
    private String beginSkype;
    private String beginGg;
    private String beginFacebook;
    private String beginHash;

    private String endName;
    private String endInDbSince;
    private String endPhotoUrl;
    private String endStars;
    private String endPriceFrom;
    private String endCurrency;
    private String endPriceUnit;
    private String endStreetAddress;
    private String endPostalCode;
    private String endLocality;
    private String endRegion;
    private String endCountry;
    private String endCoorK1;
    private String endCoorK2;
    private String endWebsite;
    private String endWebsiteDirect;
    private String endSkype;
    private String endGg;
    private String endFacebook;
    private String endHash;

    private String coorDelim;
    private String coorQuote;
    private String coorPrefix;

    FetchConfig() {
        super("fetch.properties");
        initialize(FetchConfig.class);
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public String getCoordinatesUrlPattern() {
        return coordinatesUrlPattern;
    }

    public String getWebsiteUrlPattern() {
        return websiteUrlPattern;
    }

    public String getFacebookUrlPattern() {
        return facebookUrlPattern;
    }

    public String getPhonesUrlPattern() {
        return phonesUrlPattern;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public String getKeyNotFound() {
        return keyNotFound;
    }

    public String getKeyDisabled() {
        return keyDisabled;
    }

    public String getKeyNoImage() {
        return keyNoImage;
    }

    public String getKeyPriceFrom() {
        return keyPriceFrom;
    }

    public String getKeyCategory() {
        return keyCategory;
    }

    public String getKeyAddress() {
        return keyAddress;
    }

    public String getKeyWebsite() {
        return keyWebsite;
    }

    public String getKeyEmail() {
        return keyEmail;
    }

    public String getKeySkype() {
        return keySkype;
    }

    public String getKeyGG() {
        return keyGG;
    }

    public String getKeyFacebook() {
        return keyFacebook;
    }

    public String getKeyPhone() {
        return keyPhone;
    }

    public String getKeyMobilePhone() {
        return keyMobilePhone;
    }

    public String getKeyInfoline() {
        return keyInfoline;
    }

    public String getKeyFax() {
        return keyFax;
    }

    public String getBeginName() {
        return beginName;
    }

    public String getBeginInDbSince() {
        return beginInDbSince;
    }

    public String getBeginPhotoUrl() {
        return beginPhotoUrl;
    }

    public String getBeginStars() {
        return beginStars;
    }

    public String getBeginPriceFrom() {
        return beginPriceFrom;
    }

    public String getBeginCurrency() {
        return beginCurrency;
    }

    public String getBeginPriceUnit() {
        return beginPriceUnit;
    }

    public String getBeginStreetAddress() {
        return beginStreetAddress;
    }

    public String getBeginPostalCode() {
        return beginPostalCode;
    }

    public String getBeginLocality() {
        return beginLocality;
    }

    public String getBeginRegion() {
        return beginRegion;
    }

    public String getBeginCountry() {
        return beginCountry;
    }

    public String getBeginCoorK1() {
        return beginCoorK1;
    }

    public String getBeginCoorK2() {
        return beginCoorK2;
    }

    public String getBeginWebsite() {
        return beginWebsite;
    }

    public String getBeginWebsiteDirect() {
        return beginWebsiteDirect;
    }

    public String getBeginSkype() {
        return beginSkype;
    }

    public String getBeginGg() {
        return beginGg;
    }

    public String getBeginFacebook() {
        return beginFacebook;
    }

    public String getBeginHash() {
        return beginHash;
    }

    public String getEndName() {
        return endName;
    }

    public String getEndInDbSince() {
        return endInDbSince;
    }

    public String getEndPhotoUrl() {
        return endPhotoUrl;
    }

    public String getEndStars() {
        return endStars;
    }

    public String getEndPriceFrom() {
        return endPriceFrom;
    }

    public String getEndCurrency() {
        return endCurrency;
    }

    public String getEndPriceUnit() {
        return endPriceUnit;
    }

    public String getEndStreetAddress() {
        return endStreetAddress;
    }

    public String getEndPostalCode() {
        return endPostalCode;
    }

    public String getEndLocality() {
        return endLocality;
    }

    public String getEndRegion() {
        return endRegion;
    }

    public String getEndCountry() {
        return endCountry;
    }

    public String getEndCoorK1() {
        return endCoorK1;
    }

    public String getEndCoorK2() {
        return endCoorK2;
    }

    public String getEndWebsite() {
        return endWebsite;
    }

    public String getEndWebsiteDirect() {
        return endWebsiteDirect;
    }

    public String getEndSkype() {
        return endSkype;
    }

    public String getEndGg() {
        return endGg;
    }

    public String getEndFacebook() {
        return endFacebook;
    }

    public String getEndHash() {
        return endHash;
    }

    public String getCoorDelim() {
        return coorDelim;
    }

    public String getCoorQuote() {
        return coorQuote;
    }

    public String getCoorPrefix() {
        return coorPrefix;
    }

}
