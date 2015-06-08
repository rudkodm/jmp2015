package by.rudko.concurrency.tasks;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.rudko.concurrency.config.Configuration;

/**
 * Created by rudkodm on 6/7/15.
 */
public class DummyTask implements FileProcessingTask {
    private static final Logger LOG = LogManager.getLogger(DummyTask.class);

    public DummyTask() {
    }

    @Override
    public void execute(BlockingQueue<File> filesQueue, Object context) throws InterruptedException {
        File element = filesQueue.poll(Configuration.TIME_OUT_VALUE, Configuration.TIME_OUT_UNIT);
        if (element == null) {
            LOG.debug("<< null");
            return;
        } else if (element.isDirectory()) {
            LOG.debug("<< Dir: {}", element.getName());
            filesQueue.addAll(Arrays.asList(element.listFiles()));
        } else {
            // Long calculation
//            Thread.sleep(TimeUnit.SECONDS.toMillis(5));
			LOG.debug("<< File: {};", element.getName());
        }
    }
}
