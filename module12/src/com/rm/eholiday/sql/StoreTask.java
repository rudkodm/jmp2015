package com.rm.eholiday.sql;

import java.sql.*;
import java.util.Set;
import java.util.concurrent.*;
import com.rm.eholiday.*;
import com.rm.eholiday.beans.*;
import com.rm.eholiday.config.Config;

public class StoreTask implements Runnable {

    private static final long POLLING_TIMEOUT = Config.getThread().getPollingTimeout();
    private static final String INSERT_ROW = Config.getSql().getInsertRow();
    private static final String SELECT_IDS = Config.getSql().getSelectIds();

    private static Log log = Log.newLog("Store");

    private Set<String> storeFailedIds;
    private BlockingQueue<Accommodation> accommodationQueue;
    private volatile boolean stop = false;
    private Connection connection;
    private PreparedStatement insert;

    public StoreTask(Set<String> storeFailedIds, BlockingQueue<Accommodation> accommodationQueue) {
        this.storeFailedIds = storeFailedIds;
        this.accommodationQueue = accommodationQueue;
    }

    public static Set<String> filterIds(Set<String> idSet) {
        Connection conn = null;
        Statement stmt = null;
        while (true) {
            try {
                conn = JdbcUtil.getConnection();
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SELECT_IDS);
                while (rs.next()) {
                    idSet.remove(rs.getString(1));
                }
                return idSet;
            } catch (SQLException e) {
                log.error("Unable to filter IDs", e);
            } finally {
                JdbcUtil.close(stmt);
                JdbcUtil.close(conn);
            }
        }
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

    private void populateParams(PreparedStatement prepStatement, Accommodation accommodation) throws SQLException {
        int i = 0;
        prepStatement.clearParameters();
        prepStatement.setString(++i, accommodation.getId());
        prepStatement.setNString(++i, accommodation.getName());
        prepStatement.setDate(++i, new Date(accommodation.getInDbSince().getTime()));
        prepStatement.setDate(++i, new Date(accommodation.getCopied().getTime()));
        prepStatement.setInt(++i, accommodation.getCategory().getId());
        prepStatement.setNString(++i, accommodation.getStreetAddress());
        prepStatement.setString(++i, accommodation.getPostalCode());
        prepStatement.setNString(++i, accommodation.getLocality());
        prepStatement.setNString(++i, accommodation.getRegion());
        prepStatement.setNString(++i, accommodation.getCountry());
        prepStatement.setDouble(++i, accommodation.getLongitude());
        prepStatement.setDouble(++i, accommodation.getLatitude());
        prepStatement.setInt(++i, accommodation.getStars());

        double priceFrom = accommodation.getPriceFrom();
        if (priceFrom == 0) {
            prepStatement.setNull(++i, Types.DOUBLE);
        } else {
            prepStatement.setDouble(++i, priceFrom);
        }

        PriceUnit priceUnit = accommodation.getPriceUnit();
        if (priceUnit == null) {
            prepStatement.setNull(++i, Types.INTEGER);
        } else {
            prepStatement.setInt(++i, priceUnit.getId());
        }

        Currency currency = accommodation.getCurrency();
        if (currency == null) {
            prepStatement.setNull(++i, Types.CHAR);
        } else {
            prepStatement.setString(++i, currency.toString());
        }

        String website1 = accommodation.getWebsite1();
        if (website1 == null) {
            prepStatement.setNull(++i, Types.VARCHAR);
        } else {
            prepStatement.setString(++i, website1);
        }

        String website2 = accommodation.getWebsite2();
        if (website2 == null) {
            prepStatement.setNull(++i, Types.VARCHAR);
        } else {
            prepStatement.setString(++i, website2);
        }

        String skype = accommodation.getSkype();
        if (skype == null) {
            prepStatement.setNull(++i, Types.VARCHAR);
        } else {
            prepStatement.setString(++i, skype);
        }

        String gg = accommodation.getGg();
        if (gg == null) {
            prepStatement.setNull(++i, Types.VARCHAR);
        } else {
            prepStatement.setString(++i, gg);
        }

        String facebook = accommodation.getFacebook();
        if (facebook == null) {
            prepStatement.setNull(++i, Types.VARCHAR);
        } else {
            prepStatement.setString(++i, facebook);
        }

        String phone1 = accommodation.getPhone1();
        if (phone1 == null) {
            prepStatement.setNull(++i, Types.VARCHAR);
        } else {
            prepStatement.setString(++i, phone1);
        }

        String phone2 = accommodation.getPhone2();
        if (phone2 == null) {
            prepStatement.setNull(++i, Types.VARCHAR);
        } else {
            prepStatement.setString(++i, phone2);
        }

        String mobPhone1 = accommodation.getMobilePhone1();
        if (mobPhone1 == null) {
            prepStatement.setNull(++i, Types.VARCHAR);
        } else {
            prepStatement.setString(++i, mobPhone1);
        }

        String mobPhone2 = accommodation.getMobilePhone2();
        if (mobPhone2 == null) {
            prepStatement.setNull(++i, Types.VARCHAR);
        } else {
            prepStatement.setString(++i, mobPhone2);
        }

        String infoline = accommodation.getInfoline();
        if (infoline == null) {
            prepStatement.setNull(++i, Types.VARCHAR);
        } else {
            prepStatement.setString(++i, infoline);
        }

        String fax = accommodation.getFax();
        if (fax == null) {
            prepStatement.setNull(++i, Types.VARCHAR);
        } else {
            prepStatement.setString(++i, fax);
        }

        byte[] photo = accommodation.getPhoto();
        if (photo == null) {
            prepStatement.setNull(++i, Types.BLOB);
        } else {
            prepStatement.setBytes(++i, photo);
        }

        String photoUrl = accommodation.getPhotoUrl();
        if (photoUrl == null) {
            prepStatement.setNull(++i, Types.VARCHAR);
        } else {
            prepStatement.setString(++i, photoUrl);
        }
    }

    private PreparedStatement getInsertRow() throws SQLException {
        Connection newConnection = JdbcUtil.ensureConnection(connection);
        if (newConnection == connection) {
            return insert;
        }

        connection = newConnection;
        return insert = connection.prepareStatement(INSERT_ROW);
    }

    private void releaseResources() {
        JdbcUtil.close(insert);
        JdbcUtil.close(connection);
    }

    @Override
    public void run() {
        while (true) {
            boolean doStop = stop;
            Accommodation accommodation = next();
            if (accommodation != null) {
                try {
                    log.debug("Start storing. Accommodation: " + accommodation);
                    PreparedStatement insert = getInsertRow();
                    populateParams(insert, accommodation);
                    insert.executeUpdate();
                    log.debug("Stored. Accommodation: " + accommodation);
                } catch (Exception e) {
                    log.error("Unable to insert accommodation: " + accommodation, e);
                    storeFailedIds.add(accommodation.getId());
                }
            } else if (doStop) {
                break;
            }
        }

        releaseResources();
    }

    public void shutdown() {
        stop = true;
    }

}
