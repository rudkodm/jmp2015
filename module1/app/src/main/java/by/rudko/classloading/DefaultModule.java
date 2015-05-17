package by.rudko.classloading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultModule implements Module{
	private static final Logger LOG = LogManager.getLogger(DefaultModule.class.getName());
	
    @Override
    public void load() {
    	LOG.info(">> Load DefaultModule");
    }

    @Override
    public void run() {
    	LOG.info(">> Run DefaultModule");
    }

    @Override
    public void unload() {
    	LOG.info(">> Unload DefaultModule");
    }
}
