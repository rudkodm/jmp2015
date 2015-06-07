package by.rudko.concurrency.processor;

import by.rudko.concurrency.tasks.FileProcessingTask;
import by.rudko.concurrency.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.concurrent.*;

/**
 * Created by rudkodm on 6/6/15.
 */
public class FileProcessor {
    private static final Logger LOG = LogManager.getLogger(FileProcessor.class);

    public static final long TIME_OUT_VALUE = 100;
    public static final TimeUnit TIME_OUT_UNIT = TimeUnit.MILLISECONDS;
    public static final int POOL_SIZE = 5;
    public static final int TERMINATION_TIME_SEC = 30;


    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue<File> filesQueue = new LinkedBlockingQueue<>();
        final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>(Integer.MAX_VALUE);

        final ThreadPoolExecutor executor = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE,
                0L, TimeUnit.MILLISECONDS,
                taskQueue);

        File file = new File(Configuration.TEST_DATA_PATH);
        filesQueue.add(file);

        while (isNotFinished(filesQueue)) {
            if(taskQueue.size() < filesQueue.size()){
                executor.execute(new FileProcessingTask(filesQueue));
            }
        }

        shutdown(executor);
    }

    private static void shutdown(ExecutorService executor) throws InterruptedException {
        executor.shutdown();
        if (executor.awaitTermination(TERMINATION_TIME_SEC, TimeUnit.SECONDS)) {
            LOG.info("Normal termination...");
        } else {
            LOG.error("Forcing shutdown...");
            executor.shutdownNow();
        }
    }

    private static boolean isNotFinished(BlockingQueue<File> queue) throws InterruptedException {
        if (!queue.isEmpty()) {
            return true;
        } else {
            Thread.sleep(TIME_OUT_UNIT.toMillis(TIME_OUT_VALUE));

            if (queue.isEmpty()) {
                LOG.info("task completed");
                return false;
            } else {
                return true;
            }
        }
    }

}
