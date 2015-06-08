package by.rudko.concurrency.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ExecutorUtils {
	private ExecutorUtils() {
	}
	
	private static final Logger LOG = LogManager.getLogger(ExecutorUtils.class);

	public static void shutdown(final ExecutorService executor, final int terminationTime, final TimeUnit timeUnit) {
		executor.shutdown();
		try {
			if (executor.awaitTermination(terminationTime, timeUnit)) {
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

}
