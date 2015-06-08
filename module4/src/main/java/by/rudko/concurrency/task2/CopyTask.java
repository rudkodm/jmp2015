package by.rudko.concurrency.task2;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.rudko.concurrency.config.Configuration;
import by.rudko.concurrency.tasks.FileProcessingTask;

/**
 * This class holds processing specific logic for this task
 * @author Dmitriy_Rudko
 */
class CopyTask implements FileProcessingTask {
    private static final Logger LOG = LogManager.getLogger(CopyTask.class);
	
    private File destinationFolder;

    public CopyTask(File destinationFolder) {
    	this.destinationFolder = destinationFolder;
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
			LOG.debug("<< File: {};", element.getName());
			interruptedCopy(element, destinationFolder);
			
        }
    }

	private void interruptedCopy(final File file, final File destinationFolder) {
		final ExecutorService executor = Executors.newSingleThreadExecutor();
		final Future<?> future = executor.submit(new Runnable() {
			@Override
			public void run() {
				try {
					FileUtils.copyFileToDirectory(file, destinationFolder);
				} catch (IOException e) {
					LOG.error("Can't download file: {}; to Dir: {}; Cause: {}", file, destinationFolder, e.getStackTrace());
				}					
			}
		});
		
		try {
			future.get(Configuration.TIME_OUT_VALUE, Configuration.TIME_OUT_UNIT);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			LOG.error(e);
		} finally {
			 executor.shutdownNow();
		}
	}
}