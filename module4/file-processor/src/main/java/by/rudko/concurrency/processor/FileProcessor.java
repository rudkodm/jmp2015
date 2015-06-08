package by.rudko.concurrency.processor;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.rudko.concurrency.config.Configuration;
import by.rudko.concurrency.tasks.FileProcessingTask;

/**
 * Created by rudkodm on 6/6/15.
 */
public class FileProcessor {
    private static final Logger LOG = LogManager.getLogger(FileProcessor.class);

    
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

        shutdown(executor);
        
        return executionContext;
    }

    private static void shutdown(ExecutorService executor) {
        executor.shutdown();
        try {
			if (executor.awaitTermination(Configuration.TERMINATION_TIME_SEC, TimeUnit.SECONDS)) {
			    LOG.info("Normal termination...");
			} else {
			    LOG.error("Forcing shutdown...");
			    executor.shutdownNow();
			}
		} catch (InterruptedException e) {
			 LOG.error("Forcing shutdown...");
			 executor.shutdownNow();
		}
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
