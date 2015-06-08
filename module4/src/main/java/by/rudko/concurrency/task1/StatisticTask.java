package by.rudko.concurrency.task1;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.rudko.concurrency.config.Configuration;
import by.rudko.concurrency.task1.FolderStatisticApplication.ExecutionContext;
import by.rudko.concurrency.tasks.FileProcessingTask;

/**
 * This class holds processing specific logic for this task
 * @author Dmitriy_Rudko
 */
class StatisticTask implements FileProcessingTask {
    private static final Logger LOG = LogManager.getLogger(StatisticTask.class);

    public StatisticTask() {
    }

    @Override
    public void execute(BlockingQueue<File> filesQueue, Object context) throws InterruptedException {
    	ExecutionContext executionContext = (ExecutionContext)context;
    	
        File element = filesQueue.poll(Configuration.TIME_OUT_VALUE, Configuration.TIME_OUT_UNIT);
        if (element == null) {
            LOG.debug("<< null");
            return;
        } else if (element.isDirectory()) {
            LOG.debug("<< Dir: {}", element.getName());
            filesQueue.addAll(Arrays.asList(element.listFiles()));
            executionContext.dirNumber.getAndIncrement();
        } else {
			LOG.debug("<< File: {};", element.getName());
			executionContext.fileNumber.getAndIncrement();
			executionContext.fileSize.getAndAdd(element.length());
        }
    }
}