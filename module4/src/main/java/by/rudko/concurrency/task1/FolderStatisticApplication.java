package by.rudko.concurrency.task1;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.rudko.concurrency.config.Configuration;
import by.rudko.concurrency.processor.FileProcessor;


/**
	Please create CLI application which scans a specified folder and provides detailed statistics:
	1. File count
	2. Folder count
	3. Size (sum of all files size)
	(Similar like windows context menu "properties")
	Since the folder may contain huge amount of files the scanning process should be executed in a separate thread
	displaying an informational message with some simple animation like progress bar in CLI (up to you, but I'd like
	to see that task is in progress). Once task is done the statistics should be displayed in the output immediately.
	Additionally there should be ability to interrupt the process pressing some reserved key (for instance "c").
	Note: it's allowed to add some sleeps between file interruptions to simulate very long progress.

	@author Dmitriy_Rudko

 */

public class FolderStatisticApplication {
	private static final Logger LOG = LogManager.getLogger(FolderStatisticApplication.class);
	
	/**
	 * This class need to hold the result of execution
	 * @author Dmitriy_Rudko
	 */
	static class ExecutionContext {
		public AtomicInteger dirNumber = new AtomicInteger(0);
		public AtomicInteger fileNumber = new AtomicInteger(0);
		public AtomicLong fileSize = new AtomicLong(0);
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		
		ExecutionContext executionContext = new ExecutionContext();
		FileProcessor.process(
				new File(Configuration.TEST_DATA_PATH),
				new StatisticTask(),
				executionContext);
	
		LOG.debug("Result: DirNumber: {}, FileNumber: {}, FileSize: {}", 
				executionContext.dirNumber,
				executionContext.fileNumber,
				executionContext.fileSize);
	}

}

