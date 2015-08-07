package com.rm.eholiday.sql;

import java.sql.*;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import com.rm.eholiday.Log;
import com.rm.eholiday.beans.*;
import com.rm.eholiday.config.Config;

public class ReadTask implements Runnable {

    private static final String SELECT_ROWS = Config.getSql().getSelectRows();

    private static Log log = Log.newLog("Read");

    private BlockingQueue<Accommodation> accommodationQueue;

    public ReadTask(BlockingQueue<Accommodation> accommodationQueue) {
        this.accommodationQueue = accommodationQueue;
    }

    private Accommodation readParams(ResultSet rs) throws SQLException {
        int i = 0;

        Accommodation accommodation = new Accommodation(rs.getString(++i));
        accommodation.setName(rs.getNString(++i));
        accommodation.setInDbSince(new Date(rs.getDate(++i).getTime()));
        accommodation.setCopied(new Date(rs.getDate(++i).getTime()));
        accommodation.setCategory(Category.categoryById(rs.getInt(++i)));
        accommodation.setStreetAddress(rs.getNString(++i));
        accommodation.setPostalCode(rs.getString(++i));
        accommodation.setLocality(rs.getNString(++i));
        accommodation.setRegion(rs.getNString(++i));
        accommodation.setCountry(rs.getNString(++i));
        accommodation.setLongitude(rs.getDouble(++i));
        accommodation.setLatitude(rs.getDouble(++i));
        accommodation.setStars(rs.getInt(++i));

        double priceFrom = rs.getDouble(++i);
        if (!rs.wasNull()) {
            accommodation.setPriceFrom(priceFrom);
        }

        int priceUnit = rs.getInt(++i);
        if (!rs.wasNull()) {
            accommodation.setPriceUnit(PriceUnit.priceUnitById(priceUnit));
        }

        String currency = rs.getString(++i);
        if (!rs.wasNull()) {
            accommodation.setCurrency(Currency.valueOf(currency));
        }

        String website1 = rs.getString(++i);
        if (!rs.wasNull()) {
            accommodation.setWebsite1(website1);
        }

        String website2 = rs.getString(++i);
        if (!rs.wasNull()) {
            accommodation.setWebsite2(website2);
        }

        String skype = rs.getString(++i);
        if (!rs.wasNull()) {
            accommodation.setSkype(skype);
        }

        String gg = rs.getString(++i);
        if (!rs.wasNull()) {
            accommodation.setGg(gg);
        }

        String facebook = rs.getString(++i);
        if (!rs.wasNull()) {
            accommodation.setFacebook(facebook);
        }

        String phone1 = rs.getString(++i);
        if (!rs.wasNull()) {
            accommodation.setPhone1(phone1);
        }

        String phone2 = rs.getString(++i);
        if (!rs.wasNull()) {
            accommodation.setPhone2(phone2);
        }

        String mobPhone1 = rs.getString(++i);
        if (!rs.wasNull()) {
            accommodation.setMobilePhone1(mobPhone1);
        }

        String mobPhone2 = rs.getString(++i);
        if (!rs.wasNull()) {
            accommodation.setMobilePhone2(mobPhone2);
        }

        String infoline = rs.getString(++i);
        if (!rs.wasNull()) {
            accommodation.setInfoline(infoline);
        }

        String fax = rs.getString(++i);
        if (!rs.wasNull()) {
            accommodation.setFax(fax);
        }

        byte[] photo = rs.getBytes(++i);
        if (!rs.wasNull()) {
            accommodation.setPhoto(photo);
        }

        String photoUrl = rs.getString(++i);
        if (!rs.wasNull()) {
            accommodation.setPhotoUrl(photoUrl);
        }

        return accommodation;
    }

    @Override
    public void run() {
        Connection conn = JdbcUtil.getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ROWS);
            while (rs.next()) {
                Accommodation accommodation = readParams(rs);
                accommodationQueue.put(accommodation);
                log.debug("Read accommodation: " + accommodation);
            }
        } catch (SQLException e) {
            String msg = "Unable to read from the database";
            log.error(msg, e);
            throw new IllegalStateException(msg, e);
        } catch (InterruptedException e) {
            String msg = "Reading from the database was interrupted";
            log.error(msg, e);
            throw new IllegalStateException(msg, e);
        } finally {
            JdbcUtil.close(stmt);
            JdbcUtil.close(conn);
        }
    }

}
