package by.rudko.classloading;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
	private static final Logger LOG = LogManager.getLogger(Application.class.getName());
	private static final ModuleFactory factory = ModuleFactory.getInstance();
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    
	//file:d:/workspace/jmp2015/module1/plugin/build/libs/plugin.jar
    public static void main(String[] args) throws Exception{
        while(true){
        	LOG.info("COMMAND:");

        	switch (in.readLine()) {
			
        	case "default":{ 
				process(factory.getModule(null));
				break;
			}
			
        	case "custom":{
        		LOG.info("PATH:");
        		
        		String path = in.readLine();
        		URL url = new URL(path);
        		if(exist(url)){
        			process(factory.getModule(url));
        		} else {
        			LOG.error("No such resource:" + path);
        			break;
        		}
        		
				break;
			}
        	
        	default:{ 
        		LOG.info(">> Please try another command");
				break;
			}
			}
        }
    }
    
    private static void process(Module module){
    	module.load();
    	module.run();
    	module.unload();
    }

    private static boolean exist(URL url) {
    	try {
    		url.openConnection();
    	} catch (Exception e) {
    		return false;
    	}
    	return true;
    }
}
