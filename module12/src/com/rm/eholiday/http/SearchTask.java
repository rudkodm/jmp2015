package com.rm.eholiday.http;

import java.net.*;
import java.text.*;
import java.util.*;
import com.rm.eholiday.*;
import com.rm.eholiday.config.Config;

public class SearchTask implements Runnable {

    private static final String URL_PATTERN = Config.getSearch().getUrlPattern();
    private static final String REQUEST_BODY = Config.getSearch().getRequestBody();
    private static final String BEGIN_ID = Config.getSearch().getBeginId();
    private static final String END_ID = Config.getSearch().getEndId();
    private static final int ITEMS_PER_PAGE = Config.getSearch().getItemsPerPage();
    private static final char DECIMAL_SEPARATOR = Config.getSearch().getDecimalSeparator();
    private static final String DECIMAL_FORMAT = Config.getSearch().getDecimalFormat();

    private static final Format DOUBLE_FORMAT;
    static {
        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
        formatSymbols.setDecimalSeparator(DECIMAL_SEPARATOR);
        DOUBLE_FORMAT = new DecimalFormat(DECIMAL_FORMAT, formatSymbols);
    }

    private static Log log = Log.newLog("Search");

    private double longitude;
    private double latitude;
    private double radius;
    private Set<String> idSharedSet;

    public SearchTask(double longitude, double latitude, double radius, Set<String> idSharedSet) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
        this.idSharedSet = idSharedSet;
    }

    @Override
    public void run() {
        String longitudeStr = DOUBLE_FORMAT.format(longitude);
        String latitudeStr = DOUBLE_FORMAT.format(latitude);
        String radiusStr = DOUBLE_FORMAT.format(radius);

        String debugStr = null;
        if (log.isDebug()) {
            debugStr = "Longitude: {0}, Latitude: {1}, Radius: {2}";
            debugStr = MessageFormat.format(debugStr, longitudeStr, latitudeStr, radiusStr);
            log.debug("Start searching. " + debugStr);
        }

        TextHandler handler = new TextHandler();
        Processor processor = new Processor(handler);

        processor.setRequestMethod("POST");
        processor.getRequestHeaders().put("X-EH-PART", "1");
        processor.getRequestHeaders().put("Accept", "application/json, text/javascript, */*; q=0.01");
        processor.setRequestBody(REQUEST_BODY);

        Set<String> prevPageSet = new HashSet<String>();
        for (int page = 1; true; page++) {
            if (log.isDebug()) {
                String msg = "Start searching on page #{0}. {1}";
                msg = MessageFormat.format(msg, page, debugStr);
                log.debug(msg);
            }

            String url = MessageFormat.format(URL_PATTERN, longitudeStr, latitudeStr, radiusStr, page);
            processor.setUrl(url);

            processor.process();
            String text = handler.getText();
            List<String> searchList = HtmlStringUtil.searchAll(text, BEGIN_ID, END_ID, false);
            Set<String> pageSet = new HashSet<String>(HtmlStringUtil.finalize(searchList));

            if (log.isInfo()) {
                log.info(new StringBuilder().append("Search URL: ").append(url).append(" Found: ").append(pageSet.size()));
            }

            if (pageSet.isEmpty() || pageSet.containsAll(prevPageSet) && pageSet.size() == prevPageSet.size()) {
                break;
            }

            idSharedSet.addAll(pageSet);
            if (pageSet.size() < ITEMS_PER_PAGE) {
                break;
            }

            prevPageSet.clear();
            prevPageSet.addAll(pageSet);

            if (log.isDebug()) {
                String msg = "Searching on page #{0} completed: {1} found. {2}";
                msg = MessageFormat.format(msg, page, pageSet.size(), debugStr);
                log.debug(msg);
            }
        }

        log.debug("Search completed. " + debugStr);
    }

}
