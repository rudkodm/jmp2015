package by.rudko.classloading;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ModuleFactory {

    public static Module getInstance() throws Exception {
        URLClassLoader libLoader = new URLClassLoader(new URL[]{getApiClassPath()}, ClassLoader.getSystemClassLoader()) {
            public Class loadClass(String name) throws ClassNotFoundException {
                return super.findClass(name);
            }
        };

        URLClassLoader modulesLoader = new URLClassLoader(new URL[]{getModuleClassPath()}, libLoader) {
            public Class loadClass(String name) throws ClassNotFoundException {
                return super.findClass(name);
            }
        };

        return (Module) modulesLoader.loadClass("by.rudko.classloading.CustomModule").newInstance();
    }

    private static URL getModuleClassPath() throws MalformedURLException {
        return new URL("file:/home/rudkodm/workspace/jmp2015/module1/plugin/build/classes/main/");
    }
    private static URL getApiClassPath() throws MalformedURLException {
        return new URL("file:/home/rudkodm/workspace/jmp2015/module1/api/build/classes/main/");
    }
}
