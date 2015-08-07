package com.rm.eholiday.kml;

import com.rm.eholiday.FileUtil;
import com.rm.eholiday.Log;
import com.rm.eholiday.beans.Accommodation;
import com.rm.eholiday.config.Config;
import com.rm.eholiday.xml.XmlUtil;

import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class CreateTask implements Runnable {

    private static final long POLLING_TIMEOUT = Config.getThread().getPollingTimeout();
    private static final String ENCODING = Config.getKml().getEncoding();
    private static final String XMLNS = Config.getKml().getXmlns();
    private static final String DOC_NAME = Config.getKml().getDocName();
    private static final String DOC_DESCRIPTION = Config.getKml().getDocDescription();
    private static final char DECIMAL_SEPARATOR = Config.getKml().getDecimalSeparator();
    private static final String DECIMAL_FORMAT = Config.getKml().getDecimalFormat();
    private static final String COORDINATE_SEPARATOR = Config.getKml().getCoordinateSeparator();
    private static final boolean COMPACT = Config.getKml().isCompact();
    private static final String NEW_LINE = Config.getKml().getNewLine();
    private static final String TAB = Config.getKml().getTab();
    private static final String KML_FILE = Config.getFile().getKmlFile();

    private static final Format DOUBLE_FORMAT;
    static {
        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
        formatSymbols.setDecimalSeparator(DECIMAL_SEPARATOR);
        DOUBLE_FORMAT = new DecimalFormat(DECIMAL_FORMAT, formatSymbols);
    }

    private static final String[] NL_TABS = new String[] {
        NEW_LINE,
        NEW_LINE + TAB,
        NEW_LINE + TAB + TAB,
        NEW_LINE + TAB + TAB + TAB,
        NEW_LINE + TAB + TAB + TAB + TAB,
    };

    private static Log log = Log.newLog("Create");

    private BlockingQueue<Accommodation> accommodationQueue;
    private volatile boolean stop = false;

    public CreateTask(BlockingQueue<Accommodation> accommodationQueue) {
        this.accommodationQueue = accommodationQueue;
    }

    private Accommodation next() {
        try {
            return accommodationQueue.poll(POLLING_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            String msg = "Waiting is interrupted";
            log.error(msg, e);
            throw new IllegalStateException(msg, e);
        }
    }

    private String newLineTabs(int tabs) {
        return COMPACT ? "" : NL_TABS[tabs < NL_TABS.length ? tabs : NL_TABS.length - 1];
    }

    @Override
    public void run() {
        File file = FileUtil.createFile(KML_FILE, true);

        BufferedWriter buffer = null;
        try {
            OutputStream output = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(output, ENCODING);
            buffer = new BufferedWriter(writer);

            buffer.append("<?xml version=\"1.0\" encoding=\"").append(ENCODING).append("\"?>").append(NEW_LINE);
            buffer.append("<kml xmlns=\"").append(XMLNS).append("\">");
            buffer.append(newLineTabs(1)).append("<Document>");
            buffer.append(newLineTabs(2)).append("<name>").append(DOC_NAME).append("</name>");
            buffer.append(newLineTabs(2)).append("<description>").append(DOC_DESCRIPTION).append("</description>");

            while (true) {
                boolean doStop = stop;
                Accommodation accommodation = next();
                if (accommodation != null) {
                    String escaped;

                    String name = accommodation.getName();
                    escaped = XmlUtil.escapeXml10(name);
                    name = escaped.equals(name) ? name : "<![CDATA[" + name + "]]>";

                    StringBuilder descBuilder = new StringBuilder();
                    descBuilder.append(accommodation.getCategory().toString().replace('_', ' '));
                    if (accommodation.getPriceFrom() != 0) {
                        descBuilder.append(", ").append(accommodation.getPriceFrom()).append(' ');
                        descBuilder.append(accommodation.getCurrency()).append(" per ");
                        descBuilder.append(accommodation.getPriceUnit());
                    }
                    descBuilder.append(NEW_LINE).append(accommodation.getStreetAddress());
                    descBuilder.append(' ').append(accommodation.getPostalCode());
                    descBuilder.append(' ').append(accommodation.getLocality());
                    descBuilder.append(' ').append(accommodation.getRegion());
                    String description = descBuilder.toString();
                    escaped = XmlUtil.escapeXml10(description);
                    description = escaped.equals(description) ? description : "<![CDATA[" + description + "]]>";

                    StringBuilder phoneNumber = new StringBuilder();
                    for (String phone : accommodation.getAllPhones()) {
                        phoneNumber.append(phoneNumber.length() > 0 ? ", " : "").append(phone);
                    }

                    String longitude = DOUBLE_FORMAT.format(accommodation.getLongitude());
                    String latitude = DOUBLE_FORMAT.format(accommodation.getLatitude());


                    buffer.append(newLineTabs(2)).append("<Placemark>");

                    buffer.append(newLineTabs(3)).append("<name>").append(name).append("</name>");

                    buffer.append(newLineTabs(3)).append("<description>").append(description).append("</description>");

                    if (phoneNumber.length() > 0) {
                        buffer.append(newLineTabs(3)).append("<phoneNumber>").append(phoneNumber).append("</phoneNumber>");
                    }

                    buffer.append(newLineTabs(3)).append("<Point>");
                    buffer.append(newLineTabs(4)).append("<coordinates>");
                    buffer.append(longitude).append(COORDINATE_SEPARATOR);
                    buffer.append(latitude).append("</coordinates>");
                    buffer.append(newLineTabs(3)).append("</Point>");

                    buffer.append(newLineTabs(2)).append("</Placemark>");
                } else if (doStop) {
                    break;
                }
            }

            buffer.append(newLineTabs(1)).append("</Document>");
            buffer.append(newLineTabs(0)).append("</kml>");

            buffer.flush();
        } catch (IOException e) {
            String msg = "Unable to write to file " + file.getAbsolutePath();
            log.error(msg, e);
            throw new IllegalStateException(msg, e);
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    log.error("Unable to close file " + file.getAbsolutePath());
                }
            }
        }
    }

    public void shutdown() {
        stop = true;
    }

}
