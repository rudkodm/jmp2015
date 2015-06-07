package by.rudko.concurrency.config;


import static java.lang.System.getProperty;
import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

public final class Configuration {
    private Configuration() {
    }

    public static final String TEST_DATA_PATH;

    static {
        String os = getProperty("os.name");
        String testDataPath = getProperty("module4.data.src.path");

        if ("Windows".equals(os)) {
            TEST_DATA_PATH = defaultIfBlank(testDataPath, "");


        } else if ("Linux".equals(os)) {
            TEST_DATA_PATH = defaultIfBlank(testDataPath, "/home/rudkodm/workspace/jmp2015/module4/test-data");


        } else {
            throw new ConfigurationException("Not supported OS");
        }
    }
}
