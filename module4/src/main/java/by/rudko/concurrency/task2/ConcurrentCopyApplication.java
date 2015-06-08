package by.rudko.concurrency.task2;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.rudko.concurrency.config.Configuration;
import by.rudko.concurrency.processor.FileProcessor;


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
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		File destinationFolder = new File(Configuration.COPY_TASK_SOURCE_PATH);
		
		FileProcessor.process(
				new File(Configuration.TEST_DATA_PATH),
				new CopyTask(destinationFolder));
	
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

