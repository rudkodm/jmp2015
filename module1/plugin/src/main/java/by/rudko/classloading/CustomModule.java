package by.rudko.classloading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.rudko.classloading.beans.SomeLogic;

public class CustomModule implements Module{
	private static final Logger LOG = LogManager.getLogger(CustomModule.class.getName());
	
    @Override
    public void load() {
    	LOG.info(">> Load Custom CustomModule");
    }

    @Override
    public void run(){
    	LOG.info(">> Run Custom CustomModule: " + new SomeLogic());
    }

    @Override
    public void unload() {
    	LOG.info(">> Unload Custom CustomModule");
    }
}
