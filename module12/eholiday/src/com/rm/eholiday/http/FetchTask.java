package com.rm.eholiday.http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.*;
import java.util.*;
import com.rm.eholiday.*;
import com.rm.eholiday.beans.*;
import com.rm.eholiday.beans.Currency;
import com.rm.eholiday.config.Config;

import java.util.concurrent.BlockingQueue;

public class FetchTask implements Runnable {

    private static final String ENCODING = "ASCII";

    private static final String HEADER_LOCATION = "Location";

    private static final String URL_PATTERN = Config.getFetch().getUrlPattern();
    //private static final String COORDINATES_URL_PATTERN = Config.getFetch().getCoordinatesUrlPattern();
    private static final String WEBSITE_URL_PATTERN = Config.getFetch().getWebsiteUrlPattern();
    private static final String FACEBOOK_URL_PATTERN = Config.getFetch().getFacebookUrlPattern();
    private static final String PHONES_URL_PATTERN = Config.getFetch().getPhonesUrlPattern();
    private static final DateFormat DATE_FORMAT = Config.getFetch().getDateFormat();

    private static final String KEY_NOT_FOUND = Config.getFetch().getKeyNotFound();
    private static final String KEY_DISABLED = Config.getFetch().getKeyDisabled();
    private static final String KEY_NO_IMAGE = Config.getFetch().getKeyNoImage();
    private static final String KEY_PRICE_FROM = Config.getFetch().getKeyPriceFrom();
    private static final String KEY_CATEGORY = Config.getFetch().getKeyCategory();
    private static final String KEY_ADDRESS = Config.getFetch().getKeyAddress();
    private static final String KEY_WEBSITE = Config.getFetch().getKeyWebsite();
    private static final String KEY_EMAIL = Config.getFetch().getKeyEmail();
    private static final String KEY_SKYPE = Config.getFetch().getKeySkype();
    private static final String KEY_GG = Config.getFetch().getKeyGG();
    private static final String KEY_FACEBOOK = Config.getFetch().getKeyFacebook();
    private static final String KEY_PHONE = Config.getFetch().getKeyPhone();
    private static final String KEY_MOBILE_PHONE = Config.getFetch().getKeyMobilePhone();
    private static final String KEY_INFOLINE = Config.getFetch().getKeyInfoline();
    private static final String KEY_FAX = Config.getFetch().getKeyFax();

    private static final String BEGIN_NAME = Config.getFetch().getBeginName();
    private static final String BEGIN_INDBSINCE = Config.getFetch().getBeginInDbSince();
    private static final String BEGIN_PHOTO_URL = Config.getFetch().getBeginPhotoUrl();
    private static final String BEGIN_STARS = Config.getFetch().getBeginStars();
    private static final String BEGIN_PRICE_FROM = Config.getFetch().getBeginPriceFrom();
    private static final String BEGIN_STREET_ADDRESS = Config.getFetch().getBeginStreetAddress();
    private static final String BEGIN_POSTAL_CODE = Config.getFetch().getBeginPostalCode();
    private static final String BEGIN_LOCALITY = Config.getFetch().getBeginLocality();
    private static final String BEGIN_REGION = Config.getFetch().getBeginRegion();
    private static final String BEGIN_COUNTRY = Config.getFetch().getBeginCountry();
    private static final String BEGIN_COOR_K1 = Config.getFetch().getBeginCoorK1();
    private static final String BEGIN_COOR_K2 = Config.getFetch().getBeginCoorK2();
    private static final String BEGIN_WEBSITE = Config.getFetch().getBeginWebsite();
    private static final String BEGIN_WEBSITE_DIRECT = Config.getFetch().getBeginWebsiteDirect();
    private static final String BEGIN_SKYPE = Config.getFetch().getBeginSkype();
    private static final String BEGIN_GG = Config.getFetch().getBeginGg();
    private static final String BEGIN_FACEBOOK = Config.getFetch().getBeginFacebook();
    private static final String BEGIN_HASH = Config.getFetch().getBeginHash();
    
    private static final String END_NAME = Config.getFetch().getEndName();    
    private static final String END_INDBSINCE = Config.getFetch().getEndInDbSince();
    private static final String END_PHOTO_URL = Config.getFetch().getEndPhotoUrl();
    private static final String END_STARS = Config.getFetch().getEndStars();    
    private static final String END_PRICE_FROM = Config.getFetch().getEndPriceFrom();
    private static final String END_CURRENCY = Config.getFetch().getEndCurrency();
    private static final String END_PRICE_UNIT = Config.getFetch().getEndPriceUnit();    
    private static final String END_STREET_ADDRESS = Config.getFetch().getEndStreetAddress();    
    private static final String END_POSTAL_CODE = Config.getFetch().getEndPostalCode();    
    private static final String END_LOCALITY = Config.getFetch().getEndLocality();    
    private static final String END_REGION = Config.getFetch().getEndRegion();    
    private static final String END_COUNTRY = Config.getFetch().getEndCountry();    
    private static final String END_COOR_K1 = Config.getFetch().getEndCoorK1();
    private static final String END_COOR_K2 = Config.getFetch().getEndCoorK2();
    private static final String END_WEBSITE = Config.getFetch().getEndWebsite();    
    private static final String END_WEBSITE_DIRECT = Config.getFetch().getEndWebsiteDirect();
    private static final String END_SKYPE = Config.getFetch().getEndSkype();
    private static final String END_GG = Config.getFetch().getEndGg();
    private static final String END_FACEBOOK = Config.getFetch().getEndFacebook();
    private static final String END_HASH = Config.getFetch().getEndHash();

    private static Log log = Log.newLog("Fetch");

    private static final String COOR_DELIM = Config.getFetch().getCoorDelim();
    private static final String COOR_QUOTE = Config.getFetch().getCoorQuote();
    private static final String COOR_PREFIX = Config.getFetch().getCoorPrefix();
    private static final Map<String, String> COOR_MAP = Config.getFetch().getPropertiesByPrefix("coorMap.");

    private static final Map<String, Category> CATEGORY_MAP = new HashMap<String, Category>(Category.values().length);
    static {
        for (Category category : Category.values()) {
            String key = Config.getFetch().getProperty("category." + category);
            if (key == null || key.trim().isEmpty()) {
                String msg = "Unable to load key for category: " + category;
                log.error(msg);
                throw new IllegalStateException(msg);
            }
            CATEGORY_MAP.put(key, category);
        }
    }

    private static final Map<String, PriceUnit> PRICE_UNIT_MAP = new HashMap<String, PriceUnit>(PriceUnit.values().length);
    static {
        for (PriceUnit unit : PriceUnit.values()) {
            String key = Config.getFetch().getProperty("priceUnit." + unit);
            if (key == null || key.trim().isEmpty()) {
                String msg = "Unable to load key for price unit: " + unit;
                log.error(msg);
                throw new IllegalStateException(msg);
            }
            PRICE_UNIT_MAP.put(key, unit);
        }
    }

    private Queue<String> idQueue;
    private Set<String> fetchFailedIds;
    private BlockingQueue<Accommodation> accommodationQueue;

    private void populateNameAndStars(Accommodation accommodation, String html) {
        String name = HtmlStringUtil.searchFirst(html, BEGIN_NAME, END_NAME, false);
        String stars = HtmlStringUtil.searchFirst(name, BEGIN_STARS, END_STARS, false);
        if (!HtmlStringUtil.nullOrEmpty(stars)) {
            accommodation.setStars(Integer.parseInt(stars));
            name = HtmlStringUtil.searchFirst(name, "", BEGIN_STARS, false);
        }
        accommodation.setName(HtmlStringUtil.finalize(name));
    }

    private void populateInDbSince(Accommodation accommodation, String html) {
        String inDbSince = HtmlStringUtil.searchFirst(html, BEGIN_INDBSINCE, END_INDBSINCE, false);
        try {
            accommodation.setInDbSince(DATE_FORMAT.parse(inDbSince));
        } catch (ParseException e) {
            String msg = "Unable to parse date: " + inDbSince;
            log.error(msg, e);
            throw new IllegalArgumentException(msg, e);
        }
    }

    private void populateCoordinates(Accommodation accommodation, String html) {
        /*String url = MessageFormat.format(COORDINATES_URL_PATTERN, accommodation.getId());
        TextHandler handler = new TextHandler();
        Processor processor = new Processor(url, handler);
        processor.process();
        String html = handler.getText();*/

        List<String> coors = HtmlStringUtil.search(html, BEGIN_COOR_K1, END_COOR_K1, BEGIN_COOR_K2, END_COOR_K2);
        if (coors.size() != 3) {
            String msg = "Unable to parse coordinates";
            log.error(msg);
            throw new IllegalStateException(msg);
        }

        String coorK1 = coors.get(0).replace(COOR_QUOTE, "");
        String coorK2 = coors.get(2).replace(COOR_QUOTE, "");

        List<String> coorTokens = HtmlStringUtil.split(coorK1, COOR_DELIM);
        int tokensLongLat = HtmlStringUtil.nullOrEmpty(coorK2) ? coorTokens.size() / 2 :
                Integer.parseInt(HtmlStringUtil.split(coorK2.substring(COOR_PREFIX.length()), COOR_PREFIX).get(0));

        StringBuilder longitude = new StringBuilder();
        StringBuilder latitude = new StringBuilder();
        for (int i = 0; i < coorTokens.size(); i++) {
            StringBuilder token = new StringBuilder(coorTokens.get(i).replace(COOR_PREFIX, ""));
            for (int j = 0; j < token.length(); j++) {
                token.replace(j, j + 2, COOR_MAP.get(token.substring(j, j + 2)));
            }

            if (i < tokensLongLat) {
                longitude.append(token);
            } else {
                latitude.append(token);
            }
        }

        accommodation.setLongitude(Double.parseDouble(longitude.toString()));
        accommodation.setLatitude(Double.parseDouble(latitude.toString()));
    }

    private void populatePhoto(Accommodation accommodation, String html) {
        String photoUrl = HtmlStringUtil.searchFirst(html, BEGIN_PHOTO_URL, END_PHOTO_URL, false);
        if (HtmlStringUtil.nullOrEmpty(photoUrl)) {
            String msg = "Photo URL is null or empty";
            log.error(msg);
            throw new IllegalStateException(msg);
        } else if (!KEY_NO_IMAGE.equals(photoUrl)) {
            accommodation.setPhotoUrl(HtmlStringUtil.finalize(photoUrl));
        }
    }

    private void populatePriceFrom(Accommodation accommodation, String html) {
        List<String> priceParts = HtmlStringUtil.search(html, BEGIN_PRICE_FROM, END_PRICE_FROM, END_CURRENCY, END_PRICE_UNIT);
        if (priceParts.size() == 0) {
            String msg = "Price and currency are not found: " + html;
            log.warning(msg);
        } else if (priceParts.size() != 3) {
            String msg = "Unable to fetch price and currency from: " + html;
            log.error(msg);
            throw new IllegalStateException(msg);
        } else {
            accommodation.setPriceFrom(Double.parseDouble(priceParts.get(0).trim()));
            accommodation.setCurrency(Currency.valueOf(priceParts.get(1).trim()));
            accommodation.setPriceUnit(PRICE_UNIT_MAP.get(priceParts.get(2).trim()));
        }
    }

    private void populateCategory(Accommodation accommodation, String html) {
        Category category = CATEGORY_MAP.get(html);
        if (category != null) {
            accommodation.setCategory(category);
        } else {
            String msg = "Category cannot be fetched from: " + html;
            log.error(msg);
            throw new IllegalStateException(msg);
        }
    }

    private void populateAddress(Accommodation accommodation, String html) {
        String addressPart;
        addressPart = HtmlStringUtil.searchFirst(html, BEGIN_STREET_ADDRESS, END_STREET_ADDRESS, false);
        accommodation.setStreetAddress(HtmlStringUtil.finalize(addressPart));
        addressPart = HtmlStringUtil.searchFirst(html, BEGIN_POSTAL_CODE, END_POSTAL_CODE, false);
        accommodation.setPostalCode(HtmlStringUtil.finalize(addressPart));
        addressPart = HtmlStringUtil.searchFirst(html, BEGIN_LOCALITY, END_LOCALITY, false);
        accommodation.setLocality(HtmlStringUtil.finalize(addressPart));
        addressPart = HtmlStringUtil.searchFirst(html, BEGIN_REGION, END_REGION, false);
        accommodation.setRegion(HtmlStringUtil.finalize(addressPart));
        addressPart = HtmlStringUtil.searchFirst(html, BEGIN_COUNTRY, END_COUNTRY, false);
        accommodation.setCountry(HtmlStringUtil.finalize(addressPart));
    }

    private void populateWebsites(Accommodation accommodation, String html) {
        int counter = 0;
        List<String> sites;

        sites = HtmlStringUtil.searchAll(html, BEGIN_WEBSITE, END_WEBSITE, false);
        for (String site : sites) {
            try {
                site = URLDecoder.decode(URLDecoder.decode(site, ENCODING), ENCODING);
            } catch (UnsupportedEncodingException e) {
                String msg = "Unsupported encoding: " + ENCODING;
                log.error(msg);
                throw new IllegalStateException(msg);
            }

            site = MessageFormat.format(WEBSITE_URL_PATTERN, site);
            Processor siteProcessor = new Processor(site, 302);
            siteProcessor.process();
            Map<String, List<String>> headers = siteProcessor.getResponseHeaders();
            site = headers.get(HEADER_LOCATION).get(0);

            accommodation.setWebsite(++counter, HtmlStringUtil.finalize(site));
        }

        sites = HtmlStringUtil.searchAll(html, BEGIN_WEBSITE_DIRECT, END_WEBSITE_DIRECT, false);
        for (String site : sites) {
            accommodation.setWebsite(++counter, HtmlStringUtil.finalize(site));
        }

        if (counter == 0) {
            String msg = "Unable to fetch websites from: " + html;
            log.error(msg);
            throw new IllegalStateException(msg);
        }
    }

    private void populateFacebook(Accommodation accommodation, String html) {
        String facebook = HtmlStringUtil.searchFirst(html, BEGIN_FACEBOOK, END_FACEBOOK, false);
        facebook = MessageFormat.format(FACEBOOK_URL_PATTERN, facebook);
        Processor facebookProcessor = new Processor(facebook, 302);
        facebookProcessor.process();
        Map<String, List<String>> headers = facebookProcessor.getResponseHeaders();
        facebook = headers.get(HEADER_LOCATION).get(0);
        accommodation.setFacebook(facebook);
    }

    private void populatePhones(Accommodation accommodation, Map<String, List<String>> phonesMap) {
        String hash = phonesMap.entrySet().iterator().next().getValue().get(0);
        hash = HtmlStringUtil.searchFirst(hash, BEGIN_HASH, END_HASH, false);

        String url = MessageFormat.format(PHONES_URL_PATTERN, accommodation.getId(), hash, String.valueOf(System.currentTimeMillis()));
        TextHandler handler = new TextHandler();
        Processor processor = new Processor(url, handler);
        processor.getRequestHeaders().put("X-EH-PART", "1");
        processor.process();
        String text = handler.getText();

        int indexPhone = 0, indexMobilePhone = 0, indexInfoline = 0, indexFax = 0;
        phonesMap = HtmlStringUtil.searchAndParseDlTags(text, 1).get(0);
        for (Map.Entry<String, List<String>> entry : phonesMap.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();

            for (String phone : value) {
                phone = HtmlStringUtil.finalize(phone);
                if (KEY_PHONE.equals(key)) {
                    accommodation.setPhone(++indexPhone, phone);
                } else if (KEY_MOBILE_PHONE.equals(key)) {
                    accommodation.setMobilePhone(++indexMobilePhone, phone);
                } else if (KEY_INFOLINE.equals(key)) {
                    accommodation.setInfoline(++indexInfoline, phone);
                } else if (KEY_FAX.equals(key)) {
                    accommodation.setFax(++indexFax, phone);
                } else {
                    String msg = "Unknown key: " + key;
                    log.error(msg);
                    throw new IllegalStateException(msg);
                }
            }
        }

        if (indexPhone == 0 && indexMobilePhone == 0 && indexInfoline == 0 && indexFax == 0) {
            String msg = "There were no phones found";
            log.error(msg);
            throw new IllegalStateException(msg);
        }
    }

    private Accommodation fetchById(String id) {
        log.debug("Start fetching. Id: " + id);

        String url = MessageFormat.format(URL_PATTERN, id);
        TextHandler handler = new TextHandler();
        Processor processor = new Processor(url, handler);
        processor.process();
        String html = handler.getText();

        if (html.contains(KEY_NOT_FOUND)) {
            String msg = "Not found: " + id;
            log.error(msg);
            throw new IllegalStateException(msg);
        }

        if (html.contains(KEY_DISABLED)) {
            String msg = "Disabled: " + id;
            log.warning(msg);
            throw new IllegalStateException(msg);
        }

        Accommodation accommodation = new Accommodation(id);

        populateNameAndStars(accommodation, html);
        populateInDbSince(accommodation, html);
        populateCoordinates(accommodation, html);
        //populatePhoto(accommodation, html);

        List<Map<String, List<String>>> parsedDlTags = HtmlStringUtil.searchAndParseDlTags(html, 2);

        if (parsedDlTags == null || parsedDlTags.isEmpty()) {
            String msg = "Details map could not be fetched";
            log.error(msg);
            throw new IllegalStateException(msg);
        }

        Map<String, List<String>> rawDetailsMap = parsedDlTags.get(0);
        Map<String, String> detailsMap = new HashMap<String, String>(rawDetailsMap.size());
        for (Map.Entry<String, List<String>> entry : rawDetailsMap.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            if (value.size() == 1) {
                detailsMap.put(key, value.get(0));
            } else {
                String msg = "Property " + key + " has not one value";
                log.error(msg);
                throw new IllegalStateException(msg);
            }
        }

        for (Map.Entry<String, String> entry : detailsMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (KEY_PRICE_FROM.equals(key)) {
                populatePriceFrom(accommodation, value);
            } else if (KEY_CATEGORY.equals(key)) {
                populateCategory(accommodation, value);
            } else if (KEY_ADDRESS.equals(key)) {
                populateAddress(accommodation, value);
            } else if (KEY_WEBSITE.equals(key)) {
                populateWebsites(accommodation, value);
            } else if (KEY_SKYPE.equals(key)) {
                String skype = HtmlStringUtil.searchFirst(value, BEGIN_SKYPE, END_SKYPE, false);
                accommodation.setSkype(HtmlStringUtil.finalize(skype));
            } else if (KEY_GG.equals(key)) {
                String gg = HtmlStringUtil.searchFirst(value, BEGIN_GG, END_GG, false);
                accommodation.setGg(HtmlStringUtil.finalize(gg));
            } else if (KEY_FACEBOOK.equals(key)) {
                populateFacebook(accommodation, value);
            } else if (!KEY_EMAIL.equals(key)) {
                String msg = "Unknown key: " + key;
                log.error(msg);
                throw new IllegalStateException(msg);
            }
        }

        /*for (int i = 1; i < parsedDlTags.size(); i++) {
            Map<String, List<String>> dl = parsedDlTags.get(i);
            if (dl.containsKey(KEY_PHONE) || dl.containsKey(KEY_MOBILE_PHONE) ||
                    dl.containsKey(KEY_INFOLINE) || dl.containsKey(KEY_FAX)) {
                populatePhones(accommodation, dl);
            }
        }*/

        log.debug("Fetch completed: " + accommodation);
        return accommodation;
    }

    public FetchTask(Queue<String> idQueue, Set<String> fetchFailedIds, BlockingQueue<Accommodation> accommodationQueue) {
        this.idQueue = idQueue;
        this.fetchFailedIds = fetchFailedIds;
        this.accommodationQueue = accommodationQueue;
    }

    @Override
    public void run() {
        String id;
        while ((id = idQueue.poll()) != null) {
            try {
                accommodationQueue.put(fetchById(id));
            } catch (Exception e) {
                log.error("Unable to fetch accommodation: " + id, e);
                fetchFailedIds.add(id);
            }
        }
    }

}
