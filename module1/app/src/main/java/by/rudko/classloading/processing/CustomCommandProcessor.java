package by.rudko.classloading.processing;

import by.rudko.classloading.Module;
import by.rudko.classloading.ModuleFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.net.URL;
import java.nio.file.NoSuchFileException;

public class CustomCommandProcessor implements CommandProcessor {

    private static final Logger LOG = LogManager.getLogger(CustomCommandProcessor.class.getName());

    @Override
    public Module process(BufferedReader in, ModuleFactory factory) throws Exception {
        LOG.info("PATH:");

        String path = in.readLine();
        try {
            URL url = new URL(path);
            return factory.getModule(url);
        } catch (Exception e) {
            throw new NoSuchFileException("No such resource:" + path);
        }
    }
}
