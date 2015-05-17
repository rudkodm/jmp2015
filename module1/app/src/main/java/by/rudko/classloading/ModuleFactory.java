package by.rudko.classloading;

import java.net.URL;
import java.net.URLClassLoader;

public class ModuleFactory {
	
	private static enum Holder {
		INSTANCE(new ModuleFactory());
		
		private ModuleFactory factory;
		
		Holder(ModuleFactory factory){
			this.factory = factory;
		}
	}
	
	private ModuleFactory() {}
	
	public static ModuleFactory getInstance(){
		return Holder.INSTANCE.factory;
	}
	
	
	
	@SuppressWarnings("resource")
    public Module getModule(URL url) throws Exception {
		if(url == null){
			return new DefaultModule();
		}
		
		URLClassLoader modulesLoader = new URLClassLoader(new URL[]{url}) ;
        return (Module) modulesLoader.loadClass("by.rudko.classloading.CustomModule").newInstance();
    }

}
