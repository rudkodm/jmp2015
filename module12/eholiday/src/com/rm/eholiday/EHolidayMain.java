package com.rm.eholiday;

import java.util.*;
import java.util.concurrent.*;
import com.rm.eholiday.http.*;
import com.rm.eholiday.config.Config;
import com.rm.eholiday.kml.CreateTask;
import com.rm.eholiday.sql.ReadTask;
import com.rm.eholiday.sql.StoreTask;
import com.rm.eholiday.beans.Accommodation;

public class EHolidayMain {

    private static final int POOL_SIZE = Config.getThread().getPoolSize();
    private static final int QUEUE_SIZE = Config.getThread().getQueueSize();
    private static final int NOTIFICATION_TO = Config.getThread().getNotificationTimeout();
    private static final double RADIUS = Config.getSearch().getRadius();
    private static final double METERS_LONG = Config.getSearch().getMetersPerLongitudeDegree();
    private static final double METERS_LAT = Config.getSearch().getMetersPerLatitudeDegree();
    private static final double NORTH = Config.getSearch().getNorthernmostLatitude();
    private static final double SOUTH = Config.getSearch().getSouthernmostLatitude();
    private static final double EAST = Config.getSearch().getEasternmostLongitude();
    private static final double WEST = Config.getSearch().getWesternmostLongitude();
    private static final String FILE_ID_CACHE = Config.getFile().getIdCache();
    private static final String FILE_FETCH_FAILED = Config.getFile().getFetchFailed();
    private static final String FILE_STORE_FAILED = Config.getFile().getStoreFailed();

    private static final Log log = Log.newLog("EHSystem");

    private static class DefaultUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            log.error("Exit due to uncaught exception", e);
            System.exit(-1);
        }
    }

    private static Set<String> searchIds() {
        double sin30 = Math.sin(Math.PI / 6);
        double cos30 = Math.cos(Math.PI / 6);

        double northThreshold = NORTH + RADIUS / METERS_LAT;
        double eastThreshold = EAST + cos30 * RADIUS / METERS_LONG;

        Set<String> idSharedSet = Collections.synchronizedSet(new HashSet<String>());
        ExecutorService threadPool = Executors.newFixedThreadPool(POOL_SIZE);
        for (int i = 0; true; i++) {
            double latitude = SOUTH + ((i + 1) * sin30 + i) * RADIUS / METERS_LAT;
            if (latitude > northThreshold) {
                break;
            }

            for (int j = 0; true; j++) {
                double longitude = WEST + ((i + 1) % 2 + 2 * j) * cos30 * RADIUS / METERS_LONG;
                if (longitude > eastThreshold) {
                    break;
                }

                SearchTask searchTask = new SearchTask(longitude, latitude, RADIUS, idSharedSet);
                threadPool.execute(searchTask);
            }
        }

        threadPool.shutdown();
        try {
            int i = 1;
            while (!threadPool.awaitTermination(NOTIFICATION_TO, TimeUnit.SECONDS)) {
                if (log.isInfo()) {
                    StringBuilder message = new StringBuilder();
                    message.append("Awaiting search termination [").append(i++ * NOTIFICATION_TO).
                            append(" seconds, ").append(idSharedSet.size()).append(" ids]...");
                    log.info(message);
                }
            }
        } catch (InterruptedException e) {
            String msg = "Search is interrupted";
            log.error(msg, e);
            throw new IllegalStateException(msg, e);
        }

        if (log.isInfo()) {
            log.info(new StringBuilder().append("Search completed: ").append(idSharedSet.size()).append(" ids found"));
        }

        return idSharedSet;
    }

    private static Set<String> getIds() {
        /*Set<String> idSet = FileUtil.readFromFile(FILE_ID_CACHE);
        if (idSet == null) {
            log.info("Id cache file does not exist. Searching ids...");
            idSet = searchIds();
            FileUtil.saveToFile(idSet, FILE_ID_CACHE);
        }
        return idSet;*/

        return searchIds();
    }

    private static void fetchAndStore() {
        Set<String> idSet = getIds();

        if (idSet == null || idSet.isEmpty() || StoreTask.filterIds(idSet).isEmpty()) {
            return;
        }

        Queue<String> idQueue = new ArrayBlockingQueue<String>(idSet.size(), false, idSet);
        Set<String> fetchFailedIds = Collections.synchronizedSet(new HashSet<String>());
        Set<String> storeFailedIds = Collections.synchronizedSet(new HashSet<String>());
        BlockingQueue<Accommodation> accommodationQueue = new ArrayBlockingQueue<Accommodation>(QUEUE_SIZE);

        ExecutorService threadPool = Executors.newFixedThreadPool(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            threadPool.execute(new FetchTask(idQueue, fetchFailedIds, accommodationQueue));
        }

        StoreTask store = new StoreTask(storeFailedIds, accommodationQueue);
        Thread storeThread = new Thread(store);
        storeThread.start();

        threadPool.shutdown();
        try {
            int i = 1;
            while (!threadPool.awaitTermination(NOTIFICATION_TO, TimeUnit.SECONDS)) {
                if (log.isInfo()) {
                    StringBuilder message = new StringBuilder();
                    message.append("Awaiting fetch termination [").append(i++ * NOTIFICATION_TO).
                            append(" seconds, ").append(idQueue.size()).append(" ids left]...");
                    log.info(message);
                }
            }
        } catch (InterruptedException e) {
            String msg = "Fetch is interrupted";
            log.error(msg, e);
            throw new IllegalStateException(msg, e);
        }

        store.shutdown();
        try {
            storeThread.join();
        } catch (InterruptedException e) {
            String msg = "Store is interrupted";
            log.error(msg, e);
            throw new IllegalStateException(msg, e);
        }

        if (!fetchFailedIds.isEmpty()) {
            FileUtil.saveToFile(fetchFailedIds, FILE_FETCH_FAILED, true);
        }
        if (!storeFailedIds.isEmpty()) {
            FileUtil.saveToFile(storeFailedIds, FILE_STORE_FAILED, true);
        }

        log.info("Fetch completed");
    }

    private static void readAndCreate() {
        BlockingQueue<Accommodation> accommodationQueue = new ArrayBlockingQueue<Accommodation>(QUEUE_SIZE);

        ReadTask read = new ReadTask(accommodationQueue);
        Thread readThread = new Thread(read);
        readThread.start();

        CreateTask create = new CreateTask(accommodationQueue);
        Thread createThread = new Thread(create);
        createThread.start();

        try {
            readThread.join();
        } catch (InterruptedException e) {
            String msg = "Read is interrupted";
            log.error(msg, e);
            throw new IllegalStateException(msg, e);
        }

        create.shutdown();
        try {
            createThread.join();
        } catch (InterruptedException e) {
            String msg = "Create is interrupted";
            log.error(msg, e);
            throw new IllegalStateException(msg, e);
        }
    }

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler());

        log.info("Starting eholiday.pl import...");
        fetchAndStore();
        readAndCreate();
        log.info("eholiday.pl import completed");
    }

}
