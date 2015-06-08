package by.rudko.concurrency.task2;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.rudko.concurrency.config.Configuration.*;
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
		File destinationFolder = new File(COPY_TASK_SOURCE_PATH);
		
		FileProcessor.process(
				new File(TEST_DATA_PATH),
				new CopyTask(destinationFolder));
	
		printResult(destinationFolder.list());
	}


	private static void printResult(String[] list) {
		StringBuilder builder = new StringBuilder(SYSTEM_LINE_SEPARATOR);
		builder.append("Result : [").append(SYSTEM_LINE_SEPARATOR);
		for (String str : list) {
			builder.append("  ").append(str).append(SYSTEM_LINE_SEPARATOR);
		}
		builder.append("]");
		
		LOG.debug(builder.toString());
	}
	
	

}

