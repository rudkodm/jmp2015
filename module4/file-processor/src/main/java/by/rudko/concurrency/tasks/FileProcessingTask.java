package by.rudko.concurrency.tasks;

import by.rudko.concurrency.processor.FileProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by rudkodm on 6/7/15.
 */
public class FileProcessingTask implements Runnable {
    private final BlockingQueue<File> queue;
    private static final Logger LOG = LogManager.getLogger(FileProcessingTask.class);

    public FileProcessingTask(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            File element = queue.poll(FileProcessor.TIME_OUT_VALUE, FileProcessor.TIME_OUT_UNIT);
            if (element == null) {
                LOG.debug("<< null");
                return;
            } else if (element.isDirectory()) {
                LOG.debug("<< Dir: {}", element.getName());
                File[] files = element.listFiles();
                queue.addAll(Arrays.asList(files));
            } else {
                // Long calculation
                Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                LOG.debug("<< File: {}", element.getName());
            }
        } catch (InterruptedException e) {
            LOG.info("<< Forcing shutdown the task...");
        }
    }
}
