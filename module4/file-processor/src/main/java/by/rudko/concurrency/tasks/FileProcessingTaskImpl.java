package by.rudko.concurrency.tasks;

import by.rudko.concurrency.processor.FileProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by rudkodm on 6/7/15.
 */
public class FileProcessingTaskImpl implements FileProcessingTask {
    private static final Logger LOG = LogManager.getLogger(FileProcessingTaskImpl.class);

    public FileProcessingTaskImpl() {
    }

    @Override
    public void execute(BlockingQueue<File> filesQueue, Map<?, ?> context) throws InterruptedException {
        File element = filesQueue.poll(FileProcessor.TIME_OUT_VALUE, FileProcessor.TIME_OUT_UNIT);
        if (element == null) {
            LOG.debug("<< null");
            return;
        } else if (element.isDirectory()) {
            LOG.debug("<< Dir: {}", element.getName());
            File[] files = element.listFiles();
            filesQueue.addAll(Arrays.asList(files));
        } else {
            // Long calculation
            Thread.sleep(TimeUnit.SECONDS.toMillis(5));
            LOG.debug("<< File: {}", element.getName());
        }
    }
}
