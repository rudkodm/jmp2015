package by.rudko.concurrency.config;


import static java.lang.System.getProperty;
import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

public final class Configuration {
	

	private Configuration() {
	}
	
	// CONFIG PROPERTIES NAMES
	private static final String PROPERTY_DATA_PATH = "module4.data.src.path";
	private static final String PROPERTY_OS_NAME = "os.name";

	
	// PUBLIC CONFIGURATION VARIABLES
	public static long TIME_OUT_VALUE = 100;
	public static TimeUnit TIME_OUT_UNIT = TimeUnit.MILLISECONDS;
	public static int POOL_SIZE = 5;
	public static int TERMINATION_TIME_SEC = 30;
	
	public static String TEST_DATA_PATH;
	public static String COPY_TASK_SOURCE_PATH;
	

	// INICIALIZATION
	static {
		String os = getProperty(PROPERTY_OS_NAME);
		if (StringUtils.contains(os, "Windows")) {
			windowsConfiguration();
		} else if (StringUtils.contains(os, "Linux")) {
			linuxConfiguration();
		} else {
			throw new ConfigurationException("Not supported OS");
		}
	}

	private static void windowsConfiguration() {
		TEST_DATA_PATH = defaultIfBlank(getProperty(PROPERTY_DATA_PATH), "d:/workspace/jmp2015/module4/test-data");
		COPY_TASK_SOURCE_PATH = defaultIfBlank(getProperty(PROPERTY_DATA_PATH), "d:/workspace/jmp2015/module4/build/tmp/copy_result");
	}

	private static void linuxConfiguration() {
		TEST_DATA_PATH = defaultIfBlank(getProperty(PROPERTY_DATA_PATH), "/home/rudkodm/workspace/jmp2015/module4/test-data");
	}
	






}
