package by.rudko.concurrency.task2;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.rudko.concurrency.config.Configuration;
import by.rudko.concurrency.processor.FileProcessor;
import by.rudko.concurrency.tasks.FileProcessingTask;


/**
	≈сть список ссылок на файлы (архивы). нужно написать апликуху котора€: 
	- скачивала их параллельно;
	- кол-во потоков задаетс€ в константе или через ввод с консоли;
	- после завершени€ скачивани€ всех файлов, вывести содержани€ фолдера (куда была скачка)
	- предусмотреть таймаут на скачивание
	т.е. скипать скачивание, если не успели скачать за 30 секунд например (таймаут или вводитс€ или беретс€ из константы)

	@author Dmitriy_Rudko
 */

public class ConcurrentCopyApplication {
	private static final Logger LOG = LogManager.getLogger(ConcurrentCopyApplication.class);
	
	/**
	 * This class holds processing specific logic for this task
	 * @author Dmitriy_Rudko
	 */
	private static class CopyTask implements FileProcessingTask {
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
				
				try {
					FileUtils.copyFileToDirectory(element, destinationFolder);
				} catch (IOException e) {
					LOG.error("Can't download file: {}; to Dir: {}; Cause: {}", element, destinationFolder, e.getStackTrace());
				}
	        }
	    }
	}

	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		File destinationFolder = new File(Configuration.COPY_TASK_SOURCE_PATH);
		
		FileProcessor.process(
				new File(Configuration.TEST_DATA_PATH),
				new CopyTask(destinationFolder),
				null);
	
		printResult(destinationFolder.list());
	}


	private static void printResult(String[] list) {
		StringBuilder builder = new StringBuilder("\r\n");
		builder.append("Result : [").append("\r\n");
		for (String str : list) {
			builder.append("  ").append(str).append("\r\n");
		}
		builder.append("]");
		
		LOG.debug(builder.toString());
	}
	
	

}

