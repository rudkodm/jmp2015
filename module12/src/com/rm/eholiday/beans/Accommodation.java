package com.rm.eholiday.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Accommodation {

    private String id;
    private String name;
    private Date inDbSince;
    private Date copied = new Date();
    private Category category;
    private String streetAddress;
    private String postalCode;
    private String locality;
    private String region;
    private String country;
    private double longitude;
    private double latitude;
    private int stars;
    private double priceFrom;
    private PriceUnit priceUnit;
    private Currency currency;
    private String website1;
    private String website2;
    private String skype;
    private String gg;
    private String facebook;
    private String phone1;
    private String phone2;
    private String mobilePhone1;
    private String mobilePhone2;
    private String infoline;
    private String fax;
    private byte[] photo;
    private String photoUrl;

    public Accommodation(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInDbSince() {
        return inDbSince;
    }

    public void setInDbSince(Date inDbSince) {
        this.inDbSince = inDbSince;
    }

    public Date getCopied() {
        return copied;
    }

    public void setCopied(Date copied) {
        this.copied = copied;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public PriceUnit getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(PriceUnit priceUnit) {
        this.priceUnit = priceUnit;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getWebsite1() {
        return website1;
    }

    public void setWebsite1(String website1) {
        this.website1 = website1;
    }

    public String getWebsite2() {
        return website2;
    }

    public void setWebsite2(String website2) {
        this.website2 = website2;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getMobilePhone1() {
        return mobilePhone1;
    }

    public void setMobilePhone1(String mobilePhone1) {
        this.mobilePhone1 = mobilePhone1;
    }

    public String getMobilePhone2() {
        return mobilePhone2;
    }

    public void setMobilePhone2(String mobilePhone2) {
        this.mobilePhone2 = mobilePhone2;
    }

    public String getInfoline() {
        return infoline;
    }

    public void setInfoline(String infoline) {
        this.infoline = infoline;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }

    public void setWebsite(int i, String website) {
        switch (i) {
            case 1: website1 = website; break;
            case 2: website2 = website; break;
            default: throw new IllegalArgumentException("One or two sites are expected. Unexpected number of sites: " + i);
        }
    }

    public void setPhone(int i, String phone) {
        switch (i) {
            case 1: phone1 = phone; break;
            case 2: phone2 = phone; break;
            default: throw new IllegalArgumentException("One or two phones are expected. Unexpected number of phones: " + i);
        }
    }

    public void setMobilePhone(int i, String mobilePhone) {
        switch (i) {
            case 1: mobilePhone1 = mobilePhone; break;
            case 2: mobilePhone2 = mobilePhone; break;
            default: throw new IllegalArgumentException("One or two mobile phones are expected. Unexpected number of mobile phones: " + i);
        }
    }

    public void setInfoline(int i, String infoline) {
        if (i == 1) {
            this.infoline = infoline;
        } else {
            throw new IllegalArgumentException("Only one infoline is expected. Unexpected number of infolines: " + i);
        }
    }

    public void setFax(int i, String fax) {
        if (i == 1) {
            this.fax = fax;
        } else {
            throw new IllegalArgumentException("Only one fax is expected. Unexpected number of faxes: " + i);
        }
    }

    public List<String> getAllPhones() {
        List<String> phones = new ArrayList<String>(4);
        if (phone1 != null) phones.add(phone1);
        if (phone2 != null) phones.add(phone2);
        if (mobilePhone1 != null) phones.add(mobilePhone1);
        if (mobilePhone2 != null) phones.add(mobilePhone2);
        if (infoline != null) phones.add(infoline);
        return phones;
    }

}
