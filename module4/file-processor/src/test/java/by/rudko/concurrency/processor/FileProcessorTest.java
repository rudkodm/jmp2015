package by.rudko.concurrency.processor;

import java.io.File;

import org.junit.Test;

import by.rudko.concurrency.config.Configuration;
import by.rudko.concurrency.tasks.DummyTask;

public class FileProcessorTest {

	
	@Test
	public void simple_test() {
		FileProcessor.process(new File(Configuration.TEST_DATA_PATH), new DummyTask(), null);
	}

}
