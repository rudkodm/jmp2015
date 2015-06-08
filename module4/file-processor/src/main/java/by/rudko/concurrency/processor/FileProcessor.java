package by.rudko.concurrency.processor;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.rudko.concurrency.config.Configuration;
import by.rudko.concurrency.tasks.FileProcessingTask;
import by.rudko.concurrency.utils.ExecutorUtils;

/**
 * Created by rudkodm on 6/6/15.
 */
public class FileProcessor {
    private static final Logger LOG = LogManager.getLogger(FileProcessor.class);

    public static Object process(final File file, final FileProcessingTask task){
    	return process(file, task, null);
    }
    
    public static Object process(final File file, final FileProcessingTask task, final Object executionContext) {
    	
        final BlockingQueue<File> filesQueue = new LinkedBlockingQueue<>();
        filesQueue.add(file);

        final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>(Integer.MAX_VALUE);
		final ThreadPoolExecutor executor = new ThreadPoolExecutor(
				Configuration.POOL_SIZE,
				Configuration.POOL_SIZE,
				0L,
				TimeUnit.MILLISECONDS,
				taskQueue);

        while (isNotFinished(filesQueue)) {
            if(taskQueue.size() < filesQueue.size()){
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            task.execute(filesQueue, executionContext);
                        } catch (InterruptedException e) {
                            LOG.info("<< Forcing shutdown the task...");
                        }
                    }
                });
            }
        }

        ExecutorUtils.shutdown(executor, Configuration.TERMINATION_TIME_SEC, TimeUnit.SECONDS);
        
        return executionContext;
    }

    private static boolean isNotFinished(BlockingQueue<File> queue) {
        if (!queue.isEmpty()) {
            return true;
        } else {
            try {
				Thread.sleep(Configuration.TIME_OUT_UNIT.toMillis(Configuration.TIME_OUT_VALUE));
			} catch (InterruptedException e) {
				// Do nothing
			}

            if (queue.isEmpty()) {
                LOG.info("task completed");
                return false;
            } else {
                return true;
            }
        }
    }

}
