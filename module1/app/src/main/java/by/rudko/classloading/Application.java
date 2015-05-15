package by.rudko.classloading;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Application {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception{
        while(true){
            String command = in.readLine();
            if("default".equals(command)) {
                Module module = new DefaultModule();

                module.load();
                module.run();
                module.unload();
            }

            if(true){
                Module module = ModuleFactory.getInstance();
                module.load();
                module.run();
                module.unload();
            }
        }
    }
}
